package autograd.autodiff;

public abstract class Term {
    public abstract Term getDerivative(Variable variable);

    public abstract double getValue();
}
