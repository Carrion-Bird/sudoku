package org.aasvogel.sudokusolver.logic;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.model.Region;
import org.aasvogel.sudokusolver.model.Symbol;

import java.util.Collection;
import java.util.Set;

public class CheckResult {

    private final ResultType type;
    private Set<CellCoordinates> cellsInvolved;
    private Region regionInvolved;
    private Symbol symbolInvolved;

    static CheckResult valid() {
        return new CheckResult(ResultType.VALID);
    }

    static CheckResult invalid(Collection<CellCoordinates> cellsInvolved, Region regionInvolved,
                               Symbol symbolInvolved) {
        CheckResult result = new CheckResult(ResultType.INVALID);
        result.cellsInvolved = Set.copyOf(cellsInvolved);
        result.regionInvolved = regionInvolved;
        result.symbolInvolved = symbolInvolved;
        return result;
    }

    private CheckResult(ResultType type) {
        this.type = type;
    }

    public Set<CellCoordinates> getCellsInvolved() {
        return cellsInvolved;
    }

    public ResultType getType() {
        return type;
    }

    public Region getRegionInvolved() {
        return regionInvolved;
    }

    public Symbol getSymbolInvolved() {
        return symbolInvolved;
    }

    public enum ResultType {VALID, INVALID}
}
