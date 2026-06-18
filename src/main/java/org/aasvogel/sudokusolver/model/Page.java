package org.aasvogel.sudokusolver.model;

import org.aasvogel.sudokusolver.common.Configuration;
import org.aasvogel.sudokusolver.common.CellCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Page {

    private final int size;

    private List<Cell> cells = new ArrayList<>();

    private List<Column> columns;
    private List<Row> rows;
    private List<Block> blocks;

    // size increases quadratically. Possible values 2, 3, 4, ??
    public Page() {
        size = Configuration.getSymbolsQuantity();

        createRegions();

        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int columnIndex = 0; columnIndex < size; columnIndex++) {
                CellCoordinates coord = new CellCoordinates( rowIndex, columnIndex);

                Cell cell = new Cell(coord);
                rows.get( rowIndex).addCell( cell);
                columns.get(columnIndex).addCell( cell);
                blocks.get( getBlockIndex(coord)).addCell( cell);

                cells.add(cell);
            }
        }
    }

    private int getBlockIndex(CellCoordinates coord) {
        int blockRow = coord.getRow() / Configuration.sizeBase;
        int blockCol = coord.getCol() / Configuration.sizeBase;

        return blockRow * Configuration.sizeBase + blockCol;
    }

    private void createRegions() {
        rows = new ArrayList<>();
        columns = new ArrayList<>();
        blocks = new ArrayList<>();

        for (int i = 0; i < size; i++){
            rows.add( new Row( i));
            columns.add( new Column( i));
            blocks.add( new Block( i));
        }
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Cell getCellAt(CellCoordinates coordinates) {
        return cells.get(coordinates.getRow() * size + coordinates.getCol());
    }

    private void copyCell(Cell source){
        Cell target = getCellAt(source.getCoordinates());
        source.getValue().ifPresent( target::setValue);
        target.setPenciled(Set.copyOf( source.getPenciled()));
    }


    public Page getCopy() {
        Page target = new Page();

        cells.forEach(target::copyCell);

        return target;
    }
}
