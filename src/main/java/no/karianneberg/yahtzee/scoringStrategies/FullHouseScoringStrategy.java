package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.FullHouse;
import no.karianneberg.yahtzee.Throw;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class FullHouseScoringStrategy {
    public int score(Throw currentThrow) {
        int score;FullHouse fullHouse = currentThrow.findFullHouse();
        if (fullHouse == null) {
            score = 0;
        } else {
            score = (fullHouse.getFirst() * 2)
                    + (fullHouse.getSecond() * 3);
        }
        return score;
    }
}
