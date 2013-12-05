package no.karianneberg.yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Round {
    private final int roundNumber;
    private Throw currentThrow;
    private List<Integer> currentlyHeldDice;
    private int currentNumberOfThrows = 0;

    public Round(int roundNumber) {
        this.roundNumber = roundNumber;
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
}
