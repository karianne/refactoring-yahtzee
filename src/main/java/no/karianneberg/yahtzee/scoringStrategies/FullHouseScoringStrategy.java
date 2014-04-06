package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

import java.util.List;

public class FullHouseScoringStrategy implements ScoringStrategy {
    public int score(Throw currentThrow) {
        int pairValue = 0;
        int threeOfAKindValue = 0;

        if (fullHouseWithPairLast(currentThrow.getDice())) {
            threeOfAKindValue = currentThrow.getDice().get(0);
            pairValue = currentThrow.getDice().get(3);
        } else if (fullHouseWithPairFirst(currentThrow.getDice())) {
            pairValue = currentThrow.getDice().get(0);
            threeOfAKindValue = currentThrow.getDice().get(2);
        }

        return (pairValue * 2)
                + (threeOfAKindValue * 3);
    }

    private boolean fullHouseWithPairFirst(List<Integer> dice) {
        return dice.get(0).equals(dice.get(1))
                && dice.get(2).equals(dice.get(4));
    }

    private boolean fullHouseWithPairLast(List<Integer> dice) {
        return dice.get(0).equals(dice.get(2))
                && dice.get(3).equals(dice.get(4));
    }
}
