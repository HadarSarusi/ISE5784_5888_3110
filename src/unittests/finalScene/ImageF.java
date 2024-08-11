package finalScene;

import geometries.Geometries;
import geometries.Polygon;
import geometries.Sphere;
import lighting.AmbientLight;
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

/**
 * The {@code ImageF} class contains a test method that configures and renders a scene
 * featuring a soda can model and additional bubbles. The scene includes various
 * geometries, materials, and light sources to enhance the visual realism.
 */
public class ImageF {

    /**
     * Configures and renders a scene with a soda can model and additional bubbles.
     * The scene includes a background color, ambient light, and various light sources.
     *
     * <p>This method demonstrates the use of enhanced material properties such as the
     * number of rays reflected and refracted, as well as their cone angles.</p>
     */
    @Test
    public void canI() {
        // Define colors for the soda can and bubbles
        Color bubbleColor = new Color(255, 10, 10); // Color of the bubbles

        // Define materials with different properties
        Material transparentMaterial = new Material()
                .setShininess(300)
                .setKd(0.2)
                .setKs(0.8)
                .setKt(0)        // High transmission for transparency
                .setKr(0.8);
                //.setNumRaysReflected(10)
                //.setConeAngleReflected(20);

        Material wallsMaterial = new Material()
                .setShininess(20)
                .setKd(0.9)
                .setKs(0.1)
                .setKt(0.8)
                .setKr(0);     // Low reflection for walls

        Material wallsMaterialDiffuse = new Material()
                .setShininess(50)
                .setKd(0.1)
                .setKs(0.2)
                .setKt(0.8)
                .setKr(0.1)
                .setNumRaysRefracted(50)
                .setConeAngleRefracted(20)
                .setNumRaysReflected(5)
                .setConeAngleReflected(5);

        // Create the scene with background color and ambient light
        final Scene canScene = new Scene("scene99")
                .setBackground(new Color(0, 0, 10)) // Dark blue background
                .setAmbientLight(new AmbientLight(new Color(10, 20, 30), 1d)); // Ambient light

        // Configure the camera
        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setDirection(new Vector(-1, 0, -0.5), new Vector(-1, 0, -0.5).crossProduct(Vector.Y).scale(-1))
                .setRayTracer(new SimpleRayTracer(canScene));

        // Add geometries to the scene
        canScene.geometries.add(
                new Sphere(new Point(-2, 0, 0), 3d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(-10, 7, 0), 5d).setEmission(new Color(0, 255, 0)).setMaterial(transparentMaterial),
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
                        new Point(1, -10, -5),
                        new Point(1, 10, -5),
                        new Point(1, 10, 5),
                        new Point(1, -10, 5)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterialDiffuse),

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
                .setImageWriter(new ImageWriter("test19", 500, 500))
                .build()
                .setMultithreading(-2)
                .renderImage()
                .writeToImage();
    }
}


