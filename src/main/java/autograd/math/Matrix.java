package autograd.math;

import autograd.autodiff.Add;
import autograd.autodiff.Expression;
import autograd.autodiff.Mul;
import autograd.autodiff.Term;
import java.util.Arrays;

public class Matrix {
    private Term[][] matrix;
    private int rows;
    private int cols;

    public Matrix(int rows, int cols) {
        this.cols = cols;
        this.rows = rows;
        this.matrix = new Term[rows][cols];
    }

    public Term[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Term[][] arr){
        this.matrix = arr;
        this.rows = arr.length;
        this.cols = arr[0].length;
    }

    public Matrix times(Matrix other) {
        if (this.cols != other.rows)
            throw new RuntimeException("Matrix size mismatch in multiplication");
        Matrix result = new Matrix(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                result.getMatrix()[i][j] =
                        new Expression(this.matrix[i][0], new Mul(), other.getMatrix()[0][j]);
                for (int q = 1; q < rows; q++) {
                    result.getMatrix()[i][j] =
                            new Expression(
                                    result.getMatrix()[i][j],
                                    new Add(),
                                    new Expression(
                                            this.matrix[i][q], new Mul(), other.getMatrix()[q][j]));
                }
            }
        }
        return result;
    }

    public double[][] eval(){
        double[][] eval = new double[rows][cols];
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<cols;j++){
                eval[i][j] = matrix[i][j].getValue();
            }
        }
        return eval;
    }

    @Override
    public String toString() {
        String ret = "[";
        for (int i = 0; i < matrix.length; i++) {
            if (i != 0) ret += " ";
            ret += Arrays.toString(matrix[i]);
            if (i != matrix.length - 1) ret += ",\n";
        }
        ret += "]";
        return ret;
    }
}
