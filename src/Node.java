/**
 * <h1>The Node Class</h1>
 * Provides a node that a user can use within the backpropagation algorithm. Generally used for input nodes.
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public class Node {
    /**
     * This is the input value of the node
     */
    double input;

    /**
     * Default constructor of the node. It sets the value to 0.
     */
    public Node(){
        input = 0;
    }

    /**
     * Overloaded constructor of the node class. Allows the user to set the input of the node.
     * @param input
     */
    public Node(double input) {
        this.input = input;
    }

    /**
     * Accessor for the input value.
     * @return Returns a double that is the input value of the node.
     */
    public double getInput() {
        return input;
    }

    /**
     * Mutator for the input value.
     * @param input This is value in which the user has used to change the value of the input member variable.
     */
    public void setInput(double input) {
        this.input = input;
    }
}
