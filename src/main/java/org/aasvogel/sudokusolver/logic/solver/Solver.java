package org.aasvogel.sudokusolver.logic.solver;

import org.aasvogel.sudokusolver.model.Page;

import java.util.Set;

public interface Solver {
    public Set<Page> getSollutions( Page startingSituation);
}
