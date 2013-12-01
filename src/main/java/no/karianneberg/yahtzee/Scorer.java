package no.karianneberg.yahtzee;

import no.karianneberg.yahtzee.scoringStrategies.*;

import java.util.HashMap;
import java.util.Map;

public class Scorer {

    private final Map<Combination, ScoringStrategy> strategies = new HashMap<Combination, ScoringStrategy>();

    public Scorer() {
        strategies.put(Combination.ONES, new NumberOfDiceWithValueScoringStrategy(1));
        strategies.put(Combination.TWOS, new NumberOfDiceWithValueScoringStrategy(2));
        strategies.put(Combination.THREES, new NumberOfDiceWithValueScoringStrategy(3));
        strategies.put(Combination.FOURS, new NumberOfDiceWithValueScoringStrategy(4));
        strategies.put(Combination.FIVES, new NumberOfDiceWithValueScoringStrategy(5));
        strategies.put(Combination.SIXES, new NumberOfDiceWithValueScoringStrategy(6));
        strategies.put(Combination.ONE_PAIR, new NumberOfAKindScoringStrategy(2));
        strategies.put(Combination.TWO_PAIRS, new TwoPairsScoringStrategy());
        strategies.put(Combination.THREE_OF_A_KIND, new NumberOfAKindScoringStrategy(3));
        strategies.put(Combination.FOUR_OF_A_KIND, new NumberOfAKindScoringStrategy(4));
        strategies.put(Combination.FULL_HOUSE, new FullHouseScoringStrategy());
        strategies.put(Combination.SMALL_STRAIGHT, new SmallStraightScoringStrategy());
        strategies.put(Combination.LARGE_STRAIGHT, new LargeStraightScoringStrategy());
        strategies.put(Combination.CHANCE, new ChanceScoringStrategy());
        strategies.put(Combination.YAHTZEE, new YahtzeeScoringStrategy());
    }

    int score(Combination combo, Throw currentThrow) {
        return strategies.get(combo).score(currentThrow);
    }

}