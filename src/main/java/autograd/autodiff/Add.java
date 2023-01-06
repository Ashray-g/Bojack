package autograd.autodiff;

public class Add extends Operator {
    @Override
    public String toString() {
        return " + ";
    }

    @Override
    public Expression getDerivative(Term t1, Term t2, Variable variable) {
        return new Expression(t1.getDerivative(variable), this, t2.getDerivative(variable));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        return t1.getValue() + t2.getValue();
    }
}
