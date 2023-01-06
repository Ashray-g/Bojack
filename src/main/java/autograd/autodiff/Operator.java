package autograd.autodiff;

public abstract class Operator {

    public abstract Expression getDerivative(Term t1, Term t2, Variable variable);

    public abstract double getVal(Term t1, Term t2);
}