package autodiff;

import autograd.autodiff.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DerivativeTest {
    @Test
    public void x_times_y() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression expression = new Expression(x, new Mul(), y);

        x.setValue(0);
        y.setValue(0);
        printInfo(expression, x, y);
    }

    @Test
    public void a_time_b_plus_c() {
        Variable a = new Variable("a");
        Variable b = new Variable("b");
        Variable c = new Variable("c");

        Expression expression = new Expression(new Expression(a, new Mul(), b), new Add(), c);

        a.setValue(1);
        b.setValue(1);
        c.setValue(1);

        printInfo(expression, a, b, c);
    }

    @Test
    public void x_squared_times_y_squared() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression expression =
                new Expression(
                        new Expression(x, new Pow(), new Constant(2)),
                        new Mul(),
                        new Expression(y, new Pow(), new Constant(2)));

        x.setValue(0);
        y.setValue(0);
        printInfo(expression, x, y);
    }

    @Test
    public void cos_x_times_sin_y() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression expression = new Expression(new Cos(x), new Mul(), new Sin(y));

        x.setValue(0);
        y.setValue(0);
        printInfo(expression, x, y);
    }

    @Test
    public void ln_x() {
        Variable x = new Variable("x");
        Term d = new NatLog(x);
        x.setValue(0);
        printInfo(d, x);
    }

    public void printInfo(Term expression, Variable... vars) {

        System.out.println("-".repeat(100));

        String function = "f(" + vars[0].toString().trim();
        String evalPoint = vars[0].toString().trim() + " = " + vars[0].getValue();
        String gradient = "[" + expression.getDerivative(vars[0]).getValue();
        for (int i = 1; i < vars.length; i++) {
            function += ", " + vars[i].toString().trim();
            evalPoint += ", " + (vars[i].toString().trim() + " = " + vars[i].getValue());
            gradient += ", " + expression.getDerivative(vars[i]).getValue();
        }
        function += ")";
        gradient += "]";
        System.out.println("Expression: ");
        System.out.println(function + " = " + expression);
        System.out.println("\nDerivatives:");
        for (Variable var : vars) {
            System.out.println(
                    "∂"
                            + function
                            + "/∂"
                            + var.toString().trim()
                            + " = "
                            + expression.getDerivative(var));
        }

        System.out.println("\n∇" + function + " at (" + evalPoint + "):");
        System.out.println(gradient);

        System.out.println("-".repeat(100));
    }
}
