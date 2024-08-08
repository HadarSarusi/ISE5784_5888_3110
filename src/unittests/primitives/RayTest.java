package primitives;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the geometries.Ray class.
 * This class contains test methods to verify the functionality of the ray class.
 * <p>
 * Author: Lea &amp; Hadar
 */
class RayTest {

    /**
     * Tests the {@link Ray#getPoint(double)} method.
     * This method checks if the point returned by the {@code getPoint} method is correct based on the given parameter {@code t}.
     */
    @Test
    void getPoint() {
        Ray ray = new Ray(new Point(1, 1, 0), new Vector(0, 1, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: t>0
        assertEquals(new Point(1, 3, 0), ray.getPoint(2));
        //TC02: t<0
        assertEquals(new Point(1, -1, 0), ray.getPoint(-2));
        // =============== Boundary Values Tests ==================
        //TC10: t=0
        assertEquals(new Point(1, 1, 0), ray.getPoint(0));
    }

    /**
     * Tests the {@code findClosestPoint} method of the {@code Ray} class.
     * It verifies the correctness of finding the closest point from a list of points
     * based on different test cases including equivalence partitions and boundary values.
     */
    @Test
    void findClosestPoint() {

        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 2, 2);
        Point p3 = new Point(3, 3, 3);
        List<Point> points = new LinkedList<>(Arrays.asList(p1, p2, p3));

        Vector dirVector = new Vector(0, -0.5, 0);

        // ============ Equivalence Partitions Tests ==============
        //TC01: The closest point is in the middle of the list
        Ray ray1 = new Ray(new Point(2, 2.5, 2), dirVector);
        assertEquals(p2, ray1.findClosestPoint(points), "The point in the middle!!");

        // =============== Boundary Values Tests ==================
        //TC10: The closest point is the first point in the list
        Ray ray2 = new Ray(new Point(1, 1.25, 1), dirVector);
        assertEquals(p1, ray2.findClosestPoint(points), "The point is the first one!!");

        //TC11: The closest point is the last point in the list
        Ray ray3 = new Ray(new Point(3, 3.5, 3), dirVector);
        assertEquals(p3, ray3.findClosestPoint(points), "The point is the last one!!");

        //TC12: The list is null
        points.clear();
        assertNull(ray3.findClosestPoint(points), "The list is empty!!");
    }
}
