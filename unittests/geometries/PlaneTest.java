package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for the geometries.Plane class.
 * This class contains test methods to verify the functionality of the Plane class.
 * <p>
 * Author: Lea &amp; Hadar
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#Plane(Point, Point, Point)}.
     */
    @Test
    public void testConstructor() {
        // ============ Boundary Value Analysis (BVA) ==============
        //TC11: if the points on the same line expect to exception
        assertThrows(IllegalArgumentException.class, () ->
                new Plane(new Point(1, 2, 3), new Point(2, 4, 6), new Point(4, 8, 12)), "the points on the same straight line");

        //TC12: the first and the second points converge
        assertThrows(IllegalArgumentException.class, () ->
                new Plane(new Point(1, 2, 3), new Point(1, 2, 3), new Point(4, 5, 6)), "2 points converge");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}.
     * This test method verifies the behavior of the getNormal method in the Plane class.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Verifying normal calculation for a specific point on the plane
        // Defining three points to form a plane
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(0, 0, 1);
        Plane plane = new Plane(p1, p2, p3);

        // Ensure that there are no exceptions during normal calculation
        assertDoesNotThrow(() -> plane.getNormal(new Point(1, 1, 0)), "Unexpected exception occurred during normal calculation");

        // Generate the test result
        Vector result = plane.getNormal(new Point(1, 1, 0));

        // Ensure that the length of the normal vector is 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // Ensure that the normal vector is orthogonal to all the edges of the plane
        assertEquals(0,result.dotProduct(p1.subtract(p2)), "Plane's normal is not orthogonal to one of the edges");
        assertEquals(0,result.dotProduct(p2.subtract(p3)), "Plane's normal is not orthogonal to one of the edges");
        assertEquals(result.dotProduct(p3.subtract(p1)), "Plane's normal is not orthogonal to one of the edges");
    }
    @Test
    void findIntsersections() {

    }
}
