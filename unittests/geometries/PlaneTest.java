package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;

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
        Plane plane = new Plane(new Point(0,0,1), new Point(0,2,0), new Point(1,0,0));
        final Point p0_20 = new Point(0, -2, 0);

        // ============ Equivalence Partitions Tests ==============
        // TC01: The ray is neither perpendicular nor parallel to the plane and trace the plane
        final var result = plane.findIntersections(new Ray(p0_20, new Vector(1,4,-1)))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p0_20))).toList();
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1,2,-1)), result, "Ray crosses plane");

        // TC02: The ray is neither perpendicular nor parallel to the plane and not trace the plane
        assertNull(plane.findIntersections(new Ray(new Point(0,5,0),new Vector(6,-5,0))));

        // =============== Boundary Values Tests ==================

        //The ray is parallel to the plane
        //TC11: ray start inside, 0 hits
        assertNull(plane.findIntersections(new Ray(new Point(0.67,-2.16,1.41), new Vector(-1.8,-0.65,2.08))));
        //TC12: ray start outside, 0 hits
        assertNull(plane.findIntersections(new Ray(new Point(5,0,0), new Vector(-0.64,-0.2,0.74))));

        //The ray is perpendicular to the plane
        Point p_200 = new Point(-2,0,0);
        // TC13: Ray start outside  the plane and goes inside the plane (1 point)
        final var result1 = plane.findIntersections(new Ray(p_200, new Vector(2,1,2)))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p_200))).toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(-0.6666666666666667, 0.6666666666666666, 1.3333333333333333)), result1, "Ray crosses plane");

        // TC14: Ray start outside  the plane (0 point)
        assertNull(plane.findIntersections(new Ray(p_200, new Vector(-1.53,-0.77,-1.53))));

        // TC15: Ray start inside the plane (0 point)
        assertNull(plane.findIntersections(new Ray(new Point(-10,-10,16), new Vector(1,0.5,2))));

        // **** Group: Ray is neither orthogonal nor parallel to
        //TC15: Ray begins at the plane
        assertNull(plane.findIntersections(new Ray(new Point(2,4,1), new Vector(2,3,5))),
                "Ray is neither orthogonal nor parallel to ray and begin at the plane");

        //TC16: Ray begins in the same point which appears as reference point in the plane
        assertNull(plane.findIntersections(new Ray(new Point(1,0,1), new Vector(2,3,5))),
                "Ray is neither orthogonal nor parallel to ray and begins in the same point " +
                        "which appears as reference point in the plane");

    }
}
