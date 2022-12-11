import java.util.Stack;

public class GameRules implements StatsPrinter {
    private final int CENTERED_MOVES = 4;
    private int moveCount = 0;
    private boolean gameOver = false;
    private BoardMap gameMap;
    private Player player1;
    private Player player2;
    private boolean players1Turn = true;
    private Stack<MapState> savedMapStates = new Stack<>();
    private Stack<GameState> savedGameStates = new Stack<>();

    public void processInput(String input) {
        switch (input){
            case "a":
                gameMap.moveLeft();
                break;
            case "d":
                gameMap.moveRight();
                break;
            case "w":
                gameMap.moveUp();
            break;
            case "s":
                gameMap.moveDown();
            break;
            case "r":
                changePlayersTurn();
            break;
            case "q":
                gameOver = true;
                break;
            case " ":
                if(gameMap.isSelectorOnEmptyCell() && (gameMap.isSelectorInCentre() || (moveCount>=CENTERED_MOVES && !gameMap.flippableCoordinates(getOpponent().getSymbol(), getPlayingPlayer().getSymbol()).isEmpty()))){
                    saveState();
                    gameMap.markAndFlipTrapped(getPlayingPlayer().getSymbol(), getOpponent().getSymbol());
                    changePlayersTurn();
                    countScore(player1);
                    countScore(player2);
                    moveCount++;
                }
                if((moveCount>=CENTERED_MOVES) && !gameMap.isPossibleToMakeMove(getOpponent().getSymbol(), getPlayingPlayer().getSymbol())){
                    changePlayersTurn();
                    if(gameMap.isPossibleToMakeMove(getOpponent().getSymbol(), getPlayingPlayer().getSymbol())){
                        System.out.println("There was no possible moves for player \"" + getOpponent().getSymbol() + "\". Player's turn has changed.");
                    }else{
                        System.out.println("There is no possible moves for any player. GAME OVER!");
                        gameOver = true;
                    }
                }
            break;
            case "z":
                undoState();
                countScore(player1);
                countScore(player2);
                break;
        }
    }
    private void countScore(Player player){
       player.setScore(gameMap.countSymbolsInMap(player.getSymbol()));
    }
    private void changePlayersTurn(){
        players1Turn = !players1Turn;
    }
    private Player getOpponent(){
        if(players1Turn){
            return player2;
        }else{
            return player1;
        }
    }
    private Player getPlayingPlayer(){
        if(players1Turn){
            return player1;
        }else{
            return player2;
        }
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    public GameRules(BoardMap gameMap, Player player1, Player player2) {
        this.gameMap = gameMap;
        this.player1 = player1;
        this.player2 = player2;
    }
    @Override
    public void printStats() {
        System.out.println("Move no.: " + moveCount);
        player1.printStats();
        player2.printStats();
        if(!gameOver){
            System.out.println("Player's \"" + getPlayingPlayer().getSymbol() + "\" turn.");
        }
    }
    public void saveState(){
        savedMapStates.push(gameMap.save());
        savedGameStates.push(this.save());
    }
    public void undoState(){
        if(!savedMapStates.empty()){
            gameMap.restore(savedMapStates.pop());
            this.restore(savedGameStates.pop());
        }
    }
    public GameState save(){
        return new GameState(players1Turn,moveCount);
    }
    public void restore(GameState gameState){
        this.players1Turn = gameState.isPlayers1Turn();
        this.moveCount = gameState.getMoveCount();
    }
}
