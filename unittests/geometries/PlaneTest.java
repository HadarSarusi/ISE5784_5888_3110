package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import primitives.Point;
import primitives.Vector;

/**
 * Testing Plane
 *
 * @author Lea &amp; Hadar
 */
class PlaneTest {

    @Test
    void getNormal() {

        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point p1= new Point(1,0,0);
        Point p2= new Point(0,1,0);
        Point p3= new Point(0,0,1);
        Plane plane = new Plane( p1, p2,p3);
        // ensure  there are no exceptions
        assertDoesNotThrow(() ->plane.getNormal(new Point(1,1,0)), "");
        // generate the test result
        Vector result = plane.getNormal(new Point(1, 1, 0));
        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");
        //ensure the result is orthogonal to all the edges
        assertTrue(isZero(result.dotProduct(p1.subtract(p2))),"Plane's normal is not orthogonal to one of the poin");
        assertTrue(isZero(result.dotProduct(p2.subtract(p3))),"Plane's normal is not orthogonal to one of the poin");
        assertTrue(isZero(result.dotProduct(p3.subtract(p1))),"Plane's normal is not orthogonal to one of the poin");

    }

}