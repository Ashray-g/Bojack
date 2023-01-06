package autograd.autodiff;

import java.util.Optional;

public class Variable extends Term {
    private final String name;
    private Optional<Double> value;

    public Variable(String name) {
        this.name = name;
        value = Optional.empty();
    }

    @Override
    public String toString() {
        return " " + name + ' ';
    }

    public Term setValue(double value) {
        this.value = Optional.of(value);
        return this;
    }

    public double getValue() {
        if (value.isPresent()) return value.get();
        else {
            throw new RuntimeException("Variable value not set in evaluation");
        }
    }

    @Override
    public Term getDerivative(Variable variable) {
        return this.equals(variable) ? new Constant(1) : new Constant(0);
    }
}
