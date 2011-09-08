package no.karianneberg.yahtzee;

import java.util.*;

public class Throw {

    private List<Integer> dice;

    public Throw(Integer... dice) {
        this.dice = Arrays.asList(dice);
        Collections.sort(this.dice);
        Collections.reverse(this.dice);
    }

    public int findNumberOfDiceWithValue(int value) {
        int number = 0;

        for (Integer val : dice) {
            if (val.equals(value)) {
                number++;
            }
        }

        return number;
    }

    public int findNumberOfAKind(int number) {
        int numberOfAKindValue = 0;

        for (int i = 0; (i < dice.size() - number + 1)
                     && (numberOfAKindValue <= 0); i++) {
            if (dice.get(i).equals(dice.get(i + number - 1))) {
                numberOfAKindValue = dice.get(i);
            }
        }

        return numberOfAKindValue;
    }

    public TwoPairs findTwoPairs() {
        int firstPairValue = 0;
        int secondPairValue = 0;

        if (leftoverDieLast(dice)) {
            firstPairValue = dice.get(0);
            secondPairValue = dice.get(2);
        } else if (leftoverDieInTheMiddle(dice)) {
            firstPairValue = dice.get(0);
            secondPairValue = dice.get(3);
        } else if (leftoverDieFirst(dice)) {
            firstPairValue = dice.get(1);
            secondPairValue = dice.get(3);
        }
        return new TwoPairs(firstPairValue, secondPairValue);
    }

    private boolean leftoverDieFirst(List<Integer> theDice) {
        return     theDice.get(1).equals(theDice.get(2))
                && theDice.get(3).equals(theDice.get(4));
    }

    private boolean leftoverDieInTheMiddle(List<Integer> theDice) {
        return     theDice.get(0).equals(theDice.get(1))
                && theDice.get(3).equals(theDice.get(4));
    }

    private boolean leftoverDieLast(List<Integer> theDice) {
        return     theDice.get(0).equals(theDice.get(1))
                && theDice.get(2).equals(theDice.get(3));
    }

    public FullHouse findFullHouse() {
        int pairValue = 0;
        int threeOfAKindValue = 0;

        if (fullHouseWithPairLast(dice)) {
            threeOfAKindValue = dice.get(0);
            pairValue = dice.get(3);
        } else if (fullHouseWithPairFirst(dice)) {
            pairValue = dice.get(0);
            threeOfAKindValue = dice.get(2);
        }

        return new FullHouse(pairValue, threeOfAKindValue);
    }

    private boolean fullHouseWithPairFirst(List<Integer> dice) {
        return     dice.get(0).equals(dice.get(1))
                && dice.get(2).equals(dice.get(4));
    }

    private boolean fullHouseWithPairLast(List<Integer> dice) {
        return     dice.get(0).equals(dice.get(2))
                && dice.get(3).equals(dice.get(4));
    }

    public boolean isSmallStraight() {
        return dice.equals(Arrays.asList(5, 4, 3, 2,1));
    }

    public boolean isLargeStraight() {
        return dice.equals(Arrays.asList(6, 5, 4, 3, 2));
    }

    public int calculateSumOfDice() {
        int sum = 0;
        for (Integer integer : dice) {
            sum += integer;
        }
        return sum;
    }

    public boolean isYahtzee() {
        Collections.sort(dice);
        return dice.get(0).equals(dice.get(4));
    }

    public Map<Integer, Integer> asMap() {
        HashMap<Integer, Integer> diceInPositions = new HashMap<Integer, Integer>();
        for (int i = 0; i < dice.size(); i++) {
            diceInPositions.put(i, dice.get(i));
        }

        return diceInPositions;
    }

    public Throw mergeWith(Throw newThrow, List<Integer> currentlyHeldDice) {
        Map<Integer, Integer> throwMap = newThrow.asMap();
        List<Integer> values = new ArrayList<Integer>();

        for (Integer key : throwMap.keySet()) {
            if (currentlyHeldDice.contains(key)) {
                throwMap.put(key, dice.get(key));
            }

            values.add(key, throwMap.get(key));
        }

        return new Throw(values.toArray(new Integer[throwMap.size()]));
    }
}