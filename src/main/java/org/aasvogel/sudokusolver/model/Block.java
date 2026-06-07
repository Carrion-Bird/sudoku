package org.aasvogel.sudokusolver.model;

import org.aasvogel.sudokusolver.common.BlockCoordinates;
import org.aasvogel.sudokusolver.common.Configuration;

public class Block extends Region{

    protected Block(int index) {
        super(index);
    }

    public BlockCoordinates getBlockCoordinates(){
        int row = index / Configuration.sizeBase;
        int col = index % Configuration.sizeBase;
        return new BlockCoordinates( row, col);
    }

    @Override
    public String getRegionTypeName() {
        return "Block";
    }

    @Override
    public String getRegionPosition() {

        return getBlockCoordinates().format();
    }
}
