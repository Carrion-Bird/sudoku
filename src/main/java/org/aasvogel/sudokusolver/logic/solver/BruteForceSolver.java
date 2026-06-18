package org.aasvogel.sudokusolver.logic.solver;

import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.logic.validity.ValidityChecker;
import org.aasvogel.sudokusolver.logic.validity.ValidityResult;
import org.aasvogel.sudokusolver.model.Cell;
import org.aasvogel.sudokusolver.model.Page;
import org.aasvogel.sudokusolver.model.Symbol;

import java.util.HashSet;
import java.util.Set;

/**
 * Brute-Force approach ignoring Penciled.
 * Only works in theory. Practically it leads to OutOfMemoryError :-(
 */
public class BruteForceSolver implements Solver{
    private ValidityChecker checker = new ValidityChecker();

    @Override
    public Set<Page> getSollutions(Page startingSituation) {
        ValidityResult validity = checker.check(startingSituation);
        if (validity.getType() == ValidityResult.ResultType.INVALID)
            return Set.of();

        return getSollutionsRec( startingSituation, new CellCoordinates(0,0));
    }

    /**
     * We assume we are correct so far.
     */
    private Set<Page> getSollutionsRec(Page page, CellCoordinates currentCoordinates){
        if (isCurrentCoordinateSet(page, currentCoordinates))
            return tryNextCoordinate( page,  currentCoordinates);
        else {
            Set<Page> possibilities = new HashSet<>();

            for (Symbol possibleSymbol : Configuration.symbols) {
                Page newVariant = page.getCopy();

                newVariant.getCellAt( currentCoordinates).setValue( possibleSymbol);

                ValidityResult validity = checker.check(newVariant);
                if (validity.getType() == ValidityResult.ResultType.VALID)
                    possibilities.addAll( tryNextCoordinate( newVariant, currentCoordinates));
            }
            return possibilities;
        }
    }

    private Set<Page> tryNextCoordinate(Page page, CellCoordinates currentCoordinates) {
        if (isLastCoordinate(currentCoordinates))
            return Set.of(page);
        else
            return getSollutionsRec( page, getNextCoordinates( currentCoordinates));
    }

    private boolean isCurrentCoordinateSet(Page page, CellCoordinates currentCoordinates) {
        Cell cellAt = page.getCellAt(currentCoordinates);
        return cellAt.getValue().isPresent();
    }

    private CellCoordinates getNextCoordinates(CellCoordinates coordinates){
        if (isLastCoordinate(coordinates))
            throw new IllegalArgumentException( "Can't go further than " + coordinates);
        else if (coordinates.getCol() < Configuration.getSymbolsQuantity()) {
            return new CellCoordinates(coordinates.getRow(), coordinates.getCol() +1);
        } else
            return new CellCoordinates(coordinates.getRow() +1, 0);
    }

    private boolean isLastCoordinate(CellCoordinates coordinates){
        return coordinates.getCol() == Configuration.getSymbolsQuantity() -1//
                || coordinates.getRow() == Configuration.getSymbolsQuantity() -1;
    }


}
