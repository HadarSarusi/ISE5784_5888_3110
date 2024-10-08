package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Unit tests for the primitives.Vector class.
 * This class contains test methods to verify the functionality of the Vector class.
 * <p>
 * Author: Lea &amp; Hadar
 */
class VectorTest {
    /**
     * Accuracy of test results for numbers
     */
    public static final double DELTA = 0.0001;

    /**
     * Sample vectors for testing.
     */
    private static final Vector v1 = new Vector(1, 2, 3);
    /**
     * Sample vectors for testing.
     */
    private static final Vector v2 = new Vector(-1, -2, -3);
    /**
     * Sample vectors for testing.
     */
    private static final Vector v3 = new Vector(2, 4, 6);
    /**
     * Sample vectors for testing.
     */
    private static final Vector v4 = new Vector(0, 0, 1);
    /**
     * Sample vectors for testing.
     */
    private static final Vector v5 = new Vector(1, 0, 0);
    /**
     * Sample vectors for testing.
     */
    private static final Vector v7 = new Vector(0, 3, -2);
    /**
     * Sample vectors for testing.
     */
    private static final Vector v8 = new Vector(3, -1, 1);


    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     * This test method verifies the behavior of the Vector constructor.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Constructing a non-zero vector should succeed
        assertDoesNotThrow(() -> new Vector(1, 2, 3), "Failed constructing a correct vector");

        // =============== Boundary Values Tests ==================
        // TC11: Constructing a vector equal to zero should throw an exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "vector can't be zero");
        // TC12: Constructing a vector equal to zero should throw an exception
        assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), "vector can't be zero");
    }

    /**
     * Test method for {@link primitives.Vector#add(Vector)}.
     * This test method verifies the behavior of the add method in the Vector class.
     */
    @Test
    void add() {
        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: Adding a vector to its negative should throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.add(v2));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Adding vectors should produce the correct result
        assertEquals(v1, v2.add(v3), "add vector failed");
    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     * This test method verifies the behavior of the scale method in the Vector class.
     */
    @Test
    void scale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Multiplying a vector by a scalar should produce the correct result
        assertEquals(v3, v1.scale(2), "scale vector failed");

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: Multiplying a vector by zero scalar should throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "vector can't be zero");
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}.
     * This test method verifies the behavior of the dotProduct method in the Vector class.
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Calculating the dot product of two vectors should produce the correct result
        assertEquals(28, v1.dotProduct(v3), DELTA, "dot product failed");

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: Dot product of orthogonal vectors should be zero
        assertEquals(0, v4.dotProduct(v5), DELTA, "dot product for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}.
     * This test method verifies the behavior of the crossProduct method in the Vector class.
     */
    @Test
    void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Calculating the cross product of two vectors should produce the correct result
        assertEquals(v1.length() * v7.length(), v1.crossProduct(v7).length(), DELTA, //
                "CrossProduct returned wrong result length");

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: Cross product result should be orthogonal to its operands
        assertEquals(0, v1.crossProduct(v8).dotProduct(v1), DELTA, "CrossProduct result is not orthogonal to its operands");
        assertEquals(0, v1.crossProduct(v8).dotProduct(v8), DELTA, "CrossProduct result is not orthogonal to its operands");
        // TC12: Cross product for parallel vectors should throw an exception
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "CrossProduct for parallel vectors should throw Exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     * This test method verifies the behavior of the lengthSquared method in the Vector class.
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Calculating the squared length of a vector should produce the correct result
        assertEquals(14, v1.lengthSquared(), DELTA, "vector length squared failed");
    }

    /**
     * Test method for {@link Vector#length()}.
     * This test method verifies the behavior of the length method in the Vector class.
     */
    @Test
    void length() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Calculating the length of a vector should produce the correct result
        assertEquals(Math.sqrt(14), v1.length(), DELTA, "vector length failed");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     * This test method verifies the behavior of the normalize method in the Vector class.
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Normalizing a vector should produce a vector with length 1
        assertEquals(1, v1.normalize().length(), DELTA, "normalize vector failed");

        // ============ Boundary Value Analysis (BVA) ==============
        // TC11: The normalized vector should be parallel to the original one
        assertThrows(Exception.class, () -> v1.crossProduct(v1.normalize()), "The normalized vector is not parallel to the original one");

        // TC12: The dot product between the original vector and its normalized version should be non-negative
        assertTrue(v1.dotProduct(v1.normalize()) >= 0, "The normalized vector is opposite to the original one");
    }
}
