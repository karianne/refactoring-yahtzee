package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 1:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class LargeStraightScoringStrategy {
    public int score(Throw currentThrow) {
        int score;
        score = currentThrow.isLargeStraight() ? 20 : 0;
        return score;
    }
}
