package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Tube
 *
 * @author Lea &amp; Hadar
 */
class TubeTest {

    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: There is a simple single test here
        Ray ray = new Ray(new Point(0,1,0), new Vector(0,1,0));
        Tube tb = new Tube(ray, 2);
        assertEquals(tb.getNormal(new Point(2,0,0)) ,new Vector(1,0,0),
                "Normal abnormality");

        // =============== Boundary Values Tests ==================

        // TC11: test When connecting the point to the horn head of the cylinder axis produces a right angle with the axis
        Tube tube = new Tube(new Ray(new Point(0,0,0),new Vector(1,0,0)),1);
        assertThrows(IllegalArgumentException.class,()-> tube.getNormal(new Point(0,1,0)),
                "The point in front of the head of the foundation");
    }
    }
