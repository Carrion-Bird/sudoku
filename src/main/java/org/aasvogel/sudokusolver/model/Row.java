package org.aasvogel.sudokusolver.model;

public class Row extends Region {
    protected Row(int index) {
        super(index);
    }

    @Override
    public String getRegionPosition() {
        return Integer.toString( getRowIndex() +1);
    }

    @Override
    public String getRegionTypeName() {
        return "Row";
    }

    public int getRowIndex(){
        return index;
    }
}
