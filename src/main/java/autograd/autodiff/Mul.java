package autograd.autodiff;

import java.util.Optional;

public class Mul extends Operator {
    public static int valCall = 0;
    @Override
    public String toString() {
        return " * ";
    }

    @Override
    public Term getDerivative(Term t1, Term t2, Variable variable) {
        if(t1 instanceof Constant) return new Expression(t1, new Mul(), t2.getDerivative(variable));
        if(t2 instanceof Constant) return new Expression(t2, new Mul(), t1.getDerivative(variable));
        return new Expression(
                new Expression(t1, new Mul(), t2.getDerivative(variable)),
                new Add(),
                new Expression(t1.getDerivative(variable), new Mul(), t2));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        valCall++;
        double val1 = t1.getValue();
        if (val1 == 0) return 0;

        double val2 = t2.getValue();
        if (val2 == 0) return 0;

        return val1 * val2;
    }

    @Override
    public Optional<Term> simplify(Term t1, Term t2) {

        if(t1 instanceof Constant && t1.getValue() == 0) return Optional.of(new Constant(0));
        if(t2 instanceof Constant && t2.getValue() == 0) return Optional.of(new Constant(0));

        return Optional.empty();
    }
}
