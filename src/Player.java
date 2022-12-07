public class Player extends BoardElement implements StatsPrinter{
    private int score = 0;

    public Player(char symbol) {
        super(symbol);
    }

    @Override
    void printSymbol() {

    }

    @Override
    public void printStats() {
        System.out.println("Player's \"" + getSymbol() + "\" score is: " + score);
    }
}
