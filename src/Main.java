import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MapState savedMapState;
        Player player1 = new Player('0');
        Player player2 = new Player('X');
        String input = "";
        BoardMap game = BoardMap.getInstance();
        GameRules gameRules = new GameRules(game, player1, player2);
        Scanner scanner = new Scanner(System.in);

        while(!gameRules.isGameOver()){
            gameRules.printStats();
            game.printMap();
            input = scanner.nextLine();
            gameRules.processInput(input);
        }
        game.printMap();
        gameRules.printStats();
    }

}