package org.aasvogel.sudokusolver.model;

public class Column extends Region{

    private final int columnIndex;

    protected Column(Page page, int columnIndex) {
        super(page);
        this.columnIndex = columnIndex;
    }

    @Override
    public Cell get(int rowindex) {
        return page.getCellAt( rowindex, columnIndex);
    }
}
