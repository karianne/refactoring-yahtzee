package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;
import no.karianneberg.yahtzee.TwoPairs;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 12:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class TwoPairsScoringStrategy implements ScoringStrategy {
    public int score(Throw currentThrow) {
        int score;TwoPairs result = currentThrow.findTwoPairs();
        if (result == null) {
            score = 0;
        } else {
            score = (result.getFirst() * 2)
                        + (result.getSecond() * 2);
        }
        return score;
    }
}
