package math;

import autograd.autodiff.Variable;
import autograd.math.Matrix;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class MatrixTest {
    @Test
    public void constants_matrix() {
        Matrix mat = new Matrix(2, 2);
        mat.getMatrix()[0][0] = new Variable("a").setValue(1);
        mat.getMatrix()[0][1] = new Variable("b").setValue(2);
        mat.getMatrix()[1][0] = new Variable("c").setValue(3);
        mat.getMatrix()[1][1] = new Variable("d").setValue(4);

        System.out.println(mat);

        Matrix mat2 = new Matrix(2, 1);
        mat2.getMatrix()[0][0] = new Variable("e").setValue(5);
        mat2.getMatrix()[1][0] = new Variable("f").setValue(6);

        System.out.println(mat2);

        Matrix mul = mat.times(mat2);

        System.out.println(mul);

        Assert.assertEquals(mul.getMatrix()[0][0].getValue(), 17.0, 0);
        Assert.assertEquals(mul.getMatrix()[1][0].getValue(), 39.0, 0);
    }
}
