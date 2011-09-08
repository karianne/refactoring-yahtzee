package no.karianneberg.yahtzee;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class YahtzeeGameTest {
    private ThrowResultStrategy resultStrategy;
    private YahtzeeGame game;

    @Before
    public void setup() {
        resultStrategy = mock(ThrowResultStrategy.class);
        game = new YahtzeeGame(resultStrategy);
    }

    @Test
    public void noPointsWhenGameHasNotStarted() throws Exception {
        assertThat(game.finalScore()).isEqualTo(0);
    }

    @Test
    public void allOnesGivesFivePointsForOnes() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.ONES);

        assertThat(score).isEqualTo(5);
    }

    @Test
    public void allTwosGivesTenPointsForTwos() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 2, 2, 2, 2));

        game.throwDice();
        int score = game.scoreFor(Combination.TWOS);

        assertThat(score).isEqualTo(10);
    }

    @Test
    public void allThreesGivesFifteenPointsForThrees() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(3, 3, 3, 3, 3));

        game.throwDice();
        int score = game.scoreFor(Combination.THREES);

        assertThat(score).isEqualTo(15);
    }

    @Test
    public void allFoursGivesTwentyPointsForFours() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(4, 4, 4, 4, 4));

        game.throwDice();
        int score = game.scoreFor(Combination.FOURS);

        assertThat(score).isEqualTo(20);
    }

    @Test
    public void allFivesGivesTwentyFivePointsForFives() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(5, 5, 5, 5, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.FIVES);

        assertThat(score).isEqualTo(25);
    }

    @Test
    public void allSixesGivesThirtyPointsForSixes() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(6, 6, 6, 6, 6));

        game.throwDice();
        int score = game.scoreFor(Combination.SIXES);

        assertThat(score).isEqualTo(30);
    }

    @Test
    public void twoOnesAndRestUnequalGivesTwoPointsForOnePair() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 2, 3, 4));

        game.throwDice();
        int score = game.scoreFor(Combination.ONE_PAIR);

        assertThat(score).isEqualTo(2);
    }

    @Test
    public void twoOnesAndTwoFivesGivesTenPointsForOnePair() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 5, 3, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.ONE_PAIR);

        assertThat(score).isEqualTo(10);
    }

    @Test
    public void smallStraightGivesZeroPointsForOnePair() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 2, 3, 4, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.ONE_PAIR);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void smallStraightGivesZeroPointsForTwoPairs() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 2, 3, 4, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.TWO_PAIRS);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void twoOnesAndTwoFivesGivesTwelvePointsForTwoPairs() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(5, 1, 5, 3, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.TWO_PAIRS);

        assertThat(score).isEqualTo(12);
    }

    @Test
    public void fourOnesGivesFourPointsForTwoPairs() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 4, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.TWO_PAIRS);

        assertThat(score).isEqualTo(4);
    }

    @Test
    public void threeOnesGivesThreePointsForThreeOfAKind() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 4, 6, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.THREE_OF_A_KIND);

        assertThat(score).isEqualTo(3);
    }

    @Test
    public void fourTwosGivesEightPointsForFourOfAKind() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 3, 2, 2, 2));

        game.throwDice();
        int score = game.scoreFor(Combination.FOUR_OF_A_KIND);

        assertThat(score).isEqualTo(8);
    }

    @Test
    public void threeTwosAndTwoFivesGivesSixteenPointsForFullHouse() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 5, 2, 5, 2));

        game.throwDice();
        int score = game.scoreFor(Combination.FULL_HOUSE);

        assertThat(score).isEqualTo(16);
    }

    @Test
    public void yahtzeeInTwosGivesTenPointsForFullHouse() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 2, 2, 2, 2));

        game.throwDice();
        int score = game.scoreFor(Combination.FULL_HOUSE);

        assertThat(score).isEqualTo(10);
    }

    @Test
    public void smallStraightGivesFifteenPointsForSmallStraight() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 2, 3, 4, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.SMALL_STRAIGHT);

        assertThat(score).isEqualTo(15);
    }

    @Test
    public void oneTwoThreeFiveSixGivesNoPointsForSmallStraight() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 2, 3, 6, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.SMALL_STRAIGHT);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void oneTwoThreeFiveSixGivesNoPointsForLargeStraight() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 2, 3, 6, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.LARGE_STRAIGHT);

        assertThat(score).isEqualTo(0);
    }

    @Test
    public void largeStraightGivesTwentyPointsForLargeStraight() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(3, 2, 6, 4, 5));

        game.throwDice();
        int score = game.scoreFor(Combination.LARGE_STRAIGHT);

        assertThat(score).isEqualTo(20);
    }

    @Test
    public void allOnesGivesFivePointsForChance() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.CHANCE);

        assertThat(score).isEqualTo(5);
    }

    @Test
    public void allOnesGivesFiftyPointsForYahtzee() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.YAHTZEE);

        assertThat(score).isEqualTo(50);
    }

    @Test
    public void scoreForOneRoundIsEqualToFinalScoreAfterOneRound() {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));

        game.throwDice();
        int score = game.scoreFor(Combination.ONES);

        assertThat(game.finalScore()).isEqualTo(score);
    }

    @Test
    public void combinesScoreForTwoRoundsIsEqualToFinalScoreAfterTwoRounds() {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));

        game.throwDice();
        int firstScore = game.scoreFor(Combination.ONES);
        game.throwDice();
        int secondScore = game.scoreFor(Combination.THREE_OF_A_KIND);

        assertThat(game.finalScore()).isEqualTo(firstScore + secondScore);
    }

    @Test
    public void noDiceWillBeRethrownWhenAllDiceAreHeld() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        game.throwDice();
        game.holdDice(0, 1);

        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 2, 2, 2, 2));
        game.throwDice();
        Map<Integer, Integer> firstThrow = game.getCurrentThrowAsMap();

        Throw expectedThrow = new Throw(1, 1, 2, 2, 2);

        assertNotNull(expectedThrow.asMap());
        assertEquals(expectedThrow.asMap(), firstThrow);
    }

    @Test
    public void heldDiceAreResetAfterScoring() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        game.throwDice();
        game.holdDice(0,1);

        when(resultStrategy.throwDice()).thenReturn(new Throw(2, 2, 2, 2, 2));
        game.throwDice();
        game.scoreFor(Combination.ONES);

        Throw allThrees = new Throw(3, 3, 3, 3, 3);
        when(resultStrategy.throwDice()).thenReturn(allThrees);
        game.throwDice();
        assertThat(allThrees.asMap()).isEqualTo(game.getCurrentThrowAsMap());
    }

    @Test
    public void gameIsOverAfterFifteenRounds() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        for (Combination combo : Combination.values()) {
            game.throwDice();
            game.scoreFor(combo);
        }

        assertThat(game.isOver()).isTrue();
    }

    @Test
    public void gameIsNotOverBeforeAfterFifteenRounds() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        for (Combination combo : Combination.values()) {
            assertThat(game.isOver()).isFalse();
            game.throwDice();
            game.scoreFor(combo);
        }
    }

    @Test(expected = YahtzeeException.class)
    public void cannotPlaceScoreOnSameComboTwice() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        game.throwDice();
        game.scoreFor(Combination.TWOS);
        game.throwDice();
        game.scoreFor(Combination.TWOS);
    }
    
    @Test(expected = YahtzeeException.class)
    public void cannotThrowDiceMoreThanThreeTimesInOneRound() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        game.throwDice();
        game.throwDice();
        game.throwDice();
        game.throwDice();
    }

    @Test(expected = YahtzeeException.class)
    public void cannotScoreBeforeAThrowIsMade() throws Exception {
        game.scoreFor(Combination.ONES);
    }

    @Test(expected = YahtzeeException.class)
    public void cannotScoreBeforeAThrowIsMadeEvenAfterItsScoredFirstTime() throws Exception {
        when(resultStrategy.throwDice()).thenReturn(new Throw(1, 1, 1, 1, 1));
        game.throwDice();
        game.scoreFor(Combination.ONES);
        game.scoreFor(Combination.TWOS);
    }
}