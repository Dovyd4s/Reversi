import java.util.ArrayList;
import java.util.List;

public class BoardMap {
    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
   private char[][] map = new char[8][8];
   private int posX = 3;
   private int posY = 3;
   private int move = 0;
   private boolean wasUnableToMoveLastTime = false;
   private boolean player1turnFlag = true;
   private final char player1Marker = 'O';
   private final char player2Marker = 'X';

    public BoardMap() {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                map[i][j]=' ';
            }
        }
    }

    public void printMap (){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
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
    public void printInfo(){
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
        if((isContiguousToOpponent() && !flippableCoordinates(posX, posY).isEmpty()) || move<4) {
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

    private boolean isContiguousToOpponent(){
        boolean valid = false;
        int xMin = posX;
        if(xMin>0)xMin-=1;
        int xMax = posX;
        if(xMax<7) xMax+=1;
        int yMin = posY;
        if(yMin >0)yMin-=1;
        int yMax = posY;
        if(yMax<7)yMax+=1;

        char opponentMarker = player1Marker;
        if(player1turnFlag){
            opponentMarker=player2Marker;
        }

        for(int i = yMin; i <=yMax; i++){
            for(int j = xMin; j<=xMax; j++){
                if(map[j][i]==opponentMarker){
                    valid=true;
                    break;
                }
            }
        }
        return valid;
    }

    private List<PairXY> flippableCoordinates(int posX, int posY){
        List<PairXY> coordinatesToSwitch = new ArrayList<>();
        List<PairXY> temp = new ArrayList<>();
        boolean opponentMarkersTrapped = false;
        char opponentMarker = player1Marker;
        char playersMarker = player2Marker;
        if(player1turnFlag){
            opponentMarker=player2Marker;
            playersMarker=player1Marker;
        }
        for(int i = posY+1; i < 8; i++){
            if(map[posX][i]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(posX, i));
            } else if (map[posX][i]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }
        temp.clear();
        opponentMarkersTrapped=false;
        for(int i = posY-1; i >= 0; i--){
            if(map[posX][i]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(posX, i));
            } else if (map[posX][i]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }

        temp.clear();
        opponentMarkersTrapped=false;
        for(int i = posX-1; i >= 0; i--){
            if(map[i][posY]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(i, posY));
            } else if (map[i][posY]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }
        temp.clear();
        opponentMarkersTrapped=false;
        for(int i = posX+1; i < 8; i++){
            if(map[i][posY]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(i, posY));
            } else if (map[i][posY]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                    temp.clear();
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }

        //Diagonal:
        temp.clear();
        opponentMarkersTrapped=false;
        for(int x = posX+1, y = posY+1; x < 8 && y < 8; x++, y++){
            if(map[x][y]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(x, y));
            } else if (map[x][y]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                    temp.clear();
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }

        temp.clear();
        opponentMarkersTrapped=false;
        for(int x = posX+1, y = posY-1; x < 8 && y >=0; x++, y--){
            if(map[x][y]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(x, y));
            } else if (map[x][y]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                    temp.clear();
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }

        temp.clear();
        opponentMarkersTrapped=false;
        for(int x = posX-1, y = posY-1; x >= 0 && y >=0; x--, y--){
            if(map[x][y]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(x, y));
            } else if (map[x][y]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                    temp.clear();
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }

        temp.clear();
        opponentMarkersTrapped=false;
        for(int x = posX-1, y = posY+1; x >= 0 && y < 8; x--, y++){
            if(map[x][y]==opponentMarker){
                opponentMarkersTrapped = true;
                temp.add(new PairXY(x, y));
            } else if (map[x][y]==playersMarker) {
                if(opponentMarkersTrapped){
                    coordinatesToSwitch.addAll(temp);
                    temp.clear();
                }
                break;
            } else {
                temp.clear();
                break;
            }
        }
        return coordinatesToSwitch;
    }

    public boolean isPossibleToMakeMove(){
        boolean flag = false;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
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
