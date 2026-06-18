package org.aasvogel.sudokusolver.view.resultFormater;

import org.aasvogel.sudokusolver.logic.hints.Hint;

import java.util.Optional;

public class HintFormatter {

    public static String format(Optional<Hint> hint) {
        return hint.map(HintFormatter::formatHint).orElseGet(HintFormatter::formatNoHint);
    }

    private static String formatNoHint() {
        return "No hint found.";
    }

    private static String formatHint(Hint hint) {
        return "Rule "+ hint.getRule().getClass().getName() + "  has found something:\n"
                + "Cell: " + hint.getTargetCell() + " Symbol: " + hint.getSymbol().getRepresentation()
                + "\nDifficulty: " + hint.getRule().getDifficulty();
    }

    private HintFormatter() {
    }
}
