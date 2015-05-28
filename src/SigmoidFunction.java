/**
 * <h1>The Sigmoid Function</h1>
 * This class implements the abstract interface ActivationFunction. It applies on function on the net sum of inputs and weights.
 */
public class SigmoidFunction implements ActivationFunction {
    /**
     * The function applied on the net sum of inputs of weights allows for a more linear style of regression versus the step function.
     *
     * @param net This is sum of the weights by the inputs
     * @return This returns a double that has the sigmoid function applied to it.
     */
    @Override
    public double activate(double net) {
        return (1 / (1 + Math.exp(-net)));
    }
}
