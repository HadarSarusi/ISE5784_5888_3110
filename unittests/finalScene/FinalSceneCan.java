package finalScene;

import geometries.Geometries;
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

public class FinalSceneCan {

    @Test
    public void canScene() {



        Color color = new Color(255,0,0);
        Material material1 = new Material()
                .setShininess(100) // ערך גבוה לברק
                .setKd(0.6)        // מקדם דיפוזיה נמוך
                .setKs(0.4)          // מקדם ספוקולרי גבוה כדי להדגיש את הברק
                .setKt(0.00001)          // מקדם העברה (transmission) נמוך, כי זה לא חומר שקוף
                .setKr(0.8);

        Material transparentMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.7)
                .setKr(0.1);

        Material reflectiveMaterial = new Material()
                .setShininess(30)
                .setKd(0.1)
                .setKs(0.5)
                .setKt(0.3)
                .setKr(0.7);

        Material surfaceMaterial = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(0.8) // משטח שקוף
                .setKr(0.2);

        Geometries geometries = Stl.ConvertStlToGeometrys
                ("unittests/finalScene/Soda_Can.stl", material1, color);

        final Scene finalCanScene = new Scene("finalCanScene")
                .setBackground(new Color(80, 80, 80)) // Darker background to match wood
                .setAmbientLight(new AmbientLight(new Color(30, 30, 40), 0.1));

        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setDirection( new Vector(-1, 0, -0.5),new Vector(-1, 0, -0.5).crossProduct(Vector.Y).scale(-1))
                .setRayTracer(new SimpleRayTracer(finalCanScene));
        Color bubbleColor=new Color(60, 30, 30);
        finalCanScene.geometries.add(geometries,
                new Sphere(new Point(0,-1,6),0.3d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,-1.3,7),0.2d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0,-0.7,5),0.25d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,-0.3,6.4),0.22d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0,-0.5,5.6),0.21d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,0,5.9),0.19d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0,0,6.9),0.23d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,-1.4,5),0.23d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Polygon(
                        new Point(15,10,-5),
                        new Point(-15,10,-5),
                        new Point(-15,-10,-5),
                        new Point(15,-10,-5)
                ).setEmission(new Color(0,0,0)).setMaterial(surfaceMaterial), new Polygon(
                        new Point(15, 10, -5),
                        new Point(15, 10, 10),
                        new Point(-15, 10, 10),
                        new Point(-15, 10, -5)),
                new Polygon(
                        new Point(-15, 10, -5),
                        new Point(-15, 10, 10),
                        new Point(-15, -10, 10),
                        new Point(-15, -10, -5)
                ),
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(-15, -10, 10),
                        new Point(15, -10, 10),
                        new Point(15, -10, -5)
                ));
        finalCanScene.lights.add(
                new SpotLight(new Color(255,255,255), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006)
        );
        finalCanScene.lights.add(
                new PointLight(new Color(200,255,255), new Point(0, 0, 0))
                        .setKl(0.0004).setKq(0.0000006)
        );
        // Lighting setup
//        finalCanScene.lights.add(new SpotLight(new Color(255, 200, 150), new Point(5, 10, 5), new Vector(-1, -2, -1))
//                .setKl(0.0001).setKq(0.00005));
//        finalCanScene.lights.add(new PointLight(new Color(150, 150, 200), new Point(-10, 5, 10))
//                .setKl(0.0001).setKq(0.00005));
        finalCanScene.lights.add(new DirectionalLight(new Color(50, 50, 50), new Vector(0, -1, -0.5)));
finalCanScene.lights.add(new SpotLight(new Color(60, 30, 30),new Point(0,0,0),new Vector(1,1,50)));

        cameraBuilder.setLocation(new Point(15, 0, 10)).setVpDistance(500d)
                .setVpSize(500d, 500d)
                .setImageWriter(new ImageWriter("canScene", 500, 500))
                .build()
                .renderImage()
                .writeToImage();

    }
}