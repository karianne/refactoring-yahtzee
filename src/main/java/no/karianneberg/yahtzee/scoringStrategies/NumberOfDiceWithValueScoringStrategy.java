package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

public class NumberOfDiceWithValueScoringStrategy {

    private int value;

    public NumberOfDiceWithValueScoringStrategy(int value) {
        this.value = value;
    }

    public int scoreForNumberOfDiceWithValue(Throw currentThrow) {
        int num = currentThrow.findNumberOfDiceWithValue(this.value);
        return num * this.value;
    }
}
