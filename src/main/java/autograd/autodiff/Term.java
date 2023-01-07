package autograd.autodiff;

import java.util.Optional;

public abstract class Term {
    public abstract Term getDerivative(Variable variable);

    public abstract double getValue();

    public abstract Optional<Term> simplify();
}
