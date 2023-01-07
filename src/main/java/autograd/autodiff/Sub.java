package autograd.autodiff;

import java.util.Optional;

public class Sub extends Operator {
    public static int valCall = 0;
    @Override
    public String toString() {
        return " - ";
    }

    @Override
    public Term getDerivative(Term t1, Term t2, Variable variable) {
        return new Expression(t1.getDerivative(variable), this, t2.getDerivative(variable));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        valCall++;
        return t1.getValue() - t2.getValue();
    }

    @Override
    public Optional<Term> simplify(Term t1, Term t2) {

        if(t1 instanceof Constant || t2 instanceof Constant){
            if(t1.getValue() == 0) return Optional.of(new Negative(t2));
            if(t2.getValue() == 0) return Optional.of(t1);
        }

        return Optional.empty();
    }
}
