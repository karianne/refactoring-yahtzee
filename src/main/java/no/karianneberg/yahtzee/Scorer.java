package no.karianneberg.yahtzee;

public class Scorer {
    public Scorer() {
    }

    int score(Combination combo, Throw currentThrow) {
        int score = 0;

        switch (combo) {
            case ONES:
                score = scoreForNumberOfDiceWithValue(1, currentThrow);
                break;
            case TWOS:
                score = scoreForNumberOfDiceWithValue(2, currentThrow);
                break;
            case THREES:
                score = scoreForNumberOfDiceWithValue(3, currentThrow);
                break;
            case FOURS:
                score = scoreForNumberOfDiceWithValue(4, currentThrow);
                break;
            case FIVES:
                score = scoreForNumberOfDiceWithValue(5, currentThrow);
                break;
            case SIXES:
                score = scoreForNumberOfDiceWithValue(6, currentThrow);
                break;
            case ONE_PAIR:
                score = scoreForNumberOfAKind(2, currentThrow);
                break;
            case TWO_PAIRS:
                TwoPairs result = currentThrow.findTwoPairs();
                if (result == null) {
                    score = 0;
                } else {
                    score = (result.getFirst() * 2)
                            + (result.getSecond() * 2);
                }
                break;
            case THREE_OF_A_KIND:
                score = scoreForNumberOfAKind(3, currentThrow);
                break;
            case FOUR_OF_A_KIND:
                score = scoreForNumberOfAKind(4, currentThrow);
                break;
            case FULL_HOUSE:
                FullHouse fullHouse = currentThrow.findFullHouse();
                if (fullHouse == null) {
                    score = 0;
                } else {
                    score = (fullHouse.getFirst() * 2)
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
        return score;
    }

    int scoreForNumberOfAKind(int numberOfAKind, Throw currentThrow) {
        int value = currentThrow.findNumberOfAKind(numberOfAKind);
        return value * numberOfAKind;
    }

    int scoreForNumberOfDiceWithValue(int value, Throw currentThrow) {
        int num = currentThrow.findNumberOfDiceWithValue(value);
        return num * value;
    }
}