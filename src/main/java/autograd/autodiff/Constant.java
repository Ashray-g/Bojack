package autograd.autodiff;

import java.util.Optional;

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
    public Optional<Term> simplify() {
        return Optional.of(this);
    }

    @Override
    public String toString() {
        return " " + value + " ";
    }
}
