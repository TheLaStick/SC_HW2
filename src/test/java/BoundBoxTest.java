import LaStick.*;
import org.junit.jupiter.api.Test;


import java.security.PublicKey;

import static org.junit.jupiter.api.Assertions.*;


public class BoundBoxTest {
    /**
     *
     */
    @Test
    public void boundBoxMergeOnEmptyBoundBoxAndBoundBoxWith23And54ShouldReturnBoundBoxWith23And54() {
        BoundBox box = new BoundBox(new Coord2D(2, 3), new Coord2D(5, 4));
        BoundBox emptyBox = new BoundBox();

        BoundBox newBox = BoundBox.merge(box, emptyBox);

        assertEquals(newBox, box);
    }

    @Test
    public void boundBoxMergeOnBoundBoxWith23And54AndBoundBoxWith00And45ShouldReturnBoundBoxWith00And55() {
        BoundBox box1 = new BoundBox(new Coord2D(2, 3), new Coord2D(5, 4));
        BoundBox box2 = new BoundBox(new Coord2D(0, 0), new Coord2D(4, 5));

        BoundBox newBox = BoundBox.merge(box1, box2);

        assertEquals(newBox, new BoundBox(new Coord2D(0, 0), new Coord2D(5, 5)));
    }

    @Test
    public void boundBoxGetLowPointOnCoord24AndCoord54ShouldReturnCoord24() {
        BoundBox box = new BoundBox(new Coord2D(2, 4), new Coord2D(5, 4));

        assertEquals(box.getLowPoint(), new Coord2D(2, 4));
    }

    @Test
    public void boundBoxGetHighPointOnCoord24AndCoord54ShouldReturnCoord54() {
        BoundBox box = new BoundBox(new Coord2D(2, 4), new Coord2D(5, 4));

        assertEquals(box.getHighPoint(), new Coord2D(5, 4));
    }

    @Test
    public void boundBoxMoveBoxOnBoxWithCoords23And54AndCoords55ShouldReturnBoundBox78And109(){
        BoundBox box = new BoundBox(new Coord2D(2, 3), new Coord2D(5, 4));

        assertEquals(BoundBox.moveBox(box, new Coord2D(5,5)),
                new BoundBox(new Coord2D(7, 8), new Coord2D(10,9)));
    }
}
