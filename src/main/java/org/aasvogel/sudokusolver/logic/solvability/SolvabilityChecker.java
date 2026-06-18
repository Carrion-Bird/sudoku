package org.aasvogel.sudokusolver.logic.solvability;

import org.aasvogel.sudokusolver.logic.solver.Solver;
import org.aasvogel.sudokusolver.model.Page;

import java.util.Set;

public class SolvabilityChecker {
    private final Solver solver;

    public SolvabilityChecker(Solver solver) {
        this.solver = solver;
    }

    public SolvabilityResult isSolvable(Page pageToTest){
        Set<Page> solutions = solver.getSollutions(pageToTest);
        return SolvabilityResult.ofNumberSolutions( solutions.size());
    }
}
