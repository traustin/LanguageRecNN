import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * <h1>The Backpropagation Learning Algorithm</h1>
 * This is implements the Backpropagation learning algorithm.
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 27/05/2015
 */
public class BackPropagationAlgorithm {
    private Node[] inputNodes;
    private WeightedNode[] hiddenNodes;
    private WeightedNode[] outputNodes;
    private ActivationFunction activationFunction;
    private String outputFile;

    public BackPropagationAlgorithm(ActivationFunction activationFunction, String outputFile) {
        this.activationFunction = activationFunction;
        this.outputFile = outputFile;
    }

    public void run(int noOfHidNode, String dataset, int maxEpochs, double learningRate, double momentum) throws Exception {
        int nOfPatt = calculateLines(dataset);
        if(noOfHidNode==nOfPatt){
            throw new Exception("Error: amount of patterns equals the amount of hidden nodes");
        }
        iniatilizeWeights(noOfHidNode);
        FileWriter output = new FileWriter(outputFile);
        output.append("Epochs,TrainingAccuracy,GeneralizationAccuracy\n");
        /*-----------------------Initialize all values---------------------------------------------------*/
        int epochs = 0;

        int trainSet = nOfPatt * 8 / 10;

        /*--------------------Repeat until maximum epochs-----------------------------------------------*/
        while (epochs < maxEpochs) {
            /*Set training accuracy to zero*/
            double AT = 0;
            /*Increment epochs*/
            epochs++;
            BufferedReader br = new BufferedReader(new FileReader(dataset));
            /*For each pattern in the training set*/
            for (int i = 0; i < trainSet; i++) {
                String[] s = br.readLine().split(" ");
                for (int j = 0; j < s.length - 1; j++) {
                    inputNodes[j].setInput(Double.parseDouble(s[j]));
                }
                /*Target output*/
                int target = (int) Double.parseDouble(s[s.length - 1]);
                /*Net input & activation function for each hiddenNode*/
                for (WeightedNode node : hiddenNodes) {
                    node.getInput();
                }
                /*Accuracy value to determine how accurate */
                int accuracy = 1;
                double errSigOut[] = new double[outputNodes.length];
                double errSigHid[] = new double[hiddenNodes.length];
                for (int j = 0; j < outputNodes.length; j++) {
                    /**/
                    double oK = outputNodes[j].getInput();
                    double aK = oK;
                    if (aK > 0.7) {
                        aK = 1;
                    } else if (aK < 0.3) {
                        aK = 0;
                    }

                    double tK = target;
                    if (j != 0) {
                        tK = 1 - target;
                    }


                    if (tK != aK && accuracy == 1) {
                        accuracy = 0;
                    }
                    //Calculate error signal for each output
                    errSigOut[j] = (-(tK - oK)) * (1 - oK) * oK;
                    //Calculate error signal for each hidden input
                    errSigHid = outputNodes[j].getHidSigErr(errSigOut[j], errSigHid);
                }
                AT += accuracy;
                //Calculate new weights for hidden-to-output weights
                for (int j = 0; j < outputNodes.length; j++) {
                    outputNodes[j].updateWeights(learningRate, errSigOut[j], momentum);
                }
                //Calculate new weights for input-to-hidden weights
                for (int j = 0; j < hiddenNodes.length; j++) {
                    hiddenNodes[j].updateWeights(learningRate, errSigHid[j], momentum);
                }
            }
            AT = AT / trainSet * 100;
            double AG = 0;
            for (int i = trainSet; i < nOfPatt; i++) {
                String[] s = br.readLine().split(" ");
                for (int j = 0; j < s.length - 1; j++) {
                    inputNodes[j].setInput(Double.parseDouble(s[j]));
                }
                /*Target output*/
                int target = (int) Double.parseDouble(s[s.length - 1]);

                for (WeightedNode node: hiddenNodes) {
                    node.getInput();
                }

                int accuracy = 1;
                for (int j = 0; j < outputNodes.length; j++) {
                    /**/
                    double aK = outputNodes[j].getInput();
                    if (aK > 0.7) {
                        aK = 1;
                    } else if (aK < 0.3) {
                        aK = 0;
                    }

                    double tK = target;
                    if (j != 0) {
                        tK = 1 - target;
                    }


                    if (tK != aK && accuracy == 1) {
                        accuracy = 0;
                    }
                }
                AG += accuracy;
            }
            br.close();
            AG = AG / (nOfPatt - trainSet) * 100;
            System.out.println("Epoch Number: " + epochs + "\tTraining Accuracy: " + AT + "%\tGeneralization Accuracy: " + AG + "%");
            output.append(epochs + "," + AT + "," + AG + "\n");
        }
        output.flush();
        output.close();
    }

    private void iniatilizeWeights(int noOfHidNode) {
        inputNodes = new Node[26];
        //TODO Check if noOfHidNode+1 is not equal to patterns
        hiddenNodes = new WeightedNode[noOfHidNode];
        outputNodes = new WeightedNode[2];
        for (int i = 0; i < inputNodes.length; i++) {
            inputNodes[i] = new Node();
        }
        for (int i = 0; i < noOfHidNode; i++) {
            hiddenNodes[i] = new WeightedNode(inputNodes, activationFunction);
        }
        for (int i = 0; i < outputNodes.length; i++) {
            outputNodes[i] = new WeightedNode(hiddenNodes, activationFunction);
        }
    }

    private int calculateLines(String dataset) {
        int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dataset));
            while (br.readLine() != null) {
                count++;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
