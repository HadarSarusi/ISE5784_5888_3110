package finalScene;

import geometries.*;
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
 * The `CanWithImprove` class is a test class for rendering a 3D scene featuring an improved soda can model
 * with additional bubble spheres and more detailed material properties. It configures various elements such
 * as lighting, materials, and geometries to create a realistic and visually appealing scene.
 *
 * <p>Test Method:</p>
 * <ul>
 *     <li><code>canI()</code> - Sets up and renders a scene with a soda can and several bubbles, using advanced
 *     material properties and lighting. The result is saved as an image.</li>
 * </ul>
 *
 * @see org.junit.jupiter.api.Test
 */
public class CanWithImprove {

    /**
     * Configures and renders a scene with a soda can model and additional bubbles.
     * The scene includes a background color, ambient light, and various light sources.
     *
     * <p>This method also demonstrates the use of enhanced material properties such as the number of rays
     * reflected and refracted, as well as their cone angles.</p>
     */
    @Test
    public void canI() {
        // Define colors for the soda can and bubbles
        Color canColor = new Color(100, 120, 10); // Color of the soda can
        Color bubbleColor = new Color(10, 10, 10); // Color of the bubbles

        // Define materials with enhanced properties
        Material canMaterial = new Material()
                .setShininess(300) // High shininess for a glossy look
                .setKd(0.4)        // Diffuse reflection coefficient
                .setKs(0.9)        // Specular reflection coefficient
                .setKt(0)          // No transmission (opaque)
                .setKr(1)  // High reflection
               .setNumRaysReflected(20) // Number of rays for reflection
                .setConeAngleReflected(5); // Cone angle for reflection

        Material transparentMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.7)        // High transmission for transparency
                .setKr(0.1)
                .setNumRaysRefracted(10)
                .setConeAngleRefracted(5);

        Material reflectiveMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.3)
                .setKr(0.7)     ;   // Reflective material
//                .setNumRaysReflected(10)
//                .setConeAngleReflected(30);

        Material wallsMaterial = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(0)
                .setKr(0.1);     // Low reflection for walls

        Material wallsMaterialDiffuse = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(1)
                .setKr(0.1)
                .setNumRaysRefracted(10) // Number of rays for refraction
                .setConeAngleRefracted(5);// Low reflection for walls

        // Load geometries from STL file with specified material and color
        Geometries geometries = Stl.ConvertStlToGeometrys("unittests/finalScene/Soda_Can.stl", canMaterial, canColor);

        // Create the scene with background color and ambient light
        final Scene canScene = new Scene("canScene1")
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
                new Sphere(new Point(0, -1.4, 8), 0.35d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, -1.6, 6), 0.38d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),

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
                .setImageWriter(new ImageWriter("test14", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}

