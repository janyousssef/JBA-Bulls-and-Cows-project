package bullscows;

import java.util.*;
import java.util.stream.Collectors;

class Code {
    private int CODE_LENGTH;
    private String code = "";

    Code(int CODE_LENGTH) {
        if (CODE_LENGTH <= 10) {
            this.CODE_LENGTH = CODE_LENGTH;
            this.code=makeCode();
            System.out.println("The random secret number is " + code);
        } else
            System.out.println("Error: can't generate a secret number with a length of " + CODE_LENGTH + " because there aren't enough unique digits.");
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


        return buildResult(res);

    }

    private String buildResult(String res) {
        String s = ". The secret code is " + code + ".";

        if (!res.isEmpty()) return res + s;
        return "None" + s;
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
        String [] allChars={"0","1","2","3","4","5","6","7","8","9","a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        String[] neededChars = Arrays.copyOf(allChars, CODE_LENGTH);
        Collections.shuffle(Arrays.asList(neededChars));
        return String.join("", neededChars);
    }

}

class Game {
    private static int turn = 1;

    public int getAndIncrementTurn() {
        return turn++;
    }
}

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();

        Game game = new Game();
        Code code = new Code(input);

        //System.out.println("The secret code is prepared: ****.");

        //System.out.println(code.grade(input));

        //System.out.println("Congrats! The secret code is 9876.");
    }
}