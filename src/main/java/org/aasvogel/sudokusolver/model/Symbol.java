package org.aasvogel.sudokusolver.model;

import java.util.Set;

public interface Symbol {
    String getRepresentation();

    static Symbol fromText(Set<? extends Symbol> set, String text) {
        for (Symbol symbol : set) {
            if (text.equals(symbol.getRepresentation()))
                return symbol;
        }
        throw new IllegalArgumentException("Representation not found, text: " + text);
    }
}
