package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the primitives.Point class.
 * This class contains test methods to verify the functionality of the Point class.
 *
 * Author: Lea &amp; Hadar
 */
class PointTest {

    /**
     * Constant representing the point (1, 1, 1).
     */
    public static final Point ONE = new Point(1, 1, 1);

    /**
     * Constant representing the origin point (0, 0, 0).
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * A sample point.
     */
    public static final Point point = new Point(1, 2, 3);

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     * This test method verifies the behavior of the add method in the Point class.
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if adding a vector to a point produces the correct result
        assertEquals(ONE, new Point(2, 2, 2).add(new Vector(-1, -1, -1)));

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: test if adding a vector to the origin point produces the correct result
        assertEquals(ZERO, ONE.add(new Vector(-1, -1, -1)));
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}.
     * This test method verifies the behavior of the subtract method in the Point class.
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if subtracting a point from another point produces the correct result
        assertEquals(ONE, new Point(2, 2, 2).subtract(ONE));

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: test if subtracting a point from itself throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> ONE.subtract(ONE), "\"ERROR: (point - itself) does not throw an exception\"");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}.
     * This test method verifies the behavior of the distanceSquared method in the Point class.
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if calculating the squared distance between two points produces the correct result
        assertEquals(5, ONE.distanceSquared(point));

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: test if calculating the squared distance between a point and itself produces zero
        assertEquals(0, point.distanceSquared(point));
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}.
     * This test method verifies the behavior of the distance method in the Point class.
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if calculating the distance between two points produces the correct result
        assertEquals(Math.sqrt(5), ONE.distance(point));

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: test if calculating the distance between a point and itself produces zero
        assertEquals(0, point.distance(point));
    }
}
