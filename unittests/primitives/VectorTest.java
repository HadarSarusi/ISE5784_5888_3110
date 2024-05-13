package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Testing Vector
 *
 * @author Lea &amp; Hadar
 */
class VectorTest {
    public static final Double3 ZERO = new Double3(0, 0, 0);
    private static final Vector v1=new Vector(1,2,3);
    private static final Vector v2=new Vector(-1,-2,-3);
    private static final Vector v3=new Vector(2,4,6);
    private static final Vector v4=new Vector(0,0,1);
    private static final Vector v5=new Vector(1,0,0);
    private static final Vector v7=new Vector(0,3,-2);
    private static final Vector v8=new Vector(3,-1,1);

    private static final Vector v6=v1.normalize();
    @Test
    public void testConstructor() {
        // =============== Boundary Values Tests ==================
        //TC11: vector equal zero
        assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
                "vector can't be zero");
        //TC12: vector equal zero
        assertThrows(IllegalArgumentException.class, () -> new Vector(ZERO), "vector can't be zero");

        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test
        assertDoesNotThrow(()->new Vector(1,2,3),"Failed constructing a correct vector");
    }
    /**
     * Test method for {@link primitives.Vector#add(Vector)}
     */
    @Test
    void add() {
        // ============ BVA ==============
        //TC11: Vector + -itself throw an exception
        assertThrows(IllegalArgumentException.class,()->v1.add(v2));
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test- add vectors work currently
        assertEquals(v1,v2.add(v3),"add vector failed");
    }
    /**
     * Test method for {@link primitives.Vector#scale(double)}
     */
    @Test
    void scale() {
        // ============ BVA ==============
        //TC11: Multiplying a vector by a zero scalar
        assertThrows(IllegalArgumentException.class,()->v1.scale(0),"vector can't be zero");
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test- multiplying a vactor by scalar
        assertEquals(v3,v1.scale(2),"scale vector failed");
    }
    /**
     * Test method for {@link primitives.Vector#dotProduct(Vector)}
     */
    @Test
    void dotProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test - dot product work currently
        assertEquals(28,v1.dotProduct(v3),"dot product failed");
        // ============ BVA ==============
        //TC11: dot product for orthogonal vectors is not zero
        assertEquals(0,v4.dotProduct(v5),"dot product for orthogonal vectors is not zero");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(Vector)}
     */
    @Test
    void crossProduct() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test - cross product work currently
         assertTrue(Util.isZero(v1.crossProduct(v7).length() - v1.length() * v7.length()), "CrossProduct returned wrong result length");
        // ============ BVA ==============
        //TC11: CrossProduct result orthogonal to its operands
        assertTrue(Util.isZero(v1.crossProduct(v8).dotProduct(v1))||Util.isZero(v1.crossProduct(v8).dotProduct(v8)),"CrossProduct result is not orthogonal to its operands");
        //TC12: vector can't be zero
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3), "CrossProduct for parallel vectors should throw Exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}
     */
    @Test
    void lengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test
        assertEquals(14,v1.lengthSquared(),"vector length squared failed");
    }
    /**
     * Test method for {@link Vector#length()}
     */
    @Test
    void length() {
         // ============ Equivalence Partitions Tests ==============
        //TC01: simple test
        assertEquals(Math.sqrt(14),v1.length(),"vector length failed");
    }
    /**
     * Test method for {@link Vector#normalize()}
     */
    @Test
    void normalize() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test
        assertEquals(1,v1.normalize().length(),"normalize vector failed");
        // ============ BVA ==============
        //TC11:The normalized vector is parallel to the original one
        assertThrows(Exception.class, () -> v1.crossProduct(v6), "The normalized vector is not parallel to the original one");
        //TC12:
        assertTrue(v1.dotProduct(v6) >= 0, "The normalized vector is opposite to the original one");

    }

}