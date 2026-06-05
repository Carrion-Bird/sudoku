package org.aasvogel.sudokusolver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Regions can be rows, columns or blocks.
 */
public abstract class Region {

    protected final Page page;

    protected Region(Page page) {
        this.page = page;
    }

    public abstract Cell get(int index);
}