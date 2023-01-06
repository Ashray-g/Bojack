package autograd.autodiff;

public class Negative extends Function{

    private final Term t1;

    public Negative(Term t1) {
        this.t1 = t1;
    }

    @Override
    public Term getDerivative(Variable variable) {
        return new Negative(t1.getDerivative(variable));
    }

    @Override
    public double getValue() {
        return -t1.getValue();
    }

    @Override
    public String toString() {
        return " -(" + t1 + ") ";
    }
}
