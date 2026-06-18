package org.aasvogel.sudokusolver.view;

import org.aasvogel.sudokusolver.common.BlockCoordinates;
import org.aasvogel.sudokusolver.common.CellCoordinates;

import java.util.Set;

public class Highlights {
    private Set<CellCoordinates> cells;
    private Integer row;
    private Integer col;
    private BlockCoordinates block;

    public static Highlights singleCell( CellCoordinates coordinates){
        Highlights result = new Highlights();
        result.cells = Set.of( coordinates);
        result.block = coordinates.getCorrespondingBlock();
        result.row = coordinates.getRow();
        result.col = coordinates.getCol();

        return result;
    }

    public static Highlights multipleCells( Set<CellCoordinates> coordinates){
        Highlights result = new Highlights();
        result.cells = Set.copyOf( coordinates);
        return result;
    }

    public Set<CellCoordinates> getCells() {
        return Set.copyOf(cells);
    }

    public Integer getRow() {
        return row;
    }

    public Integer getCol() {
        return col;
    }

    public BlockCoordinates getBlock() {
        return block;
    }

    private Highlights() {
    }
}
