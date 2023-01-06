package autograd.autodiff;

public class Expression extends Term {

    private final Term t1, t2;
    private final Operator operator;

    public Expression(Term t1, Operator operator, Term t2) {
        this.t1 = t1;
        this.t2 = t2;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "(" + t1 + ") " + operator + " (" + t2 + ")";
    }

    @Override
    public Term getDerivative(Variable variable) {
        if(t1 instanceof Constant && t2 instanceof Constant) return new Constant(0);
        return operator.getDerivative(t1, t2, variable);
    }

    @Override
    public double getValue() {
        return operator.getVal(t1, t2);
    }
}
