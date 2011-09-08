package no.karianneberg.yahtzee;

import java.util.*;

public class YahtzeeGame {
    private final ThrowResultStrategy throwResultStrategy;
    private final Set<Combination> scoredCombinations;

    private Throw currentThrow;
    private int currentScore;
    private int currentRoundNumber;
    private List<Integer> currentlyHeldDice;
    private int currentNumberOfThrowsInThisRound = 0;

    public static final int NUMBER_OF_ROUNDS = Combination.values().length;
    private static final int MAX_NUMBER_OF_THROWS_PER_ROUND = 3;

    public YahtzeeGame(ThrowResultStrategy throwResultStrategy) {
        this.throwResultStrategy = throwResultStrategy;
        this.currentlyHeldDice = new ArrayList<Integer>();
        this.currentRoundNumber = 1;
        this.scoredCombinations = new HashSet<Combination>();
    }

    public void throwDice() {
        if (currentNumberOfThrowsInThisRound >= MAX_NUMBER_OF_THROWS_PER_ROUND) {
            throw new YahtzeeException("You cannot throw the dice more than "
                    + MAX_NUMBER_OF_THROWS_PER_ROUND + " times per round!");
        }

        Throw newThrow = throwResultStrategy.throwDice();
        currentThrow = currentlyHeldDice.isEmpty()
                           ? newThrow
                           : currentThrow.mergeWith(newThrow, currentlyHeldDice);
        currentNumberOfThrowsInThisRound++;
    }

    public void holdDice(Integer... positions) {
        this.currentlyHeldDice = Arrays.asList(positions);
    }

    public int scoreFor(Combination combo) {
        if (noThrowsYetInThisRound()) {
            throw new YahtzeeException("You have to throw at " +
                    "least once before you score");
        }

        if (scoredCombinations.contains(combo)) {
            throw new YahtzeeException("This combination has " +
                    "already been taken!");
        }

        scoredCombinations.add(combo);

        int score = 0;

        switch (combo) {
            case ONES:
                score = scoreForNumberOfDiceWithValue(1);
                break;
            case TWOS:
                score = scoreForNumberOfDiceWithValue(2);
                break;
            case THREES:
                score = scoreForNumberOfDiceWithValue(3);
                break;
            case FOURS:
                score = scoreForNumberOfDiceWithValue(4);
                break;
            case FIVES:
                score = scoreForNumberOfDiceWithValue(5);
                break;
            case SIXES:
                score = scoreForNumberOfDiceWithValue(6);
                break;
            case ONE_PAIR:
                score = scoreForNumberOfAKind(2);
                break;
            case TWO_PAIRS:
                TwoPairs result = currentThrow.findTwoPairs();
                if (result == null) {
                    score = 0;
                } else {
                    score =   (result.getFirst() * 2)
                            + (result.getSecond() * 2);
                }
                break;
            case THREE_OF_A_KIND:
                score = scoreForNumberOfAKind(3);
                break;
            case FOUR_OF_A_KIND:
                score = scoreForNumberOfAKind(4);
                break;
            case FULL_HOUSE:
                FullHouse fullHouse = currentThrow.findFullHouse();
                if (fullHouse == null) {
                    score = 0;
                } else {
                    score =   (fullHouse.getFirst() * 2)
                            + (fullHouse.getSecond() * 3);
                }
                break;
            case SMALL_STRAIGHT:
                score = currentThrow.isSmallStraight() ? 15 : 0;
                break;
            case LARGE_STRAIGHT:
                score = currentThrow.isLargeStraight() ? 20 : 0;
                break;
            case CHANCE:
                score = currentThrow.calculateSumOfDice();
                break;
            case YAHTZEE:
                score = currentThrow.isYahtzee() ? 50 : 0;
                break;
        }

        currentScore += score;
        currentRoundNumber++;
        currentNumberOfThrowsInThisRound = 0;
        currentlyHeldDice = new ArrayList<Integer>();
        currentThrow = null;

        return score;
    }

    private boolean noThrowsYetInThisRound() {
        return currentThrow == null;
    }

    private int scoreForNumberOfAKind(int numberOfAKind) {
        int value = currentThrow.findNumberOfAKind(numberOfAKind);
        return value * numberOfAKind;
    }

    private int scoreForNumberOfDiceWithValue(int value) {
        int num = currentThrow.findNumberOfDiceWithValue(value);
        return num * value;
    }

    public int finalScore() {
        return currentScore;
    }

    public Map<Integer, Integer> getCurrentThrowAsMap() {
        return currentThrow.asMap();
    }

    public boolean isOver() {
        return currentRoundNumber > NUMBER_OF_ROUNDS;
    }

    public int getCurrentRoundNumber() {
        return currentRoundNumber;
    }

    public Set<Combination> getRemainingCombos() {
        Set<Combination> allCombos
                = new HashSet<Combination>(Arrays.asList(Combination.values()));
        allCombos.removeAll(scoredCombinations);

        return new HashSet<Combination>(allCombos);
    }
}
