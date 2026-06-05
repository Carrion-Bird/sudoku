package org.aasvogel.sudokusolver.model;

public class Row extends Region {

    private final int rowindex;

    protected Row(Page thePage, int index) {
        super(thePage);

        this.rowindex = index;
    }


    @Override
    public Cell get(int columnIndex) {
        return page.getCellAt( rowindex, columnIndex);
    }
}
