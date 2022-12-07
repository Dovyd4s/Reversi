import java.util.ArrayList;
import java.util.List;

public class BoardMap implements StatsPrinter{
   final static int BOARD_SIZE = 8;
   private int scorePlayer1 = 0;
   private int scorePlayer2 = 0;
   private char[][] map = new char[BOARD_SIZE][BOARD_SIZE];
   private int posX = 3;
   private int posY = 3;
   private int move = 0;
   private boolean wasUnableToMoveLastTime = false;
   private boolean player1turnFlag = true;
   private final char player1Marker = 'O';
   private final char player2Marker = 'X';

    public BoardMap() {
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                map[i][j]=' ';
            }
        }
    }

    public void printMap (){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                if(posX == j && posY == i){
                    if(map[j][i]==' '){
                        System.out.print("{_}");
                    }else{
                    System.out.print("{"+map[j][i]+"}");
                    }
                }else{
                    System.out.print("["+map[j][i]+"]");
                }
                if(j == 7){
                    System.out.println();
                }
            }
        }
    }
    @Override
    public void printStats(){
        System.out.println("Move nr.: " + move);
        System.out.print("It's turn for: ");
        if(player1turnFlag){
            System.out.println(player1Marker);
        }else{
            System.out.println(player2Marker);
        }
        countScores();
        System.out.println("Player's 1 (" + player1Marker + ") score: " + scorePlayer1);
        System.out.println("Player's 2 (" + player2Marker + ") score: " + scorePlayer2);
    }

    public boolean markAndChangeTurns() {
        if((!flippableCoordinates(posX, posY).isEmpty()) || move<4) {
            if (map[posX][posY] == ' ') {
                flipMarkers(flippableCoordinates(posX, posY));
                if (player1turnFlag) {
                    map[posX][posY] = player1Marker;
                } else {
                    map[posX][posY] = player2Marker;
                }
                move+=1;
                changeTurn();
                return true;
            }
        }
        return false;
    }


    private List<PairXY> flippableCoordinatesOnOneDirection(int posX, int posY, int dX, int dY, char opponentMarker, char playersMarker){
        List<PairXY> coordinatesToSwitch = new ArrayList<>();
        List<PairXY> temp = new ArrayList<>();
        boolean opponentMarkersTrapped = false;

        for (int x = (posX + dX), y = (posY + dY); x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE; x+=dX, y+=dY) {
            if (map[x][y] == opponentMarker) {
                opponentMarkersTrapped = true;
                temp.add(new PairXY(x, y));
            } else {
                if (map[x][y] == playersMarker) {
                    if (opponentMarkersTrapped) {
                        coordinatesToSwitch.addAll(temp);
                    }
                }
                break;
            }
        }
        return coordinatesToSwitch;
    }


    private List<PairXY> flippableCoordinates(int posX, int posY){
        List<PairXY> coordinatesToSwitch = new ArrayList<>();
        char opponentMarker = player1Marker;
        char playersMarker = player2Marker;
        if(player1turnFlag){
            opponentMarker=player2Marker;
            playersMarker=player1Marker;
        }
        for(int i = -1; i<=1; i++){
            for(int j = -1; j <=1; j++){
                if(!(i==0 && j ==0)){
                    coordinatesToSwitch.addAll(flippableCoordinatesOnOneDirection(posX,posY,i,j,opponentMarker,playersMarker));
                }
            }
        }
        return coordinatesToSwitch;
    }

    public boolean isPossibleToMakeMove(){
        boolean flag = false;
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                if(map[i][j]==' '){
                    if(!flippableCoordinates(i,j).isEmpty()){
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public void changeTurn(){
        if (player1turnFlag) {
            player1turnFlag = false;
        } else {
            player1turnFlag = true;
        }
    }

    private void flipMarkers(List<PairXY> coordinatesList){
        for(PairXY pair : coordinatesList){
            if(player1turnFlag){
                map[pair.getX()][pair.getY()]=player1Marker;
            }else{
                map[pair.getX()][pair.getY()]=player2Marker;
            }
        }
    }

    private void countScores(){
        scorePlayer1 = 0;
        scorePlayer2 = 0;
        for(int i = 0; i < 8; i ++){
            for(int j = 0 ; j < 8; j++){
                if(map[i][j]==player1Marker){
                    scorePlayer1++;
                }else if(map[i][j]==player2Marker){
                    scorePlayer2++;
                }
            }
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        if(move<4){
            if(posX==3 || posX==4){
                this.posX=posX;
            }
        }else if(posX>=0 && posX<=7){
            this.posX = posX;
        }
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        if(move<4){
            if(posY==3 || posY==4){
                this.posY = posY;
            }
        }else if(posY>=0 && posY <=7){
            this.posY = posY;
        }
    }

    public int getMove() {
        return move;
    }
}
