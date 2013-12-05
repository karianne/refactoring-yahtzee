package no.karianneberg.yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Round {
    static final int MAX_NUMBER_OF_THROWS_PER_ROUND = 3;
    private final int roundNumber;
    private ThrowResultStrategy throwResultStrategy;
    private Throw currentThrow;
    private List<Integer> currentlyHeldDice;
    private int currentNumberOfThrows = 0;

    public Round(int roundNumber, ThrowResultStrategy throwResultStrategy) {
        this.roundNumber = roundNumber;
        this.throwResultStrategy = throwResultStrategy;
        this.currentlyHeldDice = new ArrayList<Integer>();
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public Throw getCurrentThrow() {
        return currentThrow;
    }

    public void holdDice(List<Integer> currentlyHeldDice) {
        this.currentlyHeldDice = currentlyHeldDice;
    }

    public int getCurrentNumberOfThrows() {
        return currentNumberOfThrows;
    }

    void throwDice() {
        if (getCurrentNumberOfThrows() >= MAX_NUMBER_OF_THROWS_PER_ROUND) {
            throw new YahtzeeException("You cannot throw the dice more than "
                    + MAX_NUMBER_OF_THROWS_PER_ROUND + " times per round!");
        }

        Throw newThrow = throwResultStrategy.throwDice();
        this.currentThrow = currentlyHeldDice.isEmpty()
                           ? newThrow
                           : currentThrow.mergeWith(newThrow, currentlyHeldDice);
        this.currentNumberOfThrows++;
    }

    boolean noThrowsYetInThisRound() {
        return currentThrow == null;
    }
}
