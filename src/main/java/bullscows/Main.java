package bullscows;

import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Code {
    private int CODE_LENGTH;
    private String code = "";

    Code(int CODE_LENGTH) {
        if (CODE_LENGTH <= 10) {
            this.CODE_LENGTH = CODE_LENGTH;
            makeCode();
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

    private void makeCode() {
        String[] arr = generateRandomNumbers();
        Set<String> usedNums = new HashSet<>();
        while (usedNums.size() < CODE_LENGTH) {
            String t = arr[(int) (Math.random() * 10)];
            if (!usedNums.contains(t)) {
                usedNums.add(t);
                code += t;
            }
        }


    }

    private String[] generateRandomNumbers() {
        Set<String> set = new HashSet<>();
        while (set.size() < 10) {
            double l = Math.random();
            String s = Double.toString(l).replaceAll("\\.", "");
            Collections.addAll(set, s.split(""));
        }

        return set.toArray(new String[10]);
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