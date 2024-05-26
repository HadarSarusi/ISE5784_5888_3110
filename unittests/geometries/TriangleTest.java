package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;


/**
 * Testing Triangle class
 *
 * @author Lea &amp; Hadar
 */

class TriangleTest {

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
    Triangle tri = new Triangle(new Point(1,2,3),new Point(4,5,6),new Point(7,8,10));
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
    @Test
    void findIntsersections() {

    }
}