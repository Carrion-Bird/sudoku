package org.aasvogel.sudokusolver.model;

public class Block extends Region{

    private final int blockIndex;

    protected Block(Page page, int blockIndex) {
        super(page);
        this.blockIndex = blockIndex;
    }

    @Override
    public Cell get(int cellIndex) {
        int row = determineRow( cellIndex);
        int column = determineColumn( cellIndex);
        return page.getCellAt( row, column);
    }

    private int determineRow(int cellIndex) {
        int base = page.getBase();

        int rowByBlock = (blockIndex / base) * 3;
        int rowByCell = cellIndex / base;

        return rowByBlock + rowByCell;
    }

    private int determineColumn(int cellIndex) {
        int base = page.getBase();

        int columnByBlock = (blockIndex % base) * 3;
        int columnByCell = (cellIndex % base);

        return columnByBlock + columnByCell;
    }
}
