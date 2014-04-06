package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 1:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class LargeStraightScoringStrategy implements ScoringStrategy {
    public int score(Throw currentThrow) {
        int score;
        score = currentThrow.getDice().equals(Arrays.asList(6, 5, 4, 3, 2)) ? 20 : 0;
        return score;
    }
}
