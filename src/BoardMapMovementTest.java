import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardMapMovementTest {

    @Test
    void Yposition6moveUpShouldMakeY5() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setY(6);
        boardMap.moveUp();
        assertEquals(5, boardMap.getSelector().getY());
    }
    @Test
    void Yposition0moveUpShouldMakeY0() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setY(0);
        boardMap.moveUp();
        assertEquals(0, boardMap.getSelector().getY());
    }

    @Test
    void positionY7movedDownShouldBeY7() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setY(7);
        boardMap.moveDown();
        assertEquals(7, boardMap.getSelector().getY());
    }
    @Test
    void positionY5movedDownShouldBeY6() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setY(5);
        boardMap.moveDown();
        assertEquals(6, boardMap.getSelector().getY());
    }

    @Test
    void moveX4RightShouldBeX6() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setX(4);
        boardMap.moveRight();
        assertEquals(5, boardMap.getSelector().getX());
    }
    @Test
    void moveX7RightShouldBeX7() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setX(7);
        boardMap.moveRight();
        assertEquals(7, boardMap.getSelector().getX());
    }

    @Test
    void moveX7LeftShouldBeX6() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setX(7);
        boardMap.moveLeft();
        assertEquals(6, boardMap.getSelector().getX());
    }
    @Test
    void moveX0LeftShouldBeX0() {
        BoardMap boardMap = BoardMap.getInstance();
        boardMap.getSelector().setX(0);
        boardMap.moveLeft();
        assertEquals(0, boardMap.getSelector().getX());
    }
}