package org.aasvogel.sudokusolver.view.resultFormater;

import org.aasvogel.sudokusolver.logic.solvability.SolvabilityResult;
import org.aasvogel.sudokusolver.logic.validity.ValidityResult;

public class SolvabilityResultFormater {
    public static String format(SolvabilityResult result) {
        return switch (result.getType()) {
            case UNSOLVABLE -> getTextUnsolvable();
            case SOLVABLE -> getTextSolvable();
            case NON_UNIQUE -> getTextNotUnique( result.getSolutionsNumber());
        };
    }

    private static String getTextUnsolvable() {
        return "Nope - Sorry.\nThis sudoku is NOT solvable!";
    }

    private static String getTextSolvable() {
        return "Yea it might!\nYou can do it! I believe in you!";
    }

    private static String getTextNotUnique(int solutionsNumber) {
        return "Not unique!\nCheck if you entered everything!";
    }
}
