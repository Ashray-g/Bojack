package autograd.autodiff;

import java.util.Optional;

public abstract class Operator {

    public abstract Term getDerivative(Term t1, Term t2, Variable variable);

    public abstract double getVal(Term t1, Term t2);

    public abstract Optional<Term> simplify(Term t1, Term t2);
}
