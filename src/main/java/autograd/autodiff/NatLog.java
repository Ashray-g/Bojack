package autograd.autodiff;

public class NatLog extends Function {
    private final Term t1;

    public NatLog(Term t1) {
        this.t1 = t1;
    }

    @Override
    public Term getDerivative(Variable variable) {
        return new Expression(t1.getDerivative(variable), new Div(), t1);
    }

    @Override
    public double getValue() {
        return Math.log(t1.getValue());
    }

    @Override
    public String toString() {
        return " ln(" + t1 + ") ";
    }
}
