package autograd.autodiff;

public class Pow extends Operator {
    @Override
    public Expression getDerivative(Term t1, Term t2, Variable variable) {

        if(t2 instanceof Constant){
            return new Expression(new Expression(t2, new Mul(), new Expression(t1, new Pow(), new Constant(t2.getValue()-1))), new Mul(), t1.getDerivative(variable));
        }

        Term coefficient = new Expression(new NatLog(t1), new Mul(), t2);
        return new Expression(
                coefficient.getDerivative(variable), new Mul(), new Expression(t1, new Pow(), t2));
    }

    @Override
    public double getVal(Term t1, Term t2) {
        return Math.pow(t1.getValue(), t2.getValue());
    }

    @Override
    public String toString() {
        return " ^ ";
    }
}
