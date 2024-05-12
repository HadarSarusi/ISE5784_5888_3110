package primitives;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit teats for primitives.Point class
 * @author Lea & Hadar
 */

class PointTest {

    public static final Point ONE = new Point(1,1,1);
    public static final Point ZERO = new Point(1,1,1);
    public static final Vector vector = new Vector(1,1,1);


    /**
     * Test method for {@link primitives.Point#add(Vector)}
     */
    @Test
    void add() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test if Add vector to point correct
        assertEquals(vector, new Point(2,2,2).add(new Vector(-1,-1,-1)));
        // ============ BVA ==============
        //TC11: test if center of coordinates does work correctly
        assertEquals(ZERO, ONE.add(new Vector(-1,-1,-1)));
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void subtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if subtract point to point correct
        assertEquals(ONE, new Point(2,2,2).subtract(ONE));
        // ============ BVA  ==============
        //TC11: test zero vector from subtract
        assertThrows(IllegalArgumentException.class, ()-> ONE.subtract(ONE),"\"ERROR: (point - itself) does not throw an exception\"");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}
     */
    @Test
    void distanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test if point squared distance to itself is zero





    }

    @Test
    void distance() {
    }
}