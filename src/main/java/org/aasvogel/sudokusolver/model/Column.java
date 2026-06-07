package org.aasvogel.sudokusolver.model;

public class Column extends Region{
    @Override
    public String getRegionTypeName() {
        return "Column";
    }

    @Override
    public String getRegionPosition() {
        return Integer.toString( getColumnIndex() +1);
    }

    protected Column(int index) {
        super(index);
    }

    public int getColumnIndex(){
        return index;
    }
}
