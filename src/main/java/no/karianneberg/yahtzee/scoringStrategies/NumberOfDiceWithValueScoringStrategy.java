package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

public class NumberOfDiceWithValueScoringStrategy implements ScoringStrategy {

    private int value;

    public NumberOfDiceWithValueScoringStrategy(int value) {
        this.value = value;
    }

    public int score(Throw currentThrow) {
        int number = 0;

        for (Integer val : currentThrow.getDice()) {
            if (val.equals(this.value)) {
                number++;
            }
        }

        int num = number;
        return num * this.value;
    }
}
