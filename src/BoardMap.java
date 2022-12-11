import java.util.ArrayList;
import java.util.List;

public class BoardMap {
   final private Selector OPEN_SELECTOR = new Selector('{');
   final private Selector CLOSE_SELECTOR = new Selector('}');
   final static int BOARD_SIZE = 8;
   final int [] CENTER_POSITIONS = {3,4};
   final char EMPTY_CELL_MARKER = ' ';
   private CoordinatesXY selector = new CoordinatesXY(3,3);
   private char[][] map = new char[BOARD_SIZE][BOARD_SIZE];

   public static BoardMap instance = null;
    private BoardMap() {
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                map[i][j]=EMPTY_CELL_MARKER;
            }
        }
    }
    public static BoardMap getInstance(){
        if(instance == null){
            instance = new BoardMap();
        }
        return instance;
    }
    public void printMap (){
        for(int i = 0; i < BOARD_SIZE; i++){
            for(int j = 0; j < BOARD_SIZE; j++){
                if(selector.getX() == j && selector.getY() == i){
                    System.out.print(OPEN_SELECTOR.getSymbolRed() + map[j][i] + CLOSE_SELECTOR.getSymbolRed());
                }else{
                    System.out.print("["+map[j][i]+"]");
                }
                if(j == BOARD_SIZE-1){
                    System.out.println();
                }
            }
        }
    }
    public void markAndFlipTrapped(char playersMarker, char opponentMarker){
        if(map[selector.getX()][selector.getY()] == EMPTY_CELL_MARKER){
            flipMarkers(flippableCoordinates(opponentMarker, playersMarker),playersMarker);
            map[selector.getX()][selector.getY()] = playersMarker;
        }
    }
    private List<CoordinatesXY> flippableCoordinatesOnOneDirection(CoordinatesXY coordinates, int dX, int dY, char opponentMarker, char playersMarker){
        List<CoordinatesXY> coordinatesToSwitch = new ArrayList<>();
        List<CoordinatesXY> temp = new ArrayList<>();
        boolean opponentMarkersTrapped = false;

        for (int x = (coordinates.getX() + dX), y = (coordinates.getY() + dY); x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE; x+=dX, y+=dY) {
            if (map[x][y] == opponentMarker) {
                opponentMarkersTrapped = true;
                temp.add(new CoordinatesXY(x, y));
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
    public List<CoordinatesXY> flippableCoordinates(CoordinatesXY coordinates, char opponentMarker, char playersMarker){
        List<CoordinatesXY> coordinatesToSwitch = new ArrayList<>();
        int [] deltaValues = {-1,0,1};

        for(int dY : deltaValues){
            for(int dX : deltaValues){
                if(!(dX == 0 && dY == 0)){
                    coordinatesToSwitch.addAll(flippableCoordinatesOnOneDirection(coordinates,dX,dY,opponentMarker,playersMarker));
                }
            }
        }
        return coordinatesToSwitch;
    }
    public List<CoordinatesXY> flippableCoordinates(char opponentMarker, char playersMarker){
        List<CoordinatesXY> coordinatesToSwitch = new ArrayList<>();
        int [] deltaValues = {-1,0,1};

        for(int dY : deltaValues){
            for(int dX : deltaValues){
                if(!(dX == 0 && dY == 0)){
                    coordinatesToSwitch.addAll(flippableCoordinatesOnOneDirection(selector,dX,dY,opponentMarker,playersMarker));
                }
            }
        }
        return coordinatesToSwitch;
    }
    public boolean isPossibleToMakeMove(char opponentSymbol, char playersSymbol){
        boolean flag = false;
        CoordinatesXY coordinates = new CoordinatesXY(0,0);
        for(int i = 0; i < BOARD_SIZE; i++){
            coordinates.setY(i);
            for(int j = 0; j < BOARD_SIZE; j++){
                coordinates.setX(j);
                if(map[i][j]==EMPTY_CELL_MARKER){
                    if(!flippableCoordinates(coordinates, opponentSymbol, playersSymbol).isEmpty()){
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
    public boolean isSelectorOnEmptyCell(){
        if(map[selector.getX()][selector.getY()]==EMPTY_CELL_MARKER){
            return true;
        }else return false;
    }
    private void flipMarkers(List<CoordinatesXY> coordinatesList, char playersMarker){
        for(CoordinatesXY pair : coordinatesList){
            map[pair.getX()][pair.getY()]=playersMarker;
        }
    }
    public int countSymbolsInMap(char symbol){
        int count = 0;
        for(int i = 0; i < 8; i ++){
            for(int j = 0 ; j < 8; j++){
                if(map[i][j]==symbol){
                    count++;
                }
            }
        }
        return count;
    }
    public void moveUp(){
        if(selector.getY()>0){
            selector.setY(selector.getY()-1);
        }
    }
    public void moveDown(){
        if(selector.getY()<BOARD_SIZE-1){
            selector.setY(selector.getY()+1);
        }
    }
    public void moveRight(){
        if(selector.getX()<BOARD_SIZE-1){
            selector.setX(selector.getX()+1);
        }
    }
    public CoordinatesXY getSelector() {
        return selector;
    }
    public void moveLeft(){
        if(selector.getX()>0){
            selector.setX(selector.getX()-1);
        }
    }
    public boolean isSelectorInCentre(){
        boolean xInCenter = false;
        boolean yInCenter = false;
        for(int x: CENTER_POSITIONS){
            if (selector.getX()==x){
                xInCenter=true;
            }
            if(selector.getY()==x){
                yInCenter=true;
            }
        }
        return (xInCenter&&yInCenter);
    }
    public MapState save(){
        return new MapState(map,selector);
    }
    public void restore (MapState mapState){
        this.map = mapState.getMap();
        this.selector = mapState.getSelector();
    }
    public char[][] getMap() {
        return map;
    }
    public void setMap(char[][] map) {
        this.map = map;
    }
}
