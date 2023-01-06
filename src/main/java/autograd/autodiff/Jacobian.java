package autograd.autodiff;

import autograd.math.Matrix;

public class Jacobian {
    private final Matrix matrix;
    private final AlgebraSystem algebraSystem;
    private final int functions, variables;

    public Jacobian(AlgebraSystem algebraSystem) {
        this.functions = algebraSystem.getFunctions().length;
        this.variables = algebraSystem.getVariables().length;
        this.algebraSystem = algebraSystem;
        matrix = new Matrix(functions, variables);

        int func = 0;
        int var;

        for (Term fuction : algebraSystem.getFunctions()) {
            var = 0;
            for (Variable variable : algebraSystem.getVariables()) {
                matrix.getMatrix()[func][var] = fuction.getDerivative(variable);
                var++;
            }
            func++;
        }
    }

    public Matrix getMatrix() {
        return matrix;
    }

    public double[][] evaluateAtPoint() {

        double[][] gradient = new double[functions][variables];

        for (int i = 0; i < functions; i++) {
            for (int j = 0; j < variables; j++) {
                gradient[i][j] = matrix.getMatrix()[i][j].getValue();
            }
        }

        return gradient;
    }

    @Override
    public String toString() {
        return matrix.toString();
    }
}
