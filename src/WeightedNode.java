import java.util.Random;

/**
 * Created by User on 5/26/2015.
 */
public class WeightedNode extends Node {
    double [] weights;
    Node [] nodes;
    ActivationFunction activationFunction;

    public WeightedNode() {
        weights = null;
        nodes = null;
        activationFunction = null;
    }

    public WeightedNode(Node[] nodes, ActivationFunction activationFunction) {
        this.nodes = nodes;
        this.activationFunction = activationFunction;
        weights = new double[nodes.length];
        generateWeights();
    }

    private void generateWeights(){
        double fanin = weights.length;
        for(int i = 0;  i < weights.length; i++){
            Random rand = new Random();
            weights[i] = -1/(Math.sqrt(fanin)) + ((1/(Math.sqrt(fanin))) + (1/(Math.sqrt(fanin)))) * rand.nextDouble();
        }
    }


}
