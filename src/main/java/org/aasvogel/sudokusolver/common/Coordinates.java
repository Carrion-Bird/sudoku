package org.aasvogel.sudokusolver.common;

public class Coordinates {
    private final int row;
    private final int col;

    public Coordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String format(){
        return (row + 1)+ ", " + (col +1);
    }
}
