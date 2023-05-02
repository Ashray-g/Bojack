package autograd.opt;

import autograd.autodiff.*;
import java.util.Arrays;

public class SimpleGradientDecent extends Solver {

    private final double[] guess;
    private final int max_iter;

    private final Jacobian gradient;

    private double epsilon = 1E-3;

    private double k_factor = 50;

    public SimpleGradientDecent(
            AlgebraSystem system, int iter_max, double[] initial_guess, double epsilon, double k_factor) {
        this(system, iter_max, initial_guess);
        this.epsilon = epsilon;
        this.k_factor = k_factor;
    }

    public SimpleGradientDecent(AlgebraSystem system, int iter_max, double[] initial_guess) {
        super(system);
        this.guess = initial_guess;
        this.max_iter = iter_max;

        if (initial_guess.length != system.getVariables().length) {
            throw new RuntimeException("Initial Guess Improper Size");
        }

        long time = System.currentTimeMillis();
        gradient = new Jacobian(system);
        System.out.println("Jacobian symbolic time: " + (System.currentTimeMillis() - time) + "ms");
    }

    private void applyGuess() {
        for (int i = 0; i < guess.length; i++) {
            system.getVariables()[i].setValue(guess[i]);
        }
    }

    @Override
    public double[] solve(boolean print) throws InterruptedException {
        long time = System.currentTimeMillis();
        long evalGrad = 0;

        int iter;
        for (iter = 0; iter < max_iter; iter++) {
            if (print) System.out.println("Guess: " + Arrays.toString(guess));
            applyGuess();

            long time1 = System.currentTimeMillis();
            double[][] evaluation = gradient.evaluateAtPoint();
            evalGrad += System.currentTimeMillis() - time1;

            if (print) System.out.println("Eval: " + Arrays.deepToString(evaluation));

            double maxSilon = 0;
            for (int i = 0; i < evaluation[0].length; i++)
                maxSilon = Math.max(maxSilon, Math.abs(evaluation[0][i]));
            if (maxSilon < epsilon) break;

            if (print) {
                System.out.println("Grad: " + Arrays.deepToString(evaluation));
                System.out.println("Cost: " + system.getFunctions()[0].getValue());
            }

            for (int i = 0; i < guess.length; i++) {
                guess[i] += -evaluation[0][i] / 70;
            }

            if (print) System.out.println();
        }

        System.out.println("Total iteration time: " + (System.currentTimeMillis() - time) + "ms");
        System.out.println("↳ Evaluate gradient: " + evalGrad + "ms");
        System.out.println("  ↳ Add calls: " + Add.valCall);
        System.out.println("  ↳ Sub calls: " + Sub.valCall);
        System.out.println("  ↳ Div calls: " + Div.valCall);
        System.out.println("  ↳ Mul calls: " + Mul.valCall);
        System.out.println("  ↳ Sin calls: " + Sin.valCall);
        System.out.println("  ↳ Cos calls: " + Cos.valCall);
        System.out.println("  ↳ Pow calls: " + Pow.valCall);
        System.out.println("  ↳ Neg calls: " + Negative.valCall);
        System.out.println("  ↳ Ln_ calls: " + NatLog.valCall);
        System.out.println("Iterations: " + iter);

        return guess;
    }
}
