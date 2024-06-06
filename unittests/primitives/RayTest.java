package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * Tests the {@link Ray#getPoint(double)} method.
     * This method checks if the point returned by the {@code getPoint} method is correct based on the given parameter {@code t}.
     */
    @Test
    void getPoint() {
        Ray ray = new Ray(new Point(1, 1, 0), new Vector(0, 1, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: t>0
        assertEquals(new Point(1, 3, 0), ray.getPoint(2));
        //TC02: t<0
        assertEquals(new Point(1, -1, 0), ray.getPoint(-2));
        // =============== Boundary Values Tests ==================
        //TC10: t=0
        assertEquals(new Point(1, 1, 0), ray.getPoint(0));
    }
}