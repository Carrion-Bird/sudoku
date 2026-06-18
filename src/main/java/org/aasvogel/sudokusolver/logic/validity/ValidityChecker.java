package org.aasvogel.sudokusolver.logic.validity;

import org.aasvogel.sudokusolver.model.Cell;
import org.aasvogel.sudokusolver.model.Page;
import org.aasvogel.sudokusolver.model.Region;
import org.aasvogel.sudokusolver.model.Symbol;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidityChecker {

    public ValidityResult check(Page model) {
        try {
            checkRegions(model.getRows());
            checkRegions(model.getColumns());
            checkRegions(model.getBlocks());
        } catch (ValidityFailed e) {
            var coordinates = e.cells.stream().map(Cell::getCoordinates).toList();
            return ValidityResult.invalid( coordinates, e.region, e.symbol );
        }

        return ValidityResult.valid();
    }

    private void checkRegions(List<? extends Region> regions) throws ValidityFailed {
        for (Region region : regions) {
            Map<Symbol, List<Cell>> cellsBySymbol = region
                    .getCells()
                    .stream()
                    .filter(c -> c.getValue().isPresent())
                    .collect(Collectors.groupingBy(cell -> cell.getValue().get()));

            Optional<Map.Entry<Symbol, List<Cell>>> multipleSymbols = cellsBySymbol
                    .entrySet().stream()
                    .filter(e -> e.getValue().size() > 1)
                    .findAny();

            if (multipleSymbols.isPresent()) {
                var entry = multipleSymbols.get();
                throw new ValidityFailed(region, entry.getKey(), entry.getValue());
            }
        }
    }

    private static class ValidityFailed extends Exception {
        private final Region region;
        private final Symbol symbol;
        private final Set<Cell> cells;

        private ValidityFailed(Region region, Symbol symbol, List<Cell> cells) {
            this.region = region;
            this.symbol = symbol;
            this.cells = Set.copyOf(cells);
        }
    }
}
