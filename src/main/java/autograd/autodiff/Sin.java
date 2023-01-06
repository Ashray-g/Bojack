package autograd.autodiff;

public class Sin extends Function {
    private final Term t1;

    public Sin(Term t1) {
        this.t1 = t1;
    }

    @Override
    public Term getDerivative(Variable variable) {
        return new Expression(new Cos(t1), new Mul(), t1.getDerivative(variable));
    }

    @Override
    public double getValue() {
        return Math.sin(t1.getValue());
    }

    @Override
    public String toString() {
        return " sin(" + t1 + ") ";
    }
}
