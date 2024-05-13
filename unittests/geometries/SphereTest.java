package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Vector;

/**
 * Testing Sphere
 *
 * @author Lea &amp; Hadar
 */
class SphereTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Proper normal examination
        Sphere sphere = new Sphere(new Point(0,0,0),1d);
        assertEquals(new Vector(1,0,0),sphere.getNormal(new Point(2,0,0)),"the normal is not good");
    }
}