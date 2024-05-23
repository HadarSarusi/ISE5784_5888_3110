package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Vector;

/**
 * Unit tests for the geometries.Sphere class.
 * This class contains test methods to verify the functionality of the Sphere class.
 * <p>
 * Author: Lea &amp; Hadar
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}.
     * This test method verifies the behavior of the getNormal method in the Sphere class.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Verifying normal calculation for a specific point on the sphere
        Sphere sphere = new Sphere(new Point(0, 0, 0), 1d);
        assertEquals(new Vector(1, 0, 0), sphere.getNormal(new Point(2, 0, 0)), "the normal is not correct");
    }
}
