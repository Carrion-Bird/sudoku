package org.aasvogel.sudokusolver.model;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Digits implements Symbol {

    ONE('1'), TWO('2'), THREE('3'), //
    FOUR('4'), FIVE('5'), SIX('6'), //
    SEVEN('7'), EIGHT('8'), NINE('9');

    private final char representaion;

    Digits(char representaion) {
        this.representaion = representaion;
    }

    @Override
    public String getRepresentation() {
        return "" + representaion;
    }

    public static Set<Symbol> getSet() {
        return Arrays.stream(Digits.values()).collect(Collectors.toSet());
    }
}
