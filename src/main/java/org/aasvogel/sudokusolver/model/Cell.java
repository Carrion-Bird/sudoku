package org.aasvogel.sudokusolver.model;

import org.aasvogel.sudokusolver.common.CellCoordinates;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cell {

    private final CellCoordinates coordinates;

    private Symbol value;
    private final Set<Symbol> penciled = new HashSet<>();

    public Cell(CellCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void togglePenciled( Symbol symbol){
        value = null;

        if (penciled.contains( symbol))
            penciled.remove( symbol);
        else
            penciled.add( symbol);
    }

    public void setPenciled(Set<Symbol> values){
        penciled.clear();
        penciled.addAll(values);
        value = null;

    }

    public void setValue( Symbol value){
        this.value = value;
        penciled.clear();
    }


    public Optional<Symbol> getValue() {
        return Optional.ofNullable( value);
    }

    public Set<Symbol> getPenciled( ){
        return Set.copyOf( penciled);
    }

    public CellCoordinates getCoordinates() {
        return coordinates;
    }
}
