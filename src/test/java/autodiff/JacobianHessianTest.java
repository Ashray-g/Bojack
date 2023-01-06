package autodiff;

import autograd.autodiff.*;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class JacobianHessianTest {
    @Test
    public void x_times_y_and_x_sq_plus_y_sq() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression expression1 = new Expression(x, new Mul(), y);
        Expression expression2 =
                new Expression(
                        new Expression(x, new Pow(), new Constant(2)),
                        new Mul(),
                        new Expression(y, new Pow(), new Constant(2)));

        AlgebraSystem algebraSystem =
                new AlgebraSystem(new Term[] {expression1, expression2}, new Variable[] {x, y});

        x.setValue(1);
        y.setValue(1);

        printResults(algebraSystem);
    }

    @Test
    public void cosx_times_siny() {
        Variable x = new Variable("x");
        Variable y = new Variable("y");

        Expression cosx_times_siny_expression = new Expression(new Cos(x), new Mul(), new Sin(y));

        AlgebraSystem algebraSystem =
                new AlgebraSystem(new Term[] {cosx_times_siny_expression}, new Variable[] {x, y});

        x.setValue(0);
        y.setValue(0);

        printResults(algebraSystem);
    }

    public void printMatrix(double[][] matrix) {
        System.out.print("[");
        for (int i = 0; i < matrix.length; i++) {
            if (i != 0) System.out.print(" ");
            System.out.print(Arrays.toString(matrix[i]));
            if (i != matrix.length - 1) System.out.println(",");
        }
        System.out.println("]");
    }

    public void printResults(AlgebraSystem system) {
        System.out.println("-".repeat(100));

        String functionName = "f(" + system.getVariables()[0].toString().trim();
        String evalPoint =
                system.getVariables()[0].toString().trim()
                        + " = "
                        + system.getVariables()[0].getValue();
        for (int i = 1; i < system.getVariables().length; i++) {
            functionName += ", " + system.getVariables()[i].toString().trim();
            evalPoint +=
                    ", "
                            + (system.getVariables()[i].toString().trim()
                                    + " = "
                                    + system.getVariables()[i].getValue());
        }
        functionName += ")";
        System.out.println("Expression: ");
        for (Term function : system.getFunctions()) {
            System.out.println(functionName + " = " + function);
        }

        Jacobian jacobian = new Jacobian(system);

        System.out.println("\nJacobian Matrix: ");
        System.out.println(jacobian);

        System.out.println("\nJacobian evaluated at (" + evalPoint + "):");
        printMatrix(jacobian.evaluateAtPoint());

        Hessian hessian = new Hessian(system);

        System.out.println("\nHessian Matrix: ");
        System.out.println(hessian);

        System.out.println("\nHessian evaluated at (" + evalPoint + "):");
        printMatrix(hessian.evaluateAtPoint());

        System.out.println("-".repeat(100));
    }
}
