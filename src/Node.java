/**
 * <h1>The Node Class</h1>
 * Provides a node that a user can use within the backpropagation algorithm. Generally used for input & bias nodes.
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public class Node extends AbstractNode {

    /**
     * Calls the default constructor of the parent
     */
    public Node() {
    }

    /**
     * Calls the parent overloaded constructor.
     *
     * @param input
     */
    public Node(double input) {
    }

    /**
     * Mutator for the input value.
     *
     * @param input This is value in which the user has used to change the value of the input member variable.
     */
    public void setInput(double input) {
        this.input = input;
    }
}
