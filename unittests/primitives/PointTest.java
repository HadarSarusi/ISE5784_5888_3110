package primitives;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit teats for primitives.Point class
 *
 * @author Lea &amp; Hadar
 */

class PointTest {

    public static final Point ONE = new Point(1, 1, 1);
    public static final Point ZERO = new Point(0, 0, 0);
    public static final Point point = new Point(1, 2, 3);

    /**
     * Test method for {@link primitives.Point#add(Vector)}
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test if Add vector to point correct
        assertEquals(ONE, new Point(2, 2, 2).add(new Vector(-1, -1, -1)));
        // ============ BVA ==============
        //TC11: test if center of coordinates does work correctly
        assertEquals(ZERO, ONE.add(new Vector(-1, -1, -1)));
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if subtract point to point correct
        assertEquals(ONE, new Point(2, 2, 2).subtract(ONE));
        // ============ BVA  ==============
        //TC11: test zero vector from subtract
        assertThrows(IllegalArgumentException.class, () -> ONE.subtract(ONE), "\"ERROR: (point - itself) does not throw an exception\"");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test: distance square between 2 point work corrently
        assertEquals(5, ONE.distanceSquared(point));
        // ============ BVA  ==============
        //TC11: test if point squared distance to itself is zero
        assertEquals(0, point.distanceSquared(point));
    }
    /**
     * Test method for {@link primitives.Point#distance(Point)}
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test: distance square between 2 point work corrently
        assertEquals(Math.sqrt(5), ONE.distance(point));
        // ============ BVA  ==============
        //TC11: test if point squared distance to itself is zero
        assertEquals(0, point.distance(point));
    }
}