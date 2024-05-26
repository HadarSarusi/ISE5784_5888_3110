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
    public static final double DELTA = 0.0001;
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
    private final Point p220 = new Point(2, 2, 0);
    private final Point p210 = new Point(2, 1, 0);

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(p100, 1d);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final Point gp4 = new Point(1,1,0);
        final var exp = List.of(gp1, gp2);
        final var exp7 = List.of(gp4);
        final Point gp3 = new Point(2, 0.5, 0);
        final var exp2 = List.of(gp3);
        final Point p150 = new Point(-1,5,0);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v310op = new Vector(-3, -1, 0);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Vector v_1_10 = new Vector(-1, -1, 0);
        final Vector v010 = new Vector(0, 1, 0);
        final Vector v_100 = new Vector(-1, 0, 0);
        final Vector v0_10 = new Vector(0, -1, 0);
        final Point p01 = new Point(-1, 0, 0);
        final Point p02 = new Point(1.5, 0.5, 0);
        final Point p11_50 = new Point(1,0.5,0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
        assertEquals(2, result1.size(), "Wrong number of points");
        assertEquals(exp, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        final var result2 = sphere.findIntersections(new Ray(p11_50, v010))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p11_50))).toList();
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(exp7, result2, "Ray crosses sphere");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p150, v310op)), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================
        //TC11: The ray starts before the sphere and if we reverse the direction it will pass through the center
        final Point p020 = new Point(0, 2, 0);
        final Point p1_52_866 = new Point(1.5, 2.8660254037844384, 0);
        final Point p120 = new Point(1, 2, 0);
        final Point p1_520 = new Point(1.5, 2, 0);
        final Point p320 = new Point(3, 2, 0);
        final Point p110 = new Point(1, 1, 0);
        final Point p2_51_134 = new Point(2.5, 1.134, 0);
        final Point p1_51_134 = new Point(1.5, 1.134, 0);
        Sphere sphere1 = new Sphere(p220, 1d);
        assertNull(sphere1.findIntersections(new Ray(p020, v110)), "Ray's line out of sphere");
        //TC12:The ray starts at the edge, if we reverse the direction the ray will pass through the center
        assertNull(sphere1.findIntersections(new Ray(p120, v_100)), "Ray's start on the sphere");
        //TC:13:The ray starts inside the sphere and if we were to reverse the direction it would pass through the center
        final var exp3 = List.of(p120);
        final var result3 = sphere1.findIntersections(new Ray(p1_520, v_100))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p1_520))).toList();
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(exp3, result3, "Ray crosses sphere");
        //TC14:The ray starts in the middle of the sphere and there is one hit
        final var exp4 = List.of(p120);
        final var result4 = sphere1.findIntersections(new Ray(p220, v_100))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p220))).toList();
        assertEquals(1, result4.size(), "Wrong number of points");
        assertEquals(exp4, result4, "Ray crosses sphere");
        //TC15:The ray starts on the sphere and goes through the center, there is one hit
        final var exp5= List.of(p320);
        final var result5 = sphere1.findIntersections(new Ray(p120, v100))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p120))).toList();
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(exp5, result5, "Ray crosses sphere");
        //TC16:The ray starts before the sphere and goes through the center, there are 2 points of hit
        final var exp6= List.of(p120,p320);
        final var result6 = sphere1.findIntersections(new Ray(p020, v100))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p020))).toList();
        assertEquals(2, result6.size(), "Wrong number of points");
        assertEquals(exp6, result6, "Ray crosses sphere");
        //TC17:The ray starts on the edge and goes out, 0 hits
        assertNull(sphere1.findIntersections(new Ray(p320, v100)), "Ray's line out of sphere");
        //TC18:The ray starts on the edge and goes inside, there is one hit
        final var exp8= List.of(p210);
        final var result8 = sphere1.findIntersections(new Ray(p320, v_1_10))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p320))).toList();
        assertEquals(1, result8.size(),"Wrong number of points");
        assertEquals(exp8, result8, "Ray crosses sphere");

       //TC19:The ray launches to the sphere
        assertNull(sphere1.findIntersections(new Ray(p110, v010)), "Ray's line launches of sphere");
        //TC20:The ray starts on the count, 0 hits
        assertNull(sphere1.findIntersections(new Ray(p120, v010)), "Ray's start on the sphere");
        //TC21:The ray after the count, if we had reversed the direction the beam would have launched, zero hits
        assertNull(sphere1.findIntersections(new Ray(p110, v0_10)), "Ray's start on the sphere");
        //TC22:If a line is drawn from the center point to the top of the ray which is inside the sphere, then the ray is perpendicular to the line, 1 hit
        final var exp9= List.of(p1_52_866);
        final var result9 = sphere1.findIntersections(new Ray(p1_520, v010))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p1_520))).toList();
        assertEquals(1, result9.size(), "Wrong number of points");
        assertEquals(exp9, result9, "Ray crosses sphere");
        //TC23:If a line is drawn from the center point to the top of the ray which is outside the sphere, then the ray is perpendicular to the line, 0 hit
        assertNull(sphere1.findIntersections(new Ray(p020, v0_10)), "Ray's start on the sphere");


    }



}

