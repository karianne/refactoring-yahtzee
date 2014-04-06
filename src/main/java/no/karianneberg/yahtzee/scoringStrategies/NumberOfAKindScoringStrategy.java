package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

public class NumberOfAKindScoringStrategy implements ScoringStrategy {

    private int numberOfAKind;

    public NumberOfAKindScoringStrategy(int numberOfAKind) {
        this.numberOfAKind = numberOfAKind;
    }

    public int score(Throw currentThrow) {
        int numberOfAKindValue = 0;

        for (int i = 0; (i < currentThrow.getDice().size() - this.numberOfAKind + 1)
                     && (numberOfAKindValue <= 0); i++) {
            if (currentThrow.getDice().get(i).equals(currentThrow.getDice().get(i + this.numberOfAKind - 1))) {
                numberOfAKindValue = currentThrow.getDice().get(i);
            }
        }

        int value = numberOfAKindValue;
        return value * this.numberOfAKind;
    }
}
