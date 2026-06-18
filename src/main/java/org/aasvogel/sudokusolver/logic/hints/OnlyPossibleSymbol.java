package org.aasvogel.sudokusolver.logic.hints;

import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.model.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Looks for positions in which all but one Symbol are not possible.
 */
public class OnlyPossibleSymbol implements WeightedRule {

    @Override
    public Optional<Hint> check(Page page) {

        PageNavigator navigator = new PageNavigator(page);

        for (Cell cell : navigator.getCells()) {
            if (cell.getValue().isEmpty()) {
                Set<Symbol> possibleSymbols = checkPossibleValuesForCell(cell, navigator);
                if (possibleSymbols.isEmpty()) {
                    throw new IllegalStateException("Unsolvable!");
                } else if (possibleSymbols.size() == 1) {

                    return Hint.withoutInclusions(cell.getCoordinates(),
                            possibleSymbols.iterator().next(), this);
                }
            }
        }

        return Optional.empty();
    }

    private Set<Symbol> checkPossibleValuesForCell(Cell cell, PageNavigator navigator) {
        Set<Symbol> possibilities = new HashSet<>(Configuration.getAllSymbols());

        navigator.getRegionsOfCell(cell).stream() //
                .map(Region::getCells) //
                .flatMap(Collection::stream) //
                .map(Cell::getValue) //
                .filter(Optional::isPresent) //
                .map(Optional::get)
                .forEach(possibilities::remove);

        return possibilities;
    }

    @Override
    public int getDifficulty() {
        return 1;
    }
}
