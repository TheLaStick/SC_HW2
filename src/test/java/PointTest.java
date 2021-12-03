import LaStick.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PointTest {
    @Test
    public void pointConstructorMakesPointOnValues23() {
        Point point = new Point(new Coord2D(2, 3));
        assertTrue(point.getPosition().getX() == 2 && point.getPosition().getY() == 3);
    }

    @Test
    public void pointSetPointOnNewCoord23ShouldReturnPointWithCoord23() {
        Point point = new Point(new Coord2D(0, 0));
        point.setPosition(new Coord2D(2, 3));
        assertTrue(point.getPosition().getX() == 2 && point.getPosition().getY() == 3);
    }

    @Test
    public void pointEqualsOnPointWithSimilarNumbersShouldReturnFalse() {
        Point firstPoint = new Point(new Coord2D(32, 54));
        Point secondPoint = new Point(new Coord2D(32, 54));

        assertTrue(!firstPoint.equals(secondPoint));
    }

    @Test
    public void pointBoundShouldReturnBoundBoxWithOneUniqeCoord2D() {
        Point point = new Point(new Coord2D(5, 4));
        BoundBox box = point.bound();
        assertTrue(box.getLowPoint().getX() == 5 &&
                box.getLowPoint().getY() == 4 &&
                box.getHighPoint().getX() == 5 &&
                box.getHighPoint().getY() == 4);
    }
}
