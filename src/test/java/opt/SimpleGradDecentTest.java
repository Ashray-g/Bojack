package opt;

import autograd.autodiff.AlgebraSystem;
import autograd.opt.SampleAlgebraSystems;
import autograd.opt.SimpleGradientDecent;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimpleGradDecentTest {
    @Test
    public void x2_times_y2() throws InterruptedException {
        SampleAlgebraSystems systems = new SampleAlgebraSystems();

        AlgebraSystem system = systems.x2_plus_y2;

        double[] guess = {10, 10};

        SimpleGradientDecent solver = new SimpleGradientDecent(system, 1_000_000, guess);
        double[] solution = solver.solve(false);

        System.out.println("Solution: " + Arrays.toString(solution));
        System.out.println("Objective function: " + system.getFunctions()[0].getValue());

        Assert.assertEquals(0, system.getFunctions()[0].getValue(), 1E-2);
    }

    @Test
    public void cosx_timse_siny() throws InterruptedException {
        SampleAlgebraSystems systems = new SampleAlgebraSystems();

        AlgebraSystem system = systems.cosx_timse_siny;

        double[] guess = {10, 10};

        SimpleGradientDecent solver = new SimpleGradientDecent(system, 100_000, guess);
        double[] solution = solver.solve(false);

        System.out.println("Solution: " + Arrays.toString(solution));
        System.out.println("Objective function: " + system.getFunctions()[0].getValue());

        Assert.assertEquals(-1, system.getFunctions()[0].getValue(), 1E-2);
    }
}
