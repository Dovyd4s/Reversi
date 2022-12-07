import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player player1 = new Player('0');
        Player player2 = new Player('X');
        String input = "";
        BoardMap game = new BoardMap();
        GameRules gameRules = new GameRules(game);
        Scanner scanner = new Scanner(System.in);

        while(!gameRules.isGameOver()){
            game.printMap();
            game.printStats();
            input = scanner.nextLine();

            gameRules.processInput(input);

        }
    }
}