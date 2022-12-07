public abstract class BoardElement {
    private char symbol;
    abstract void printSymbol();

    public char getSymbol() {
        return symbol;
    }

    public BoardElement(char symbol) {
        this.symbol = symbol;
    }
}
