package geometries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import primitives.*;


/**
 * Testing Cylinder class
 *
 * @author Lea &amp; Hadar
 */
class CylinderTest {

    @Test
    void getNormal() {
        Cylinder cyl = new Cylinder(1.0, new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)), 1d);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Point at a side of the cylinder
        assertEquals(new Vector(0, 0, 1), cyl.getNormal(new Point(0, 0.5, 2)), "Bad normal to cylinder");
        // TC02: Point at a 1st base of the cylinder
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 1.5)), "Bad normal to lower base of cylinder");
        // TC03: Point at a 2nd base of the cylinder
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 0.5)), "Bad normal to upper base of cylinder");

        // =============== Boundary Values Tests ==================
        // TC11: Point at the center of 1st base
        //  TC11: Point at the center of 1st base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 1)), "Bad normal to center of lower base");

        // TC12: Point at the center of 2nd base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 1)), "Bad normal to center of upper base");
    }

    @Test
    void findIntsersections() {

    }
}