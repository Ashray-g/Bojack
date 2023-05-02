import autograd.autodiff.*;
import autograd.math.Matrix;
import autograd.opt.SimpleGradientDecent;
import java.util.Arrays;

public class ArmForwardK {
    public static void main(String[] args) throws InterruptedException {

        Variable[] angles = {
            new Variable("a1"),
            new Variable("a2"),
            new Variable("a3"),
            new Variable("a4"),
            new Variable("a5"),
            new Variable("a6")
        }; // Radians please

        Term[][] DHTable = {
            {new Constant(1), new Constant(0), new Constant(0), angles[0]},
            {new Constant(1), new Constant(0), new Constant(0), angles[1]},
            {new Constant(1), new Constant(0), new Constant(0), angles[2]},
            {new Constant(1), new Constant(0), new Constant(0), angles[3]},
            {new Constant(1), new Constant(0), new Constant(0), angles[4]},
            {new Constant(1), new Constant(0), new Constant(0), angles[5]},
        };

        Matrix[] links = new Matrix[6];

        for (int i = 0; i < 6; i++) {
            Term a = DHTable[i][0];
            Term alpha = DHTable[i][1];
            Term d = DHTable[i][2];
            Term theta = DHTable[i][3];

            Matrix link = new Matrix(4, 4);

            link.getMatrix()[0][0] = new Cos(theta);
            link.getMatrix()[0][1] =
                    new Expression(new Negative(new Sin(theta)), new Mul(), new Cos(alpha));
            link.getMatrix()[0][2] = new Expression(new Sin(theta), new Mul(), new Sin(alpha));
            link.getMatrix()[0][3] = new Expression(a, new Mul(), new Cos(theta));

            link.getMatrix()[1][0] = new Sin(theta);
            link.getMatrix()[1][1] = new Expression(new Cos(theta), new Mul(), new Cos(alpha));
            link.getMatrix()[1][2] =
                    new Expression(new Negative(new Cos(theta)), new Mul(), new Sin(alpha));
            link.getMatrix()[1][3] = new Expression(a, new Mul(), new Sin(theta));

            link.getMatrix()[2][0] = new Constant(0);
            link.getMatrix()[2][1] = new Sin(alpha);
            link.getMatrix()[2][2] = new Cos(alpha);
            link.getMatrix()[2][3] = d;

            link.getMatrix()[3][0] = new Constant(0);
            link.getMatrix()[3][1] = new Constant(0);
            link.getMatrix()[3][2] = new Constant(0);
            link.getMatrix()[3][3] = new Constant(1);

            links[i] = link;
        }

        Matrix mult = new Matrix(4, 4);
        mult.setMatrix(
                new Term[][] {
                    {new Constant(1), new Constant(0), new Constant(0), new Constant(0)},
                    {new Constant(0), new Constant(1), new Constant(0), new Constant(0)},
                    {new Constant(0), new Constant(0), new Constant(1), new Constant(0)},
                    {new Constant(0), new Constant(0), new Constant(0), new Constant(1)}
                });

        for (Matrix link : links) {
            mult = mult.times(link);
        }

        angles[0].setValue(0);
        angles[1].setValue(0);
        angles[2].setValue(0);
        angles[3].setValue(0);
        angles[4].setValue(0);
        angles[5].setValue(0);

        Term x_coord = mult.getMatrix()[0][3];
        Term y_coord = mult.getMatrix()[1][3];

        double goalX = 4;
        double goalY = 1;

        x_coord = x_coord.simplify().get();
        y_coord = y_coord.simplify().get();

        Expression costX =
                new Expression(
                        new Expression(x_coord, new Sub(), new Constant(goalX)),
                        new Pow(),
                        new Constant(2));
        Expression costY =
                new Expression(
                        new Expression(y_coord, new Sub(), new Constant(goalY)),
                        new Pow(),
                        new Constant(2));

        AlgebraSystem systemCost =
                new AlgebraSystem(new Term[] {new Expression(costX, new Add(), costY)}, angles);

        SimpleGradientDecent grad =
                new SimpleGradientDecent(
                        systemCost, 100_000, new double[] {Math.PI / 2, 0, 0, 0, 0, 0});

        double[] sol = grad.solve(false);

        System.out.println("Solution: " + Arrays.toString(sol));
        System.out.println("Goal: (" + goalX + ", " + goalY + ")");
        System.out.println("Opti: (" + x_coord.getValue() + ", " + y_coord.getValue() + ")");
    }
}
