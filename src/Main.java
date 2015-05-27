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
    public static void main(String[] args) {
        BackPropagationAlgorithm algorithm = new BackPropagationAlgorithm(new SigmoidFunction());
        try {
            algorithm.run(26, "doc.txt", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
