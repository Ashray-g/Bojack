package autograd.autodiff;

import java.util.Optional;

public class NatLog extends Function {
    public static int valCall = 0;
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
        valCall++;
        return Math.log(t1.getValue());
    }

    @Override
    public Optional<Term> simplify() {
        return Optional.of(this);
    }

    @Override
    public String toString() {
        return " ln(" + t1 + ") ";
    }
}
