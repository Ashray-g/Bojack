package autograd.opt;

import autograd.autodiff.AlgebraSystem;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        SampleAlgebraSystems systems = new SampleAlgebraSystems();

        AlgebraSystem system = systems.sinx_times_x2_plus_y2;
        //        AlgebraSystem system = systems.x2_plus_y2;
        //        AlgebraSystem system = systems.cosx_timse_siny;

        double[] guess = {-1, 1};

        SimpleGradientDecent solver = new SimpleGradientDecent(system, 1_000_000, guess);
        double[] solution = solver.solve(false);

        System.out.println("Solution: " + Arrays.toString(solution));
        System.out.println("Objective function: " + system.getFunctions()[0].getValue());
    }
}
