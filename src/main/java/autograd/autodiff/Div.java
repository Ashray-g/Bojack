package autograd.autodiff;

public class Div extends Operator {
    @Override
    public String toString() {
        return " / ";
    }

    @Override
    public Expression getDerivative(Term t1, Term t2, Variable variable) {
        return new Expression(
                new Expression(
                        new Expression(t1.getDerivative(variable), new Mul(), t2),
                        new Sub(),
                        new Expression(t2.getDerivative(variable), new Mul(), t1)),
                new Div(),
                new Expression(t1, new Pow(), new Constant(2)));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        double val1 = t1.getValue();
        double val2 = t2.getValue();
        if (val1 == 0) return 0;

        return val1 / val2;
    }
}
