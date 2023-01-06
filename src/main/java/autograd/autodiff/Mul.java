package autograd.autodiff;

public class Mul extends Operator {
    @Override
    public String toString() {
        return " * ";
    }

    @Override
    public Expression getDerivative(Term t1, Term t2, Variable variable) {
        if(t1 instanceof Constant) return new Expression(t1, new Mul(), t2.getDerivative(variable));
        if(t2 instanceof Constant) return new Expression(t2, new Mul(), t1.getDerivative(variable));
        return new Expression(
                new Expression(t1, new Mul(), t2.getDerivative(variable)),
                new Add(),
                new Expression(t1.getDerivative(variable), new Mul(), t2));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        double val1 = t1.getValue();
        if (val1 == 0) return 0;

        double val2 = t2.getValue();
        if (val2 == 0) return 0;

        return val1 * val2;
    }
}