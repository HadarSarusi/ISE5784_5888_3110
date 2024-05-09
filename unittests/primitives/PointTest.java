package primitives;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Point
 * @author Lea & Hadar
 */

class PointTest {

    public static final Point point = new Point(1,1,1);



    @Test
    void add() {
    }

    @Test
    void subtract() {
        Point point1 = new Point(2,2,2);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(point, point1.subtract(point));
    }

    private void assertEquals(Point point, Object o) {

    }

    @Test
    void product() {
    }

    @Test
    void distanceSquared() {
    }

    @Test
    void distance() {
    }
}