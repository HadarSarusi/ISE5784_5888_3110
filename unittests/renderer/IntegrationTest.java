package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.security.KeyStore;
import java.util.List;

/**
 * Testing integration between Camera and Geometry.
 *
 * @author Hadar &amp; Lea
 */
class IntegrationTest {

    /**
     * A builder for constructing a {@link Camera} with specific configurations:
     * <ul>
     *   <li>Viewport size: 3.0 by 3.0 units</li>
     *   <li>Direction vectors: (0, 0, -1) and (0, -1, 0)</li>
     *   <li>Viewport distance: 1.0 unit</li>
     * </ul>
     * Optional ray tracer and image writer settings are commented out.
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            // .setRayTracer(new SimpleRayTracer(new Scene("Test")))
            //.setImageWriter(new ImageWriter("Test", 1, 1))
            .setVpSize(3.0, 3.0)
            .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
            .setVpDistance(1.0);

    /**
     * Counts the number of intersection points between rays constructed by the camera and the given geometry.
     *
     * @param camera   the camera used to construct the rays
     * @param geometry the geometry to test for intersections
     * @return the number of intersection points
     */
    int helpTest(Camera camera, Intersectable geometry) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Ray ray = camera.constructRay(3, 3, i, j);
                if (geometry.findIntersections(ray) != null)
                    count += geometry.findIntersections(ray).size();
            }
        }
        return count;
    }

    /**
     * A {@link Camera} instance built with the configured builder,
     * located at the origin (0, 0, 0).
     */
    Camera camera = cameraBuilder.setLocation(new Point(0, 0, 0)).build();

    /**
     * Test integration  method for
     * {@link renderer.Camera#constructRay(int, int, int, int) and
     *
     * @link geometries.Sphere#findIntersections(Ray)}.
     */
    @Test
    void sphereIntegrationTest() {

        //TC01: Small Sphere 2 points
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1d);
        assertEquals(2, helpTest(camera, sphere));
        //TC02: Big Sphere 18 points
        Camera camera1 = cameraBuilder.setLocation(new Point(0, 0, 0.5)).build();
        Sphere sphere1 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, helpTest(camera1, sphere1));
        //TC03: Medium Sphere 10 points
        Sphere sphere2 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, helpTest(camera1, sphere2));
        //TC04: Inside Sphere 9 points
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 4);
        assertEquals(9, helpTest(camera1, sphere3));
        // TC05: Beyond Sphere 0 points
        Sphere sphere4 = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, helpTest(camera1, sphere4));
    }

    /**
     * Test integration  method for
     * {@link renderer.Camera#constructRay(int, int, int, int) and
     *
     * @link geometries.Plane#findIntersections(Ray)}.
     */
    @Test
    void planeIntegrationTest() {
        //TC01: The plane is parallel to the view plane
        Plane plane = new Plane(new Point(0, 0, -3), new Vector(0, 0, -1));
        assertEquals(9, helpTest(camera, plane));
        //TC02:
        Plane plane1 = new Plane(new Point(0, 0, -3), new Vector(0.25, 0.25, -1));
        assertEquals(9, helpTest(camera, plane1));
        //TC03:
        Plane plane2 = new Plane(new Point(0, 0, -3), new Vector(1, 1, -1));
        assertEquals(6, helpTest(camera, plane2));
    }

    /**
     * Test integration  method for
     * {@link renderer.Camera#constructRay(int, int, int, int) and
     *
     * @link geometries.Triangle#findIntersections(Ray)}.
     */
    @Test
    void triangleIntegrationTest() {

        //TC01: small triangle
        Triangle triangle = new Triangle(new Point(0, 1, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        assertEquals(1, helpTest(camera, triangle));

        //TC02: big triangle
        Triangle triangle1 = new Triangle(new Point(0, 20, -2), new Point(-1, -1, -2), new Point(1, -1, -2));
        assertEquals(2, helpTest(camera, triangle1));

    }
}
