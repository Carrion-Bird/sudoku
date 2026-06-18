package org.aasvogel.sudokusolver.logic.validity;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.model.Region;
import org.aasvogel.sudokusolver.model.Symbol;

import java.util.Collection;
import java.util.Set;

public class ValidityResult {

    private final ResultType type;
    private Set<CellCoordinates> cellsInvolved = Set.of();
    private Region regionInvolved;
    private Symbol symbolInvolved;

    static ValidityResult valid() {
        return new ValidityResult(ResultType.VALID);
    }

    static ValidityResult invalid(Collection<CellCoordinates> cellsInvolved, Region regionInvolved,
                                  Symbol symbolInvolved) {
        ValidityResult result = new ValidityResult(ResultType.INVALID);
        result.cellsInvolved = Set.copyOf(cellsInvolved);
        result.regionInvolved = regionInvolved;
        result.symbolInvolved = symbolInvolved;
        return result;
    }

    private ValidityResult(ResultType type) {
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
