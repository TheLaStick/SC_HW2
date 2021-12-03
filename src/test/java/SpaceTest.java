import LaStick.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class SpaceTest {
    @Test
    public void spaceOnOriginWith21AndPoints22And31AndOriginWithMinus2Minus2AndPoints11AndPoint13ShouldReturnBoundBoxWithCoord2Dminus1minus1And53() throws DAGConstraintException {
        Space space = new Space(new Coord2D(0, 0));
        Origin origin1 = new Origin(new Coord2D(2, 1));
        Origin origin2 = new Origin(new Coord2D(-2, -2));

        Set<Point> points1 = new HashSet<>();
        points1.add(new Point(new Coord2D(2, 2)));
        points1.add(new Point(new Coord2D(3, 1)));

        Set<Point> points2 = new HashSet<>();
        points2.add(new Point(new Coord2D(1, 1)));

        Point point = new Point(new Coord2D(1, 3));

        Set<Point> origins = new HashSet<>();
        origins.add(origin1);
        origins.add(origin2);
        origins.add(point);

        origin1.setChildren(points1);
        origin2.setChildren(points2);
        space.setChildren(origins);


        BoundBox box = space.bound();
        BoundBox result = new BoundBox(new Coord2D(-1, -1), new Coord2D(5, 3));

        assertEquals(box, result);
    }


    @Test
    public void spaceEqualsOnSpaceWithSimilarNumbersShouldReturnFalse() {
        Space firstSpace = new Space(new Coord2D(32, 54));
        Space secondSpace = new Space(new Coord2D(32, 54));

        assertNotEquals(firstSpace, secondSpace);
    }

}
