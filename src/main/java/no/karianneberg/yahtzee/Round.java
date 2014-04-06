package no.karianneberg.yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Round {

    static final int MAX_NUMBER_OF_THROWS_PER_ROUND = 3;
    private final int roundNumber;
    private Throw currentThrow;
    private List<Integer> currentlyHeldDice;
    private int currentNumberOfThrows = 0;
    private ThrowResultStrategy throwResultStrategy;

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

    public void setCurrentThrow(Throw currentThrow) {
        this.currentThrow = currentThrow;
    }

    public List<Integer> getCurrentlyHeldDice() {
        return currentlyHeldDice;
    }

    public void setCurrentlyHeldDice(List<Integer> currentlyHeldDice) {
        this.currentlyHeldDice = currentlyHeldDice;
    }

    public int getCurrentNumberOfThrows() {
        return currentNumberOfThrows;
    }

    public void incrementNumberOfThrows() {
        this.currentNumberOfThrows++;
    }

    public ThrowResultStrategy getThrowResultStrategy() {
        return throwResultStrategy;
    }

    public void setThrowResultStrategy(ThrowResultStrategy throwResultStrategy) {
        this.throwResultStrategy = throwResultStrategy;
    }
}
