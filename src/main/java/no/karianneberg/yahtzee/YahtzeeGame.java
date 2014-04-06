package no.karianneberg.yahtzee;

import java.util.*;

public class YahtzeeGame {
    private final Set<Combination> scoredCombinations;
    private final Scorer scorer = new Scorer();

    private int currentScore;
    private Round currentRound;
    private Queue<Round> remainingRounds = new LinkedList<Round>();

    public static final int NUMBER_OF_ROUNDS = Combination.values().length;

    public YahtzeeGame(ThrowResultStrategy throwResultStrategy) {

        for (int roundNumber = 1; roundNumber <= NUMBER_OF_ROUNDS; roundNumber++) {
            remainingRounds.add(new Round(roundNumber, throwResultStrategy));
        }

        currentRound = remainingRounds.peek();

        this.scoredCombinations = new HashSet<Combination>();
    }

    public void throwDice() {
        if (currentRound.getCurrentNumberOfThrows() >= Round.MAX_NUMBER_OF_THROWS_PER_ROUND) {
            throw new YahtzeeException("You cannot throw the dice more than "
                    + Round.MAX_NUMBER_OF_THROWS_PER_ROUND + " times per round!");
        }

        Throw newThrow = currentRound.getThrowResultStrategy().throwDice();
        Throw currentThrow = currentRound.getCurrentlyHeldDice().isEmpty()
                           ? newThrow
                           : currentRound.getCurrentThrow().mergeWith(newThrow, currentRound.getCurrentlyHeldDice());
        currentRound.setCurrentThrow(currentThrow);
        currentRound.incrementNumberOfThrows();
    }

    public void holdDice(Integer... positions) {
        currentRound.setCurrentlyHeldDice(Arrays.asList(positions));
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

        int score = scorer.score(combo, currentRound.getCurrentThrow());

        currentScore += score;

        remainingRounds.poll();
        currentRound = remainingRounds.peek();

        return score;
    }

    private boolean noThrowsYetInThisRound() {
        return currentRound.getCurrentThrow() == null;
    }

    public int finalScore() {
        return currentScore;
    }

    public Map<Integer, Integer> getCurrentThrowAsMap() {
        return currentRound.getCurrentThrow().asMap();
    }

    public boolean isOver() {
        return remainingRounds.isEmpty();
    }

    public int getCurrentRoundNumber() {
        return currentRound.getRoundNumber();
    }

    public Set<Combination> getRemainingCombos() {
        Set<Combination> allCombos
                = new HashSet<Combination>(Arrays.asList(Combination.values()));
        allCombos.removeAll(scoredCombinations);

        return new HashSet<Combination>(allCombos);
    }

}
