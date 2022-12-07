public class GameRules {
    private boolean gameOver = false;
    private BoardMap gameMap;

    public void processInput(String input) {
        switch (input){
            case "a":
                gameMap.setPosX(gameMap.getPosX() - 1);
                break;
            case "d":
                gameMap.setPosX(gameMap.getPosX() + 1);
                break;
            case "w":
                gameMap.setPosY(gameMap.getPosY() - 1);
            break;
            case "s":
                gameMap.setPosY(gameMap.getPosY() + 1);
            break;
            case "r":
                gameMap.changeTurn();
            break;
            case "q":
                gameOver = true;
                break;
            case " ":
                if (gameMap.markAndChangeTurns() && gameMap.getMove() > 3) {
                    if (gameMap.isPossibleToMakeMove()) {
                        System.out.println("ALL GOOOOOOD!!!");
                    } else {
                        gameMap.changeTurn();
                        if (gameMap.isPossibleToMakeMove()) {
                            System.out.println("There was no move possible. Player's turn passed");
                        } else {
                            setGameOver(true);
                            System.out.println("Game OVER. No moves were possible!");
                            gameMap.printMap();
                            gameMap.printStats();
                        }
                    }
                }
                break;
            }
        }


    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameRules(BoardMap gameMap) {
        this.gameMap = gameMap;
    }
}
