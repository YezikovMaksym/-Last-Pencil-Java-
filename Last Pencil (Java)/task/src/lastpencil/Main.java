
package lastpencil;

import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        int number;
        String name;
        System.out.println("How many pencils would you like to use:");
        while (true) {
            String input = SC.nextLine();
            try {
                number = Integer.parseInt(input);
                if (number == 0) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                if (number < 0) {
                    System.out.println("The number of pencils should be numeric");
                    continue;
                }
                System.out.println("Who will be the first (John, Jack):");
                while (true) {
                    name = SC.next();
                    if (name.equals("John") || name.equals("Jack")) {
                        break;
                    } else {
                        System.out.println("Choose between 'John' and 'Jack'");
                    }
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
        char[] pencils = new char[number];
        choiceFirst(pencils, name);
        game(pencils, name);
    }

    public static void choiceFirst(char[] pencils, String name) {
        for (int i = 0; i < pencils.length; i++) {
            pencils[i] = '|';
            System.out.print(pencils[i]);
        }
        System.out.println("\n" + name + "'s turn!");
    }

    public static boolean botNext(String name) {
        return name.contains("Jack");
    }

    public static boolean playerNext(String name) {
        return name.contains("John");
    }

    public static char[] remainingPencils(char[] pencils, int take) {
        pencils = new char[pencils.length - take];
        for (int i = 0; i < pencils.length; i++) {
            pencils[i] = '|';
            System.out.print(pencils[i]);
        }
        return pencils;
    }

    public static String choiceNext(String name) {
        String nextTurn = name.equals("John") ? "Jack" : "John";
        System.out.println("\n" + nextTurn + "'s turn!");
        return nextTurn;
    }

    public static void game(char[] pencils, String name) {
        Random random = new Random();
        int take;
        while (true) {
            if (botNext(name)) {

                for (int i = 2; ; i += 4) {
                    if (pencils.length == 1) {
                        take = 1;
                        System.out.println(take);
                        break;
                    }
                    if (pencils.length == i) {
                        take = 1;
                        System.out.println(take);
                        break;
                    }
                    if (pencils.length == i + 1) {
                        take = 2;
                        System.out.println(take);
                        break;
                    }
                    if (pencils.length == i + 2) {
                        take = 3;
                        System.out.println(take);
                        break;
                    }
                    if (pencils.length == i + 3) {
                        take = random.nextInt(1, 4);
                        System.out.println(take);
                        break;
                    }
                }
                pencils = remainingPencils(pencils, take);
                if (pencils.length == 0) {
                    System.out.println("John won!");
                    break;
                }
                name = choiceNext(name);
            }
            if (playerNext(name)) {
                String input = SC.next();
                try {
                    take = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                if (take < 1 || take > 3) {
                    System.out.println("Possible values: '1', '2' or '3'");
                    continue;
                }
                try {
                    pencils = remainingPencils(pencils, take);
                    if (pencils.length == 0) {
                        System.out.println("Jack won!");
                        break;
                    }
                } catch (NegativeArraySizeException e) {
                    System.out.println("Too many pencils were taken");
                    continue;
                }
                name = choiceNext(name);
            }
        }
    }
}
