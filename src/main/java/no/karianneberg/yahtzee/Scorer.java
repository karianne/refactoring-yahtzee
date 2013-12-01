package no.karianneberg.yahtzee;

import no.karianneberg.yahtzee.scoringStrategies.*;

public class Scorer {

    private final NumberOfAKindScoringStrategy onePairScoringStrategy = new NumberOfAKindScoringStrategy(2);
    private final NumberOfAKindScoringStrategy threeOfAKindScoringStrategy = new NumberOfAKindScoringStrategy(3);
    private final NumberOfAKindScoringStrategy fourOfAKindScoringStrategy = new NumberOfAKindScoringStrategy(4);
    private final NumberOfDiceWithValueScoringStrategy onesScoringStrategy = new NumberOfDiceWithValueScoringStrategy(1);
    private final NumberOfDiceWithValueScoringStrategy twosScoringStrategy = new NumberOfDiceWithValueScoringStrategy(2);
    private final NumberOfDiceWithValueScoringStrategy threesScoringStrategy = new NumberOfDiceWithValueScoringStrategy(3);
    private final NumberOfDiceWithValueScoringStrategy foursScoringStrategy = new NumberOfDiceWithValueScoringStrategy(4);
    private final NumberOfDiceWithValueScoringStrategy fivesScoringStrategy = new NumberOfDiceWithValueScoringStrategy(5);
    private final NumberOfDiceWithValueScoringStrategy sixesScoringStrategy = new NumberOfDiceWithValueScoringStrategy(6);
    private final TwoPairsScoringStrategy twoPairsScoringStrategy = new TwoPairsScoringStrategy();
    private final FullHouseScoringStrategy fullHouseScoringStrategy = new FullHouseScoringStrategy();
    private final SmallStraightScoringStrategy smallStraightScoringStrategy = new SmallStraightScoringStrategy();
    private final LargeStraightScoringStrategy largeStraightScoringStrategy = new LargeStraightScoringStrategy();
    private final ChanceScoringStrategy chanceScoringStrategy = new ChanceScoringStrategy();
    private final YahtzeeScoringStrategy yahtzeeScoringStrategy = new YahtzeeScoringStrategy();


    public Scorer() {
    }

    int score(Combination combo, Throw currentThrow) {
        int score = 0;

        switch (combo) {
            case ONES:
                score = onesScoringStrategy.scoreForNumberOfDiceWithValue(currentThrow);
                break;
            case TWOS:
                score = twosScoringStrategy.scoreForNumberOfDiceWithValue(currentThrow);
                break;
            case THREES:
                score = threesScoringStrategy.scoreForNumberOfDiceWithValue(currentThrow);
                break;
            case FOURS:
                score = foursScoringStrategy.scoreForNumberOfDiceWithValue(currentThrow);
                break;
            case FIVES:
                score = fivesScoringStrategy.scoreForNumberOfDiceWithValue(currentThrow);
                break;
            case SIXES:
                score = sixesScoringStrategy.scoreForNumberOfDiceWithValue(currentThrow);
                break;
            case TWO_PAIRS:
                score = twoPairsScoringStrategy.score(currentThrow);
            break;
            case ONE_PAIR:
                score = onePairScoringStrategy.scoreForNumberOfAKind(currentThrow);
                break;
            case THREE_OF_A_KIND:
                score = threeOfAKindScoringStrategy.scoreForNumberOfAKind(currentThrow);
                break;
            case FOUR_OF_A_KIND:
                score = fourOfAKindScoringStrategy.scoreForNumberOfAKind(currentThrow);
                break;
            case FULL_HOUSE:
                score = fullHouseScoringStrategy.score(currentThrow);
                break;
            case SMALL_STRAIGHT:
                score = smallStraightScoringStrategy.score(currentThrow);
                break;
            case LARGE_STRAIGHT:
                score = largeStraightScoringStrategy.score(currentThrow);
                break;
            case CHANCE:
                score = chanceScoringStrategy.score(currentThrow);
                break;
            case YAHTZEE:
                score = yahtzeeScoringStrategy.score(currentThrow);
                break;
        }
        return score;
    }

}