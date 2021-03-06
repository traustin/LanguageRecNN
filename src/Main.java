import java.io.IOException;

/**
 * This is the main class. It is used for testing purposes at the current time but will be updated towards the end of the project.
 * This update will allow the neural network to be run.
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public class Main {
    /**
     * This is used to run the program. It calls upon the DatasetCreator which creates the data set and then runs each pattern in the data set through the backpropagation algorithm.
     * @param args This is used to construct the values to passed to the backpropagation algorithm.
     */
    public static void main(String[] args) throws IOException {
        if(args.length!=5){
            System.out.println("Incorrect amount of arguments entered\n" +
                    "Format is: [inputDoc][amountOfHiddenNodes][amountOfEpochs][learningRate][momentum]");
        }
        DatasetCreator data = new DatasetCreator();
        for(int i = 1; i <= 11; i++){
            data.addEnglishDocument("documents/eng" + i + ".txt");
        }
        for(int i = 1; i <= 11; i++){
            data.addAfrikaansDocument("documents/afr" + i + ".txt");
        }
        data.shuffle();

        BackPropagationAlgorithm algorithm = new BackPropagationAlgorithm(new SigmoidFunction(), "output.csv");
        try {
            algorithm.run(Integer.parseInt(args[1]), args[0], Integer.parseInt(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
