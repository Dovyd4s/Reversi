public class GameState {
    private boolean players1Turn;
    private int moveCount;
    public GameState(boolean players1Turn, int moveCount){
        this.players1Turn = players1Turn;
        this.moveCount = moveCount;
    }

    public boolean isPlayers1Turn() {
        return players1Turn;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
