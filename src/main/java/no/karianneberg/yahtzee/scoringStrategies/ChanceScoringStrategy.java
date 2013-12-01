package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 1:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChanceScoringStrategy {
    public int score(Throw currentThrow) {
        int score;
        score = currentThrow.calculateSumOfDice();
        return score;
    }
}
