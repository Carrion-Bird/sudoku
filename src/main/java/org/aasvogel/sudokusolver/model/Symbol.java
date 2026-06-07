package org.aasvogel.sudokusolver.model;

import org.aasvogel.sudokusolver.common.Configuration;

import java.util.Optional;
import java.util.logging.Logger;

public interface Symbol {

    String getRepresentation();

    static Optional<Symbol> fromText(String text) {
        for (Symbol symbol : Configuration.symbols) {
            if (text.equals(symbol.getRepresentation()))
                return Optional.of( symbol);
        }
        Logger.getLogger( Symbol.class.getName()).warning( "Representation not found, text: " + text);

        return Optional.empty();
    }
}
