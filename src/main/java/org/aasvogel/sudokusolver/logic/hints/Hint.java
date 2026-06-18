package org.aasvogel.sudokusolver.logic.hints;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.model.Region;
import org.aasvogel.sudokusolver.model.Symbol;

import java.util.Optional;
import java.util.Set;

public class Hint {
    private CellCoordinates targetCell;
    private Set<CellCoordinates> includedCells;
    private Set<Region> includedRegions;
    private Symbol symbol;
    private WeightedRule rule;

    static Optional<Hint> withoutInclusions(CellCoordinates coordinates, Symbol symbol, WeightedRule rule){
        Hint hint = new Hint();
        hint.setTargetCell( coordinates);
        hint.setSymbol( symbol);
        hint.setRule( rule);
        return Optional.of( hint);
    }

    public CellCoordinates getTargetCell() {
        return targetCell;
    }

    public void setTargetCell(CellCoordinates targetCell) {
        this.targetCell = targetCell;
    }

    public Set<CellCoordinates> getIncludedCells() {
        return includedCells;
    }

    public void setIncludedCells(Set<CellCoordinates> includedCells) {
        this.includedCells = includedCells;
    }

    public Set<Region> getIncludedRegions() {
        return includedRegions;
    }

    public void setIncludedRegions(Set<Region> includedRegions) {
        this.includedRegions = includedRegions;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public WeightedRule getRule() {
        return rule;
    }

    public void setRule(WeightedRule rule) {
        this.rule = rule;
    }
}
