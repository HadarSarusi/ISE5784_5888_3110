/**
 *
 */
package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), Vector.Y)
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        scene.geometries.add(
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000d)
                .setVpSize(150d, 150d)
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
//                .setImageWriter(new ImageWriter("refractionTwoSpheres", 1, 1)) // debug
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        scene.geometries.add(
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                .setKl(0.00001).setKq(0.000005));

        cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000d)
                .setVpSize(2500d, 2500d)
                .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        scene.geometries.add(
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7));

        cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000d)
                .setVpSize(200d, 200d)
                .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }
    @Test
    public void complexImageTest() {
        // Create a camera object with position, direction, and up vector
         final Camera.Builder cameraBuilder1 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), Vector.Y)
                .setRayTracer(new SimpleRayTracer(scene));
        // Set the ambient light in the scene
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

        // Add geometries to the scene
        scene.geometries.add(
                // Add a triangle with three points and set its material properties
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.09)),

                // Add another triangle with three points and set its material properties
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKr(0.09)),

                // Add a sphere with radius and center point, and set its emission and material properties
                new Sphere(new Point(60, 50, -50),30d )
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKr(0.5)),

                // Add another sphere with radius and center point, and set its emission and material properties
                new Sphere(new Point(-40, -30, -80),40d)
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(50).setKt(0.8)),

                // Add another sphere with radius and center point, and set its emission and material properties
                new Sphere( new Point(-40, -30, -80),20d)
                        .setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(10).setKr(0.8))
        );

        // Add lights to the scene
        scene.lights.add(new SpotLight(new Color(400, 400, 400), new Point(-40, -30, 0),
                new Vector(0, 0, -1)).setKl(0.0005).setKq(0.0005));
        scene.lights.add(new SpotLight(new Color(800, 800, 800), new Point(60, 50, 0),
                new Vector(0, 0, -1)).setKl(0.0005).setKq(0.0005));
        scene.lights.add(new DirectionalLight(new Color(100, 50, 100), new Vector(-1, -1, -1)));

        cameraBuilder1.setLocation(new Point(0, 0, 1000)).setVpSize(200d, 200d)
                .setVpDistance(1000d)
                .setImageWriter(new ImageWriter("complexImageTest", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }
}