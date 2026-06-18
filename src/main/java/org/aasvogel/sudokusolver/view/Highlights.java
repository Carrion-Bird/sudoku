package org.aasvogel.sudokusolver.view;

import org.aasvogel.sudokusolver.common.BlockCoordinates;
import org.aasvogel.sudokusolver.common.CellCoordinates;
import org.aasvogel.sudokusolver.logic.hints.Hint;

import java.util.HashSet;
import java.util.Set;

public class Highlights {
    private CellCoordinates cell;
    private Set<CellCoordinates> additionalCells = new HashSet<>();
    private Integer row;
    private Integer col;
    private BlockCoordinates block;

    public static Highlights singleCell( CellCoordinates coordinates){
        Highlights result = new Highlights();
        result.cell = coordinates;
        result.block = coordinates.getCorrespondingBlock();
        result.row = coordinates.getRow();
        result.col = coordinates.getCol();

        return result;
    }

    public static Highlights multipleCells( Set<CellCoordinates> coordinates){
        Highlights result = new Highlights();
        result.additionalCells = Set.copyOf( coordinates);
        return result;
    }

    public static Highlights fromHint(Hint hint) {
        Highlights result = new Highlights();
        result.cell = hint.getTargetCell();
        result.additionalCells = Set.of(hint.getTargetCell());
        return result;
    }

    public Set<CellCoordinates> getAdditionalCells() {
        return Set.copyOf(additionalCells);
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

    public CellCoordinates getCell() {
        return cell;
    }

    private Highlights() {}
}
