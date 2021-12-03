import LaStick.*;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class OriginTest {
    @Test
    public void originConstructorMakesOriginOnValues23() {
        Origin origin = new Origin(new Coord2D(2, 3));
        assertTrue(origin.getPosition().getX() == 2 && origin.getPosition().getY() == 3);
    }

    @Test
    public void originEqualsOnOriginWithSimilarNumbersShouldReturnFalse() {
        Origin firstOrigin = new Origin(new Coord2D(32, 54));
        Origin secondOrigin = new Origin(new Coord2D(32, 54));

        assertNotEquals(firstOrigin, secondOrigin);
    }

    @Test
    public void originSetChildrenOnTwoCycledChildrenShouldThrowDAGConstraintException() {
        Throwable exception = assertThrows(DAGConstraintException.class,
                () -> {
                    Origin origin = new Origin(new Coord2D(0, 0));

                    Origin childOrigin1 = new Origin(new Coord2D(1, 0));
                    Origin childOrigin2 = new Origin(new Coord2D(0, 1));
                    Set<Point> descendants1 = new HashSet<>();
                    descendants1.add(childOrigin2);
                    childOrigin1.setChildren(descendants1);
                    Set<Point> descendants2 = new HashSet<>();
                    descendants2.add(origin);
                    childOrigin2.setChildren(descendants2);

                    Set<Point> children = new HashSet<>();
                    children.add(childOrigin1);
                    origin.setChildren(children);
                }
        );

        assertEquals(exception.getMessage(), "Found ring");
    }

    @Test
    public void originSetChildrenOnCycledChildrenShouldThrowDAGConstraintException() {
        Throwable exception = assertThrows(DAGConstraintException.class,
                () -> {
                    Origin origin = new Origin(new Coord2D(0, 0));

                    Origin childOrigin1 = new Origin(new Coord2D(1, 0));
                    Origin childOrigin2 = new Origin(new Coord2D(2, 0));
                    Origin childOrigin3 = new Origin(new Coord2D(3, 0));
                    Origin childOrigin4 = new Origin(new Coord2D(4, 0));

                    Set<Point> descendants1 = new HashSet<>();
                    descendants1.add(childOrigin2);
                    childOrigin1.setChildren(descendants1);

                    Set<Point> descendants2 = new HashSet<>();
                    descendants2.add(childOrigin3);
                    childOrigin2.setChildren(descendants2);

                    Set<Point> descendants3 = new HashSet<>();
                    descendants3.add(childOrigin4);
                    childOrigin3.setChildren(descendants3);

                    Set<Point> descendants4 = new HashSet<>();
                    descendants4.add(childOrigin1);
                    childOrigin4.setChildren(descendants4);

                    Set<Point> children = new HashSet<>();
                    children.add(childOrigin1);
                    origin.setChildren(children);
                }
        );

        assertEquals(exception.getMessage(), "Found ring");
    }

    @Test
    public void originGetChildrenShouldReturnCopyOfSetOfPoints() {
        Origin origin = new Origin(new Coord2D(0, 0));

        Set<Point> points = origin.getChildren();
        points.add(origin);

        assertNotSame(points, origin.getChildren());
    }

    @Test
    public void originBoundOnTwoPointsWithCoord2D43And53ShouldReturnBoundBoxWithCoords42And53() throws DAGConstraintException {
        Origin origin = new Origin(new Coord2D(0, 0));
        Set<Point> points = new HashSet<>();
        points.add(new Point(new Coord2D(4, 3)));
        points.add(new Point(new Coord2D(5, 2)));

        origin.setChildren(points);
        BoundBox box = origin.bound();
        BoundBox result = new BoundBox(new Coord2D(4, 2), new Coord2D(5, 3));

        assertEquals(box, result);
    }

    @Test
    public void originBoundOnOriginWithCoord2D21AndTwoPointsWithCoord2D22And31ShouldReturnBoundBoxWithCoords42And53() throws DAGConstraintException {
        Origin origin = new Origin(new Coord2D(2, 1));
        Set<Point> points = new HashSet<>();
        points.add(new Point(new Coord2D(2, 2)));
        points.add(new Point(new Coord2D(3, 1)));
        origin.setChildren(points);

        BoundBox box = origin.bound();
        BoundBox result = new BoundBox(new Coord2D(4, 2), new Coord2D(5, 3));

        assertEquals(box, result);
    }

    @Test
    public void originBoundOnOriginWith21AndPoints22And31AndSetPositionWith00ShouldReturnBoundBoxWith21And32() throws DAGConstraintException {
        Space space = new Space(new Coord2D(0, 0));

        Set<Point> spaceChildren = new HashSet<>();
        Origin origin = new Origin(new Coord2D(2, 1));
        spaceChildren.add(origin);
        space.setChildren(spaceChildren);

        Point p1 = new Point(new Coord2D(2, 2));
        Point p2 = new Point(new Coord2D(3, 1));

        Set<Point> children = new HashSet<>();
        children.add(p1);
        children.add(p2);

        origin.setChildren(children);

        origin.setPosition(new Coord2D(0, 0));
        BoundBox bound = origin.bound();
        BoundBox box = new BoundBox(new Coord2D(2, 1), new Coord2D(3, 2));

        assertEquals(box, bound);
    }

    @Test
    public void originBoundOnOriginWith21AndPoints22And31AndSetPositionWith33ShouldReturnBoundBoxWith54And65() throws DAGConstraintException {
        Space space = new Space(new Coord2D(0, 0));

        Set<Point> spaceChildren = new HashSet<>();
        Origin origin = new Origin(new Coord2D(2, 1));
        spaceChildren.add(origin);
        space.setChildren(spaceChildren);

        Point p1 = new Point(new Coord2D(2, 2));
        Point p2 = new Point(new Coord2D(3, 1));

        Set<Point> children = new HashSet<>();
        children.add(p1);
        children.add(p2);

        origin.setChildren(children);

        origin.setPosition(new Coord2D(3, 3));
        BoundBox bound = origin.bound();
        BoundBox box = new BoundBox(new Coord2D(5, 4), new Coord2D(6, 5));

        assertEquals(box, bound);
    }

    @Test
    public void spaceOnOriginWith21AndPoints22And31AndOriginminus2minus2AndPoints11AndPoint13ShouldReturnBoundBoxWithCoord2Dminus1minus1And53() throws DAGConstraintException {
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
    public void originBoundBoxOnEmptyChildrenShouldReturnEmptyBoundBox() {
        Origin origin = new Origin(new Coord2D(0, 0));

        BoundBox boundBox = origin.bound();

        BoundBox emptyBox = new BoundBox();

        assertEquals(emptyBox, boundBox);
    }
}
