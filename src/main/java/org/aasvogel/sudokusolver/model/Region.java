package org.aasvogel.sudokusolver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Regions can be rows, columns or blocks.
 */
public abstract class Region {

    protected final int index;
    private List<Cell> cells = new ArrayList<>();

    protected Region(int index) {
        this.index = index;
    }

    public void addCell(Cell cell){

        cells.add( cell);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public abstract String getRegionTypeName();
    public abstract String getRegionPosition();
}