import java.util.Random;

/**
 * <h1>The Weighted Node</h1>
 * This node class inherits all the value from the parent node class. It also contains all the weights that point to it.
 * This works with relation to the nodes that point to it. The class takes in all these weights and nodes and gets a net sum of them.
 * The net sum is then passed to an activation function which determines the final input value of the node.
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public class WeightedNode extends Node {
    /**
     * This variable stores the weights for all nodes related to this node.
     */
    double [] weights;
    /**
     * This variable stores pointers to all the nodes.
     */
    Node [] nodes;
    /**
     * This is function used to activate the node.
     */
    ActivationFunction activationFunction;

    /**
     * This is the default constructor of the class and it sets all member variables to 0. This class should rarely be used.
     */
    public WeightedNode() {
        weights = null;
        nodes = null;
        activationFunction = null;
    }


    /**
     * This is an overloaded constructor of the class and allows the user to set the nodes pointed to and the current activation function.
     * @param nodes These are the nodes currently pointed to by the user.
     * @param activationFunction This is activation function that will be used.
     */
    public WeightedNode(Node[] nodes, ActivationFunction activationFunction) {
        this.nodes = nodes;
        this.activationFunction = activationFunction;
        weights = new double[nodes.length];
        generateWeights();
    }

    /**
     * This function will randomly generate the weights for the nodes.
     * It will only generate weights when the weights member variable has been set.
     */
    public void generateWeights(){
        if(weights.length==0)
            return;
        double fanin = weights.length;
        for(int i = 0;  i < weights.length; i++){
            Random rand = new Random();
            weights[i] = -1/(Math.sqrt(fanin)) + ((1/(Math.sqrt(fanin))) + (1/(Math.sqrt(fanin)))) * rand.nextDouble();
            System.out.println(weights[i]);
        }
    }


}
