package org.aasvogel.sudokusolver.logic.solvability;

public class SolvabilityResult {
    private final Solvable type;
    private final int solutionsNumber;

    public static SolvabilityResult ofNumberSolutions(int numberOfSolutions) {

        Solvable type =
                switch (numberOfSolutions) {
                    case 0 -> SolvabilityResult.Solvable.UNSOLVABLE;
                    case 1 -> SolvabilityResult.Solvable.SOLVABLE;
                    default -> SolvabilityResult.Solvable.NON_UNIQUE;
                };

        return new SolvabilityResult(type, numberOfSolutions);
    }

    private SolvabilityResult(Solvable type, int solutionsNumber) {
        this.type = type;
        this.solutionsNumber = solutionsNumber;
    }

    public Solvable getType() {
        return type;
    }

    public int getSolutionsNumber() {
        return solutionsNumber;
    }

    public enum Solvable {UNSOLVABLE, SOLVABLE, NON_UNIQUE}
}
