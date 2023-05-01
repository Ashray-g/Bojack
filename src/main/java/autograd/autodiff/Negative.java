package autograd.autodiff;

import java.util.Optional;

public class Negative extends Function {
    public static int valCall = 0;

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
        valCall++;
        return -t1.getValue();
    }

    @Override
    public Optional<Term> simplify() {
        return Optional.of(this);
    }

    @Override
    public String toString() {
        return " -(" + t1 + ") ";
    }
}
