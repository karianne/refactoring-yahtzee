package no.karianneberg.yahtzee;

import java.util.*;

public class Throw {

    private List<Integer> dice;

    public Throw(Integer... dice) {
        this.dice = Arrays.asList(dice);
        Collections.sort(this.getDice());
        Collections.reverse(this.getDice());
    }

    public Map<Integer, Integer> asMap() {
        HashMap<Integer, Integer> diceInPositions = new HashMap<Integer, Integer>();
        for (int i = 0; i < getDice().size(); i++) {
            diceInPositions.put(i, getDice().get(i));
        }

        return diceInPositions;
    }

    public Throw mergeWith(Throw newThrow, List<Integer> currentlyHeldDice) {
        Map<Integer, Integer> throwMap = newThrow.asMap();
        List<Integer> values = new ArrayList<Integer>();

        for (Integer key : throwMap.keySet()) {
            if (currentlyHeldDice.contains(key)) {
                throwMap.put(key, getDice().get(key));
            }

            values.add(key, throwMap.get(key));
        }

        return new Throw(values.toArray(new Integer[throwMap.size()]));
    }

    public List<Integer> getDice() {
        return dice;
    }

}