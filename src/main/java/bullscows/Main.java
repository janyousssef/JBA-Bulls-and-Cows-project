package bullscows;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import static java.lang.Math.min;

class Code {
    private final int DIGITS_ALLOWED;
    private final int CODE_LENGTH;
    private final String code;

    Code(int CODE_LENGTH, int DIGITS_ALLOWED) {
        this.CODE_LENGTH = CODE_LENGTH;
        this.DIGITS_ALLOWED = DIGITS_ALLOWED;
        this.code = makeCode();
        System.out.println(creationMessage());
    }

    private String creationMessage() {
        StringBuilder s = new StringBuilder("The secret is prepared: ");
        s.append("*".repeat(CODE_LENGTH));
        s.append(" (0-").append(min(DIGITS_ALLOWED - 1,9));

        if(DIGITS_ALLOWED>10) {
            s.append(", a-").append((char)('a'+DIGITS_ALLOWED-11));
        }

        s.append(").");
        return s.toString();
    }


    public String grade(String input) {
        String res = "";

        int bulls = calculateBulls(input);
        int cows = CalculateCows(input) - bulls;

        if (bulls > 0) {
            res += bulls + " bull(s)";
        }
        if (cows > 0) {
            if (!res.isEmpty()) res += " and ";
            res += cows + " cows(s)";
        }


        if (!res.isEmpty()) return res;
        return "None";

    }

    private int calculateBulls(String input) {
        int num = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            if (code.charAt(i) == input.charAt(i)) num++;
        }
        return num;
    }

    private int CalculateCows(String input) {
        int num = 0;
        for (int i = 0; i < CODE_LENGTH; i++) {
            for (int j = 0; j < CODE_LENGTH; j++) {
                if (input.charAt(i) == code.charAt(j)) num++;
            }
        }
        return num;
    }

    private String makeCode() {
        String[] allChars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] neededChars = Arrays.copyOf(allChars, DIGITS_ALLOWED);
        Collections.shuffle(Arrays.asList(neededChars));
        neededChars = Arrays.copyOf(neededChars, CODE_LENGTH);
        return String.join("", neededChars);
    }

    public String getCode() {
        return code;
    }

    public int getCODE_LENGTH() {
        return CODE_LENGTH;
    }
}

class Game {
    private final Code code;
    private int turn = 1;

    public Game(Code code) {
        this.code = code;
    }

    void start() {
        String guess;
        System.out.println("Okay, let's start a game!");
        do {
            System.out.println("Turn " + this.getAndIncrementTurn() + ":");
            guess = Main.sc.next();
            System.out.println("Grade: " + code.grade(guess));
        } while (hasNotEnded(guess));
        System.out.println("Congrats! The secret code is " + code.getCode());
    }

    public boolean hasNotEnded(String guess) {
        return !code.grade(guess).equals(code.getCODE_LENGTH() + " bull(s)");
    }

    public int getAndIncrementTurn() {
        return turn++;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        int length;
        int allowedCharacters;
        try {
            length = getDesiredCodeLength();
            allowedCharacters = getNumberOfAllowedCharacters();
            if(allowedCharacters>36) System.out.println("Error: maximum number of possible symbols" +
                    " in the code is 36 (0-9, a-z).");
            else if (allowedCharacters<length) System.out.println("Error: it's not possible to generate a code with a length of "+length+" with "+allowedCharacters+" unique symbols.");
            else {
                Code code = new Code(length, allowedCharacters);
                Game game = new Game(code);

                game.start();
            }
        } catch (Exception e) {
            System.out.println("Error, invalid number");
        }

    }

    private static int getDesiredCodeLength() {
        int length;
        System.out.println("Please, enter the secret code's length:");
        length = sc.nextInt();
        if (length < 1 ) {
            throw new RuntimeException();
        }
        return length;
    }

    private static int getNumberOfAllowedCharacters() {
        int length;
        System.out.println("Input the number of possible symbols in the code:");
        length = sc.nextInt();
        return length;
    }
}