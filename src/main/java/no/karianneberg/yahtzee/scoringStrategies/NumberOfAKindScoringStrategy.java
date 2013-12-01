package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

public class NumberOfAKindScoringStrategy {

    private int numberOfAKind;

    public NumberOfAKindScoringStrategy(int numberOfAKind) {
        this.numberOfAKind = numberOfAKind;
    }

    public int scoreForNumberOfAKind(Throw currentThrow) {
        int value = currentThrow.findNumberOfAKind(this.numberOfAKind);
        return value * this.numberOfAKind;
    }
}
