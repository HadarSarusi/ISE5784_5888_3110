package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;


/**
 * Testing Triangle class
 *
 * @author Lea &amp; Hadar
 */

class TriangleTest {
    /**
     * A small constant value used to determine the acceptable margin of error
     * in floating-point comparisons. The value of DELTA is 0.0001.
     */
    public static final double DELTA = 0.0001;

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                {new Point(1, 2, 3),
                        new Point(4, 5, 6),
                        new Point(7, 8, 9),};
        Triangle tri = new Triangle(new Point(1, 2, 3), new Point(4, 5, 6), new Point(7, 8, 10));
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tri.getNormal(new Point(1, 2, 3)), "");
        // generate the test result
        Vector result = tri.getNormal(new Point(1, 2, 3));
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 2; ++i)
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 2 : i - 1])), DELTA,
                    "Triangle's normal is not orthogonal to one of the edges");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void findIntsersections() {
        Triangle triangle = new Triangle(new Point(0, 1, 0), new Point(0, 5, 0), new Point(0, 3, 5));
        // ============ Equivalence Partitions Tests ==============
        //TC01: the ray intersect inside the triangle
        Point p130 = new Point(1, 3, 0);
        final var result = triangle.findIntersections(new Ray(p130, new Vector(-1, 0, 1)))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p130))).toList();
        assertEquals(1, result.size(),DELTA, "Wrong number of points");
        assertEquals(List.of(new Point(0, 3, 1)),
                triangle.findIntersections(new Ray(new Point(1, 3, 0), new Vector(-1, 0, 1))),
                "The point supposed to be in the triangle");

        // TC02: The intersection point is outside the triangle, against edge
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0, 1))),
                "The point supposed to be outside the triangle, against edge");

        // TC03: The intersection point is outside the triangle, against vertex
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0.1, -0.1))),
                "The point supposed to be outside the triangle, against vertex");

        // =============== Boundary Values Tests ==================
        // TC10: The point is on edge
        assertNull(triangle.findIntersections(new Ray(new Point(1, 3, 0), new Vector(-1, 0, 0))),
                "The point supposed to be on edge");

        // TC11: The point is in vertex
        assertNull(triangle.findIntersections(new Ray(new Point(1, 1, 0), new Vector(-1, 0, 0))),
                "The point supposed to be in vertex");

        // TC12: The point is on edge's continuation
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0.1, 0))),
                "The point supposed to be on edge's continuation");
    }
}
