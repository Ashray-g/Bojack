package autograd.autodiff;

public class Constant extends Term {

    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public Term getDerivative(Variable variable) {
        return new Constant(0);
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return " " + value + " ";
    }
}
