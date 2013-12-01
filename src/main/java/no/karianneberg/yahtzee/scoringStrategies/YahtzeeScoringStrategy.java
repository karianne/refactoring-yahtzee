package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class YahtzeeScoringStrategy {
    public int score(Throw currentThrow) {
        int score;
        score = currentThrow.isYahtzee() ? 50 : 0;
        return score;
    }
}
