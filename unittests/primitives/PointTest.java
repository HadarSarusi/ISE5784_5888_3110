package primitives;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Point
 * @author Lea & Hadar
 */

class PointTest {

    public static final Point ONE = new Point(1,1,1);
    public static final Point ZERO = new Point(1,1,1);
    public static final Vector vector = new Vector(1,1,1);



    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(vector, new Point(2,2,2).add(new Vector(-1,-1,-1)));
        // ============ BVA ==============
        assertEquals(ZERO, ONE.add(new Vector(-1,-1,-1)));
    }

    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: simple test
        assertEquals(ONE, new Point(2,2,2).subtract(ONE));
        // ============ BVA  ==============
        assertNotEquals(ONE, ONE.subtract(ONE),"\"ERROR: (point - itself) does not throw an exception\"");

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