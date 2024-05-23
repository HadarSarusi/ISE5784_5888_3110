package geometries;

import org.junit.jupiter.api.Test;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;
import java.util.List;

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

    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Point gp3 = new Point(2, 0.5, 0);
        final var exp2 = List.of(gp3);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v310op = new Vector(-3, -1, 0);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Point p01 = new Point(-1, 0, 0);
        final Point p02 = new Point(1.5, 0.5, 0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        final var result2 = sphere.findIntersections(new Ray(p02, v100))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p02))).toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp2, result1, "Ray crosses sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v310op)), "Ray's line out of sphere");
    }
// =============== Boundary Values Tests ==================


}

