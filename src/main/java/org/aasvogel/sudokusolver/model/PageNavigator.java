package org.aasvogel.sudokusolver.model;

import org.aasvogel.sudokusolver.common.BlockCoordinates;
import org.aasvogel.sudokusolver.common.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PageNavigator {

    private final Page page;


    public PageNavigator(Page page) {
        this.page = page;
    }

    public Iterable<Cell> getCells() {
        return page.getCells();
    }

    public Block getBlock(BlockCoordinates coordinates) {
        return page.getBlocks().get(coordinates.getRow() * Configuration.sizeBase + coordinates.getCol());
    }

    public Collection<Region> getRegionsOfCell(Cell cell) {
        Row row = page.getRows().get(cell.getCoordinates().getRow());
        Column col = page.getColumns().get(cell.getCoordinates().getCol());
        Block block = getBlock( cell.getCoordinates().getCorrespondingBlock());

        return List.of( row, col, block);
    }

    public Collection<Region> getAllRegions(){
        List<Region> allRegions = new ArrayList<>();
        allRegions.addAll( page.getRows());
        allRegions.addAll( page.getColumns());
        allRegions.addAll( page.getBlocks());
        return allRegions;
    }
}
