package no.karianneberg.yahtzee.scoringStrategies;

import no.karianneberg.yahtzee.Throw;

/**
 * Created with IntelliJ IDEA.
 * User: karianneberg
 * Date: 12/1/13
 * Time: 1:17 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ScoringStrategy {
    int score(Throw currentThrow);
}
