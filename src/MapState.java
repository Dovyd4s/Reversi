public class MapState {
    private char [][] map;
    private CoordinatesXY selector;
    public MapState(char [][] mapp, CoordinatesXY selector){
        this.map = new char [mapp.length][];
        for(int i = 0; i < mapp.length; i++){
            this.map[i] = mapp[i].clone();
        }
        this.selector = new CoordinatesXY(selector.getX(), selector.getY());
    }

    public char[][] getMap() {
        return this.map;
    }

    public CoordinatesXY getSelector() {
        return this.selector;
    }
}
