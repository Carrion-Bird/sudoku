package org.aasvogel.sudokusolver.logic.hints;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OnlyPlaceToPutThisSymbol implements WeightedRule{


    @Override
    public int getDifficulty() {
        return 1;
    }

    @Override
    public Optional<Hint> check(Page page) {
        PageNavigator navigator = new PageNavigator(page);
        for (Region region : navigator.getAllRegions()) {
            for (Symbol symbol : Configuration.getAllSymbols()) {
                Set<CellCoordinates> possibleCells = getPossibleCells( region, symbol, navigator);
                if (possibleCells.size() == 1) {
                    Hint hint = Hint.withoutInclusions(possibleCells.iterator().next(), symbol, this);
                    hint.setIncludedRegions( Set.of(region));
                    return Optional.of( hint);
                }
            }
        }

        return Optional.empty();
    }

    private Set<CellCoordinates> getPossibleCells(Region region, Symbol symbol, PageNavigator navigator) {

        Set<CellCoordinates> possibilities = new HashSet<>();
        for (Cell cell : region.getCells()) {
            if (isCellPossible(cell, symbol, navigator))
            {
                possibilities.add( cell.getCoordinates());
            }
        }

        return possibilities;
    }

    private boolean isCellPossible(Cell cell, Symbol symbol, PageNavigator navigator) {
        if (cell.getValue().isPresent())
            return false;

        return navigator.getRegionsOfCell(cell).stream()//
                .map(Region::getCells)
                .flatMap(Collection::stream)
                .map(Cell::getValue)
                .filter( Optional::isPresent)
                .map(Optional::get)
                .noneMatch(symbol::equals);
    }
}
