/**
 * <h1>The Activation Function</h1>
 * An abstract interface to allow for multiple instances of the Activation Function
 *
 * @author Trevor Austin
 * @version 1.0
 * @since 26/05/2015
 */
public interface ActivationFunction {
    /**
     * This function is used to determine whether a node is activated or not
     * @param net This is sum of the weights by the inputs;
     * @return It will return a double value which will determine whether the function is activated or not.
     */
    public double activate(double net);
}
