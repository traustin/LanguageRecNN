import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * <h1>The Abstract Node Class</h1>
 * Provides a interface for a node class.
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public abstract class AbstractNode {
    /**
     * This is the input value of the node
     */
    double input;

    /**
     * Default constructor of the node. It sets the value to 0.
     */
    public AbstractNode() {
        input = 0;
    }

    /**
     * Overloaded constructor of the node class. Allows the user to set the input of the node.
     *
     * @param input
     */
    public AbstractNode(double input) {
        this.input = input;
    }

    /**
     * Accessor for the input value.
     *
     * @return Returns a double that is the input value of the node.
     */
    public double getInput() {
        return input;
    }
}
