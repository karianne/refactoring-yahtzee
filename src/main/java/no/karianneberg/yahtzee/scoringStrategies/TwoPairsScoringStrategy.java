package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

import java.util.List;

public class TwoPairsScoringStrategy implements ScoringStrategy {
    public int score(Throw currentThrow) {
        int firstPairValue = 0;
        int secondPairValue = 0;

        if (leftoverDieLast(currentThrow.getDice())) {
            firstPairValue = currentThrow.getDice().get(0);
            secondPairValue = currentThrow.getDice().get(2);
        } else if (leftoverDieInTheMiddle(currentThrow.getDice())) {
            firstPairValue = currentThrow.getDice().get(0);
            secondPairValue = currentThrow.getDice().get(3);
        } else if (leftoverDieFirst(currentThrow.getDice())) {
            firstPairValue = currentThrow.getDice().get(1);
            secondPairValue = currentThrow.getDice().get(3);
        }
        return (firstPairValue * 2)
                + (secondPairValue * 2);
    }

    private boolean leftoverDieFirst(List<Integer> theDice) {
        return theDice.get(1).equals(theDice.get(2))
                && theDice.get(3).equals(theDice.get(4));
    }

    private boolean leftoverDieInTheMiddle(List<Integer> theDice) {
        return theDice.get(0).equals(theDice.get(1))
                && theDice.get(3).equals(theDice.get(4));
    }

    private boolean leftoverDieLast(List<Integer> theDice) {
        return theDice.get(0).equals(theDice.get(1))
                && theDice.get(2).equals(theDice.get(3));
    }
}
