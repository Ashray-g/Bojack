package autograd.autodiff;

import autograd.math.Matrix;

public class Hessian {
    private final Matrix matrix;
    private final AlgebraSystem algebraSystem;
    private final int functions, variables;

    public Hessian(AlgebraSystem algebraSystem) {

        this.functions = algebraSystem.getFunctions().length;
        this.variables = algebraSystem.getVariables().length;
        this.algebraSystem = algebraSystem;

        Jacobian jacobian = new Jacobian(algebraSystem);
        Matrix jacobianMat = jacobian.getMatrix();

        matrix = new Matrix(functions, variables);

        for (int i = 0; i < functions; i++) {
            for (int j = 0; j < variables; j++) {
                matrix.getMatrix()[i][j] =
                        jacobianMat.getMatrix()[i][j].getDerivative(
                                algebraSystem.getVariables()[j]);
            }
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public double[][] evaluateAtPoint() {

        double[][] second_order_gradient = new double[functions][variables];

        for (int i = 0; i < functions; i++) {
            for (int j = 0; j < variables; j++) {
                second_order_gradient[i][j] = matrix.getMatrix()[i][j].getValue();
            }
        }

        return second_order_gradient;
    }

    @Override
    public String toString() {
        return matrix.toString();
    }
}
