package autograd.autodiff;

public class AlgebraSystem {
    private final Term[] functions;
    private final Variable[] variables;

    public AlgebraSystem(Term[] functions, Variable[] variables) {
        this.functions = functions;
        this.variables = variables;
    }

    public Term[] getFunctions() {
        return functions;
    }

    public Variable[] getVariables() {
        return variables;
    }
}
