import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardMapFlippableCoordinatesTest {
/*
char map [][] = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };
*/
    @Test
    void flippableCoordinatesAtTop() {
        char map [][] = {
                {' ', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        BoardMap boardMap = BoardMap.getInstance();
        boardMap.setMap(map);
        String answer = boardMap.flippableCoordinates(new CoordinatesXY(0,0), 'O','X').toString();
        assertEquals("[{x=0, y=1}, {x=0, y=2}, {x=0, y=3}, {x=0, y=4}, {x=0, y=5}, {x=0, y=6}]", answer);
    }
    @Test
    void flippableCoordinatesV2() {
        char map [][] = {
                {' ', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
                {'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '},
                {'O', ' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {'O', ' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {'O', ' ', ' ', ' ', 'O', ' ', ' ', ' '},
                {'O', ' ', ' ', ' ', ' ', 'O', ' ', ' '},
                {'O', ' ', ' ', ' ', ' ', ' ', 'O', ' '},
                {'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X'}
        };

        BoardMap boardMap = BoardMap.getInstance();
        boardMap.setMap(map);
        String answer = boardMap.flippableCoordinates(new CoordinatesXY(0,0), 'O','X').toString();
        assertEquals("[{x=1, y=0}, {x=2, y=0}, {x=3, y=0}, {x=4, y=0}, {x=5, y=0}, {x=6, y=0}, {x=0, y=1}, {x=0, y=2}, {x=0, y=3}, {x=0, y=4}, {x=0, y=5}, {x=0, y=6}, {x=1, y=1}, {x=2, y=2}, {x=3, y=3}, {x=4, y=4}, {x=5, y=5}, {x=6, y=6}]", answer);
    }
    @Test
    void flippableCoordinatesV3() {
        char map [][] = {
                {'X', 'O', 'O', 'X', 'O', 'O', 'X', 'X'},
                {'O', 'O', ' ', 'O', ' ', 'O', ' ', ' '},
                {'O', ' ', 'O', 'O', 'O', ' ', ' ', ' '},
                {'X', 'O', 'O', ' ', 'O', 'O', 'O', 'X'},
                {'O', ' ', 'O', 'O', 'O', ' ', ' ', ' '},
                {'O', 'O', ' ', 'O', ' ', 'O', ' ', ' '},
                {'X', ' ', ' ', 'O', ' ', ' ', 'O', ' '},
                {'O', ' ', ' ', 'X', ' ', ' ', ' ', 'X'}
        };

        BoardMap boardMap = BoardMap.getInstance();
        boardMap.setMap(map);
        String answer = boardMap.flippableCoordinates(new CoordinatesXY(3,3), 'O','X').toString();
        assertEquals("[{x=2, y=2}, {x=1, y=1}, {x=3, y=2}, {x=3, y=1}, {x=4, y=2}, {x=5, y=1}, {x=2, y=3}, {x=1, y=3}, {x=4, y=3}, {x=5, y=3}, {x=6, y=3}, {x=2, y=4}, {x=1, y=5}, {x=3, y=4}, {x=3, y=5}, {x=3, y=6}, {x=4, y=4}, {x=5, y=5}, {x=6, y=6}]", answer);
    }
    @Test
    void flippableCoordinatesShouldBeEmpty() {
        char map [][] = {
                {' ', 'O', 'O', 'O', 'O', 'O', 'O', ' '},
                {'O', 'O', ' ', ' ', ' ', ' ', ' ', ' '},
                {'O', ' ', 'O', ' ', ' ', ' ', ' ', ' '},
                {'O', ' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {'O', ' ', ' ', ' ', 'O', ' ', ' ', ' '},
                {'O', ' ', ' ', ' ', ' ', 'O', ' ', ' '},
                {'O', ' ', ' ', ' ', ' ', ' ', 'O', ' '},
                {'O', ' ', ' ', ' ', ' ', ' ', ' ', 'O'}
        };

        BoardMap boardMap = BoardMap.getInstance();
        boardMap.setMap(map);
        String answer = boardMap.flippableCoordinates(new CoordinatesXY(0,0), 'O','X').toString();
        assertEquals("[]", answer);
    }
}