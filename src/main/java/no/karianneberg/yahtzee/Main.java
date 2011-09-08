package no.karianneberg.yahtzee;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        YahtzeeGame game = new YahtzeeGame(new RandomThrowStrategy());
        Scanner scanner = new Scanner(System.in);

        while (!game.isOver()) {
            System.out.println("ROUND " + game.getCurrentRoundNumber());
            game.throwDice();
            System.out.println("Throw: " + game.getCurrentThrowAsMap());

            int cmd = 0;
            do {
                System.out.println("1: Score;    2: Hold dice;    3: Rethrow");
                cmd = scanner.nextInt();

                if(cmd == 2) {
                    System.out.print("Type in dice to hold (comma separated): ");
                    String[] heldDice = scanner.next().split(",");
                    Integer[] theDice = new Integer[heldDice.length];
                    for (int i = 0, heldDiceLength = heldDice.length; i < heldDiceLength; i++) {
                        String die = heldDice[i];
                        theDice[i] = Integer.parseInt(die);
                    }

                    game.holdDice(theDice);
                    game.throwDice();
                    System.out.println("Throw: " + game.getCurrentThrowAsMap());
                } else if(cmd == 3) {
                    game.throwDice();
                    System.out.println("Throw: " + game.getCurrentThrowAsMap());
                }
            } while (cmd != 1);

            for (Combination combo : game.getRemainingCombos()) {
                System.out.println(combo);
            }
            System.out.print("Choose which combination to score: ");
            String choice = scanner.next();
            game.scoreFor(Combination.valueOf(choice));
        }

        System.out.println("Game over, you got " + game.finalScore() + " points!");

    }
}
