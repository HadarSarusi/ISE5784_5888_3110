package renderer;

import geometries.Intersectable;
import geometries.Sphere;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.security.KeyStore;
import java.util.List;

class IntegrationTest {

    private static final int Nx = 3;
    private static final int Ny = 3;


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

    @Test
    void sphereIntegrationTest() {

        //TC01: Small Sphere 2 points
        Camera camera = new Camera.Builder().
                setLocation(new Point(0, 0, 0))
                .setVpDistance(1d)
                .setDirection(new Vector(0, 1, 0), new Vector(0, 0, -1)).
                setVpSize(3d, 3d).build();
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1d);
        assertEquals(2, helpTest(camera, sphere));
        //TC02: Big Sphere 18 points
        Camera camera1 = new Camera.Builder().setLocation(new Point(0, 0, 0.5)).
                setVpDistance(1d)
                .setDirection(new Vector(0, 1, 0.5), new Vector(0, 0, -1.5)).
                setVpSize(3d, 3d).build();
        Sphere sphere1 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, helpTest(camera1, sphere1));
        //TC03: Medium Sphere 10 points
        Camera camera2 = new Camera.Builder().setLocation(new Point(0, 0, 0.5)).
                setVpDistance(1d)
                .setDirection(new Vector(0, 1, 0), new Vector(0, 0, -1)).
                setVpSize(3d, 3d).build();
        Sphere sphere2 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, helpTest(camera2, sphere2));
        //TC04: Inside Sphere 9 points
        Camera camera3 = new Camera.Builder().setLocation(new Point(0, 0, 0.5)).
                setVpDistance(1d)
                .setDirection(new Vector(0, 1, 0), new Vector(0, 0, -1)).
                setVpSize(3d, 3d).build();
        Sphere sphere3 = new Sphere(new Point(0, 0, -2), 4);
        assertEquals(10, helpTest(camera3, sphere3));
        // TC05: Beyond Sphere 0 points
        Camera camera4 = new Camera.Builder().setLocation(new Point(0, 0, 0.5)).
                setVpDistance(1d)
                .setDirection(new Vector(0, 1, 0), new Vector(0, 0, -1)).
                setVpSize(3d, 3d).build();
        Sphere sphere4 = new Sphere(new Point(0, 0, 1), 0.5);
        assertEquals(0, helpTest(camera4, sphere4));


    }

    @Test
    void planeIntegrationTest() {
    }

    @Test
    void triangleIntegrationTest() {
    }
}
