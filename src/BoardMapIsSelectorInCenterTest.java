import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class BoardMapIsSelectorInCenterTest {

    @Test
    void X4Y4SelectorShouldBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 4; i++){
            boardMap.moveDown();
            boardMap.moveRight();
        }
        assertTrue(boardMap.isSelectorInCentre());
    }
    @Test
    void X3Y3SelectorShouldBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 3; i++){
            boardMap.moveDown();
            boardMap.moveRight();
        }
        assertTrue(boardMap.isSelectorInCentre());
    }
    @Test
    void X4Y3SelectorShouldBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 4; i++){
            boardMap.moveRight();
        }
        for (int i = 0; i < 3; i++){
            boardMap.moveDown();
        }
        assertTrue(boardMap.isSelectorInCentre());
    }
    @Test
    void X3Y4SelectorShouldBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 3; i++){
            boardMap.moveRight();
        }
        for (int i = 0; i < 4; i++){
            boardMap.moveDown();
        }
        assertTrue(boardMap.isSelectorInCentre());
    }

    @Test
    void X1Y1SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }
    @Test
    void X1Y8SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveDown();
            boardMap.moveLeft();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }
    @Test
    void X8Y8SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveDown();
            boardMap.moveRight();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }
    @Test
    void X8Y1SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveRight();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }

    @Test
    void X2Y3SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 2; i++){
            boardMap.moveRight();
        }
        for (int i = 0; i < 3; i++){
            boardMap.moveDown();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }
    @Test
    void X5Y3SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 5; i++){
            boardMap.moveRight();
        }
        for (int i = 0; i < 3; i++){
            boardMap.moveDown();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }
    @Test
    void X3Y5SelectorShouldNotBeInCentre() {
        BoardMap boardMap = BoardMap.getInstance();
        for(int i = 0; i<100; i ++){
            boardMap.moveUp();
            boardMap.moveLeft();
        }
        for (int i = 0; i < 3; i++){
            boardMap.moveRight();
        }
        for (int i = 0; i < 5; i++){
            boardMap.moveDown();
        }
        assertFalse(boardMap.isSelectorInCentre());
    }
}