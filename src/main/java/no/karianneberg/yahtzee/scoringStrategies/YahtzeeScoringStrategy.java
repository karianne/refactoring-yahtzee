package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 1:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class YahtzeeScoringStrategy implements ScoringStrategy {
    @Override
    public int score(Throw currentThrow) {
        int score;
        Collections.sort(currentThrow.getDice());
        score = currentThrow.getDice().get(0).equals(currentThrow.getDice().get(4)) ? 50 : 0;
        return score;
    }
}
