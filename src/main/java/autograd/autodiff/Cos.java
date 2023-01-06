package autograd.autodiff;

public class Cos extends Function {
    private final Term t1;

    public Cos(Term t1) {
        this.t1 = t1;
    }

    @Override
    public Term getDerivative(Variable variable) {
        return new Expression(
                new Expression(new Constant(0), new Sub(), new Sin(t1)),
                new Mul(),
                t1.getDerivative(variable));
    }

    @Override
    public double getValue() {
        return Math.cos(t1.getValue());
    }

    @Override
    public String toString() {
        return " cos(" + t1 + ") ";
    }
}
