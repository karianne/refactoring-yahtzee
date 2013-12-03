package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class FullHouseScoringStrategy implements ScoringStrategy {
    public int score(Throw currentThrow) {
        int score;
        int pairValue = 0;
        int threeOfAKindValue = 0;

        if (fullHouseWithPairLast(currentThrow.getDice())) {
            threeOfAKindValue = currentThrow.getDice().get(0);
            pairValue = currentThrow.getDice().get(3);
        } else if (fullHouseWithPairFirst(currentThrow.getDice())) {
            pairValue = currentThrow.getDice().get(0);
            threeOfAKindValue = currentThrow.getDice().get(2);
        }

        score = (pairValue * 2) + (threeOfAKindValue * 3);
        return score;
    }

    private boolean fullHouseWithPairFirst(List<Integer> dice) {
        return     dice.get(0).equals(dice.get(1))
                && dice.get(2).equals(dice.get(4));
    }

    private boolean fullHouseWithPairLast(List<Integer> dice) {
        return     dice.get(0).equals(dice.get(2))
                && dice.get(3).equals(dice.get(4));
    }
}
