import LaStick.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Coord2DTest {
    @Test
    public void coord2DEqualsOnCoord2DWithSimilarNumbersShouldReturnTrue() {
        Coord2D firstCoord = new Coord2D(32, 54);
        Coord2D secondCoord = new Coord2D(32, 54);

        assertTrue(firstCoord.equals(secondCoord));
    }
}
