package org.aasvogel.sudokusolver.model;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private final int base;
    private final int size;

    private List<Cell> cells = new ArrayList<>();

    // size increases quadratically. Possible values 2, 3, 4, ??
    public Page( int sizeBase) {
        base = sizeBase;
        size = base * base;

        for (int i = 0; i < size * size; i++) {
            cells.add(new Cell());
        }
    }

    public Cell getCellAt(int rowIndex, int columnIndex) {
        return cells.get(rowIndex * size + columnIndex);
    }

    int getBase() {
        return base;
    }

    public int getSize(){
        return size;
    }
}
