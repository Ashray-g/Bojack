package autograd.opt;

import autograd.autodiff.AlgebraSystem;
import autograd.autodiff.Jacobian;
import java.util.Arrays;

public class SimpleGradientDecent extends Solver {

    private final double[] guess;
    private final int max_iter;

    private final Jacobian gradient;

    public SimpleGradientDecent(AlgebraSystem system, int iter_max, double[] initial_guess) {
        super(system);
        this.guess = initial_guess;
        this.max_iter = iter_max;

        if (initial_guess.length != system.getVariables().length) {
            throw new RuntimeException("Initial Guess Improper Size");
        }

        gradient = new Jacobian(system);
//        System.out.println(system.getFunctions()[0]);
//        System.out.println(gradient);
    }

    private void applyGuess() {
        for (int i = 0; i < guess.length; i++) {
            system.getVariables()[i].setValue(guess[i]);
        }
    }

    @Override
    public double[] solve(boolean print) throws InterruptedException {
        long time = System.currentTimeMillis();

        int iter;
        for (iter = 0; iter < max_iter; iter++) {
            if (print)System.out.println("Guess: " + Arrays.toString(guess));
            applyGuess();

            double[][] evaluation = gradient.evaluateAtPoint();
            if (print)System.out.println("Eval: " + Arrays.deepToString(evaluation));

            double maxSilon = 0;
            for (int i = 0; i < evaluation[0].length; i++)
                maxSilon = Math.max(maxSilon, Math.abs(evaluation[0][i]));
            if (maxSilon < 1E-3) break;

            if (print) {
                System.out.println("Grad: " + Arrays.deepToString(evaluation));
                System.out.println("Cost: " + system.getFunctions()[0].getValue());
            }

            for (int i = 0; i < guess.length; i++) {
                guess[i] += -evaluation[0][i] / 50;
            }

            if (print) System.out.println();
        }
        System.out.println("Time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println("Iterations: " + iter);

        return guess;
    }
}
