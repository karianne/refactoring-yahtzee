package no.karianneberg.yahtzee;

import java.util.*;

public class YahtzeeGame {
    private final ThrowResultStrategy throwResultStrategy;
    private final Set<Combination> scoredCombinations;
    private final Scorer scorer = new Scorer();

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

        int score = scorer.score(combo, currentThrow);

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
