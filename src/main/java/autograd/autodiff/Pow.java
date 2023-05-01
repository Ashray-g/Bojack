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
        return Math.pow(t1.getValue(), t2.getValue());
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
