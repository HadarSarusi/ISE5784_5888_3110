package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Unit tests for the geometries.Tube class.
 * This class contains test methods to verify the functionality of the Tube class.
 * <p>
 * Author: Lea &amp; Hadar
 */
class TubeTest {

    /**
     * Test method for {@link geometries.Tube#getNormal(Point)}.
     * This test method verifies the behavior of the getNormal method in the Tube class.
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Verifying normal calculation for a specific point on the tube
        Ray ray = new Ray(new Point(0, 1, 0), new Vector(0, 1, 0));
        Tube tb = new Tube(ray, 2);
        assertEquals(new Vector(1, 0, 0), tb.getNormal(new Point(2, 0, 0)),
                "Normal abnormality");

        // =============== Boundary Values Tests ==================

        // TC11: Verifying that an exception is thrown when connecting a point to the tube's axis head
        //      which produces a right angle with the axis
        Tube tube = new Tube(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)), 1);
        assertThrows(IllegalArgumentException.class, () -> tube.getNormal(new Point(0, 1, 0)),
                "The point is in front of the head of the tube axis");
    }
}
