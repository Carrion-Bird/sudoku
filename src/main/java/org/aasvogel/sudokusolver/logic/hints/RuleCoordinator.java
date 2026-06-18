package org.aasvogel.sudokusolver.logic.hints;

import org.aasvogel.sudokusolver.model.Page;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Collects and coordinates all Rules.
 */
public class RuleCoordinator implements Rule{

    private List<WeightedRule> rules = new ArrayList<>();

    public RuleCoordinator() {
        rules.add( new OnlyPossibleSymbol());
        rules.add( new OnlyPlaceToPutThisSymbol());
        rules.sort( Comparator.comparing( WeightedRule::getDifficulty));
    }

    @Override
    public Optional<Hint> check(Page page) {
        return rules.stream()
                .map(rule -> rule.check(page))
                .filter(Optional::isPresent)
                .findFirst()
                .flatMap(Function.identity());
    }
}
