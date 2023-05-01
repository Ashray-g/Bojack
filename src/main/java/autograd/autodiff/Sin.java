package autograd.autodiff;

import java.util.Optional;

public class Sin extends Function {
    public static int valCall = 0;
    private final Term t1;

    public Sin(Term t1) {
        this.t1 = t1;
    }

    @Override
    public Term getDerivative(Variable variable) {
        if (t1 instanceof Constant) return new Constant(0);
        return new Expression(new Cos(t1), new Mul(), t1.getDerivative(variable));
    }

    @Override
    public double getValue() {
        valCall++;
        return Math.sin(t1.getValue());
    }

    @Override
    public Optional<Term> simplify() {
        return Optional.of(this);
    }

    @Override
    public String toString() {
        return " sin(" + t1 + ") ";
    }
}
