package autograd.opt;

import autograd.autodiff.*;

public class SampleAlgebraSystems {
    public AlgebraSystem x2_plus_y2;
    public AlgebraSystem cosx_timse_siny;
    public AlgebraSystem sinx_times_x2_plus_y2;

    public SampleAlgebraSystems() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression x2_plus_y2_expression =
                new Expression(
                        new Expression(x, new Pow(), new Constant(2)),
                        new Add(),
                        new Expression(y, new Pow(), new Constant(2)));

        Expression cosx_times_siny_expression = new Expression(new Cos(x), new Mul(), new Sin(y));

        Expression complex_expression =
                new Expression(
                        new Expression(
                                new Sin(x),
                                new Mul(),
                                new Expression(x, new Pow(), new Constant(2))),
                        new Add(),
                        new Expression(y, new Pow(), new Constant(2)));

        x2_plus_y2 = new AlgebraSystem(new Term[] {x2_plus_y2_expression}, new Variable[] {x, y});

        sinx_times_x2_plus_y2 =
                new AlgebraSystem(new Term[] {complex_expression}, new Variable[] {x, y});

        cosx_timse_siny =
                new AlgebraSystem(new Term[] {cosx_times_siny_expression}, new Variable[] {x, y});
    }
}
