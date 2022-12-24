package cowsbulls;

import java.util.Scanner;

class Code {
    private String code;
    private final int codeLength = 4;

    public Code() {
        makeCode();
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

        if (!res.isEmpty())
            return res + s;
        return "None" + s;
    }

    private int calculateBulls(String input) {
        int num = 0;
        for (int i = 0; i < codeLength; i++) {
            if (code.charAt(i) == input.charAt(i)) num++;
        }
        return num;
    }

    private int CalculateCows(String input) {
        int num = 0;
        for (int i = 0; i < codeLength; i++) {
            for (int j = 0; j < codeLength; j++) {
                if (input.charAt(i) == code.charAt(j)) num++;
            }
        }
        return num;
    }

    private void makeCode() {
        code = "9305";

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
        String input = sc.next();

        Game game = new Game();
        Code code = new Code();

        //System.out.println("The secret code is prepared: ****.");

        System.out.println(code.grade(input));

        //System.out.println("Congrats! The secret code is 9876.");
    }
}