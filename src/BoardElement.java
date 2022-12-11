public abstract class BoardElement {
    private char symbol;
    abstract void printSymbol();

    public char getSymbol() {
        return symbol;
    }
    public String getSymbolRed(){
        return "\u001B[31m" + symbol + "\u001B[0m";
    }

    public BoardElement() {
    }

    public BoardElement(char symbol) {
        this.symbol = symbol;
    }
}
