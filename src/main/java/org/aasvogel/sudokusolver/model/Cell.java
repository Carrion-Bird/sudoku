package org.aasvogel.sudokusolver.model;

import java.util.Set;

public class Cell {

    private Symbol value;
    private Set<Symbol> possibilities;

    public void setValue(Symbol value) {
        this.value = value;
        possibilities.clear();
    }
}
