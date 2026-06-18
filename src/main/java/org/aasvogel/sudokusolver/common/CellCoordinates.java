package org.aasvogel.sudokusolver.common;

public class CellCoordinates extends Coordinates {
    public CellCoordinates(int row, int col) {
        super(row, col);
    }

    public BlockCoordinates getCorrespondingBlock(){
        return new BlockCoordinates(getRow() / 3, getCol() / 3);
    }
}
