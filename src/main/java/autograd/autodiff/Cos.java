package autograd.autodiff;

import java.util.Optional;

public class Cos extends Function {
    private final Term t1;
    public static int valCall = 0;

    public Cos(Term t1) {
        this.t1 = t1;
    }

    @Override
    public Term getDerivative(Variable variable) {
        return new Expression(
                new Negative(new Sin(t1)),
                new Mul(),
                t1.getDerivative(variable));
    }

    @Override
    public double getValue() {
        valCall++;
        return Math.cos(t1.getValue());
    }

    @Override
    public Optional<Term> simplify() {
        return Optional.of(this);
    }

    @Override
    public String toString() {
        return " cos(" + t1 + ") ";
    }
}
