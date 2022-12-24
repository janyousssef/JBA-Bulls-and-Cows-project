package cowsbulls;

class Code {
    private String code;

    public String makeCode() {
        return null;
    }

    public int bulls() {
        return 1;
    }

    public int cows() {
        return 1;
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
        Game game = new Game();
        Code code = new Code();
        System.out.println("The secret code is prepared: ****.");
        for (int i = 0; i < 4; i++) {
            System.out.printf("Turn %d. Answer:%n", game.getAndIncrementTurn());
            System.out.println("4734");
            System.out.println("Grade: " + code.bulls() + " bull and " + code.cows() + " cow.");
        }
        System.out.println("Congrats! The secret code is 9876.");
    }
}