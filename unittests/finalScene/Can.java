package finalScene;

import geometries.Geometries;
import geometries.Plane;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

import java.util.Random;

/**
 * The `Can` class contains a test for rendering a 3D scene with a soda can model.
 * It sets up a scene with various lighting sources, materials, and geometric objects,
 * including spheres and polygons. The test renders the scene using a camera and saves
 * the result as an image.
 *
 * <p>Test Method:</p>
 * <ul>
 *     <li><code>can()</code> - Configures and renders the scene with the soda can and other objects.</li>
 * </ul>
 *
 * @see org.junit.jupiter.api.Test
 */
public class Can {

    /**
     * Renders a scene featuring a soda can model along with various spherical and polygonal
     * objects to create a realistic scene. Configures lighting, camera, and materials to produce
     * the final rendered image.
     */
    @Test
    public void can() {
        // Define colors for the soda can and bubbles
        Color canColor = new Color(100, 120, 10); // Color for the soda can
        Color bubbleColor = new Color(10, 10, 10); // Color for the bubbles

        // Define materials for different objects
        Material material1 = new Material()
                .setShininess(100) // High shininess for the soda can
                .setKd(0.4)        // Low diffuse reflection
                .setKs(0.9)        // High specular reflection
                .setKt(0)          // No transmission (non-transparent)
                .setKr(0.9);       // High reflection

        Material transparentMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.7)        // High transmission (partially transparent)
                .setKr(0.1);

        Material reflectiveMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.3)
                .setKr(0.7);       // Reflective

        Material surfaceMaterial = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(0)
                .setKr(1);         // High reflection

        Material wallsMaterial = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(0)
                .setKr(0.1);       // Low reflection

        // Load geometries from STL file with specified material and color
        Geometries geometries = Stl.ConvertStlToGeometrys("unittests/finalScene/Soda_Can.stl", material1, canColor);

        // Create the scene with background color and ambient light
        final Scene canScene = new Scene("canScene")
                .setBackground(new Color(0, 0, 10)) // Dark blue background
                .setAmbientLight(new AmbientLight(new Color(10, 20, 30), 1d)); // Ambient light

        // Configure the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setDirection(new Vector(-1, 0, -0.5), new Vector(-1, 0, -0.5).crossProduct(Vector.Y).scale(-1))
                .setRayTracer(new SimpleRayTracer(canScene));

        // Add geometries to the scene
        canScene.geometries.add(geometries,
                new Sphere(new Point(0, -1, 6), 0.3d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, -1.3, 7), 0.2d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, -0.7, 5), 0.25d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, -0.3, 6.4), 0.22d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, -0.5, 5.6), 0.21d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, 0, 5.9), 0.19d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, 0, 6.9), 0.23d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, -1.4, 5), 0.23d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),

                // Floor
                new Polygon(
                        new Point(20, 10, -5),
                        new Point(-15, 10, -5),
                        new Point(-15, -10, -5),
                        new Point(20, -10, -5)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial),

                // Walls
                // Right wall
                new Polygon(
                        new Point(-15, 10, -5),
                        new Point(20, 10, -5),
                        new Point(20, 10, 12),
                        new Point(-15, 10, 12)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial),

                // Back wall
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(-15, 10, -5),
                        new Point(-15, 10, 12),
                        new Point(-15, -10, 12)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial),

                // Left wall
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(20, -10, -5),
                        new Point(20, -10, 12),
                        new Point(-15, -10, 12)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial)
        );

        // Add light sources to the scene
        canScene.lights.add(
                new SpotLight(new Color(255, 255, 255), new Point(-100, -100, 300), new Vector(-1, -1, -2))
                        .setKl(0.004).setKq(0.00006)
        );
        canScene.lights.add(
                new PointLight(new Color(255, 255, 255), new Point(-10, -5, 10))
                        .setKl(0.0004).setKq(0.00006)
        );

        // Build and render the image
        cameraBuilder.setLocation(new Point(15, 0, 10)).setVpDistance(370d)
                .setVpSize(500d, 500d)
                .setImageWriter(new ImageWriter("test7-newCanColor-newAmbientColor-2", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}


