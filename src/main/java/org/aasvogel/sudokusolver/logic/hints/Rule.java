package org.aasvogel.sudokusolver.logic.hints;

import org.aasvogel.sudokusolver.model.Page;

import java.util.Optional;

public interface Rule {
    Optional<Hint> check(Page page);
}
