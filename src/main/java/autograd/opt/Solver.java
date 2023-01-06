package autograd.opt;

import autograd.autodiff.AlgebraSystem;

public abstract class Solver {
    public AlgebraSystem system;

    public Solver(AlgebraSystem system) {
        this.system = system;
    }

    public abstract double[] solve(boolean print) throws InterruptedException;
}
