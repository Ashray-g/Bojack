package autograd.autodiff;

import java.util.Optional;

public class Pow extends Operator {
    public static int valCall = 0;

    @Override
    public Term getDerivative(Term t1, Term t2, Variable variable) {

        if (t2 instanceof Constant) {
            return new Expression(
                    new Expression(
                            t2,
                            new Mul(),
                            new Expression(t1, new Pow(), new Constant(t2.getValue() - 1))),
                    new Mul(),
                    t1.getDerivative(variable));
        }

        Term coefficient = new Expression(new NatLog(t1), new Mul(), t2);
        return new Expression(
                coefficient.getDerivative(variable), new Mul(), new Expression(t1, new Pow(), t2));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        valCall++;
        double val = t1.getValue();
        if (val == 0) return 0;
        if (val == 1) return 1;

        return Math.pow(val, t2.getValue());
    }

    @Override
    public Optional<Term> simplify(Term t1, Term t2) {

        if (t2 instanceof Constant) {
            if (t2.getValue() == 0) return Optional.of(new Constant(1));
            if (t2.getValue() == 1) return Optional.of(t1);
        }

        if (t1 instanceof Constant && t1.getValue() == 1) return Optional.of(new Constant(1));

        return Optional.empty();
    }

    @Override
    public String toString() {
        return " ^ ";
    }
}
