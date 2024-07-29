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

public class CanWithImprove {
    @Test
    public void canI() {
        Color canColor = new Color(100, 120, 10);
        Color bubbleColor = new Color(10, 10, 10);

        Material canMaterial = new Material()
                .setShininess(100) // shine
                .setKd(0.4)        //diffuse
                .setKs(0.9)          // specular
                .setKt(0)          //reflection
                .setKr(0.9)//refrection
                .setNumRaysReflected(50).setConeAngleReflected(30)
                .setNumRaysRefracted(50).setConeAngleRefracted(30);

        Material transparentMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.7)
                .setKr(0.1)
                .setNumRaysReflected(50).setConeAngleReflected(30)
                .setNumRaysRefracted(50).setConeAngleRefracted(30);
        ;

        Material reflectiveMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.3)
                .setKr(0.7)
                .setNumRaysReflected(50).setConeAngleReflected(30)
                .setNumRaysRefracted(50).setConeAngleRefracted(30);
        ;

        Material wallsMaterial = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(0)
                .setKr(0.1)
                .setNumRaysReflected(50).setConeAngleReflected(30)
                .setNumRaysRefracted(50).setConeAngleRefracted(30);
        ;

        Geometries geometries = Stl.ConvertStlToGeometrys
                ("unittests/finalScene/Soda_Can.stl", canMaterial, canColor);
        final Scene canScene = new Scene("canScene1").setBackground(new Color(0, 0, 10)).setAmbientLight(new AmbientLight(new Color(10, 20, 30), 1d));

        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setDirection(new Vector(-1, 0, -0.5), new Vector(-1, 0, -0.5).crossProduct(Vector.Y).scale(-1))
                .setRayTracer(new SimpleRayTracer(canScene));

        canScene.geometries.add(geometries,
                new Sphere(new Point(0, -1, 6), 0.3d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, -1.3, 7), 0.2d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, -0.7, 5), 0.25d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, -0.3, 6.4), 0.22d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, -0.5, 5.6), 0.21d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, 0, 5.9), 0.19d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, 0, 6.9), 0.23d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0, -1.4, 5), 0.23d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0, -1.4, 8), 0.35).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
        new Sphere(new Point(0, -1.6, 6), 0.38d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),

                // floor
                new Polygon(
                        new Point(20, 10, -5),
                        new Point(-15, 10, -5),
                        new Point(-15, -10, -5),
                        new Point(20, -10, -5)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial),

                // walls
                // right wall
                new Polygon(
                        new Point(-15, 10, -5),
                        new Point(20, 10, -5),
                        new Point(20, 10, 12),
                        new Point(-15, 10, 12)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial),

                // back
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(-15, 10, -5),
                        new Point(-15, 10, 12),
                        new Point(-15, -10, 12)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial),

                // left
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(20, -10, -5),
                        new Point(20, -10, 12),
                        new Point(-15, -10, 12)
                ).setEmission(new Color(0, 0, 0)).setMaterial(wallsMaterial)
        );
        canScene.lights.add(
                new SpotLight(new Color(255, 255, 255), new Point(-100, -100, 300), new Vector(-1, -1, -2))
                        .setKl(0.004).setKq(0.00006)
        );
        canScene.lights.add(
                new PointLight(new Color(255, 255, 255), new Point(-10, -5, 10))
                        .setKl(0.0004).setKq(0.00006)
        );

        cameraBuilder.setLocation(new Point(15, 0, 10)).setVpDistance(370d)
                .setVpSize(500d, 500d)
                .setImageWriter(new ImageWriter("test-9_can_with_improve", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
