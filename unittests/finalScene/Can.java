//package finalScene;
//
//import geometries.Geometries;
//import geometries.Plane;
//import geometries.Polygon;
//import geometries.Sphere;
//import lighting.AmbientLight;
//import lighting.PointLight;
//import lighting.SpotLight;
//import org.junit.jupiter.api.Test;
//import primitives.Color;
//import primitives.Material;
//import primitives.Point;
//import primitives.Vector;
//import renderer.Camera;
//import renderer.ImageWriter;
//import renderer.SimpleRayTracer;
//import scene.Scene;
//
//import java.util.Random;
//
//
//public class Can {
//    @Test
//    public void can() {
//        Color color = new Color(102, 102, 255);
//        Material material1 = new Material()
//                .setShininess(100) // ערך גבוה לברק
//                .setKd(0.1)        // מקדם דיפוזיה נמוך
//                .setKs(1)          // מקדם ספוקולרי גבוה כדי להדגיש את הברק
//                .setKt(0)          // מקדם העברה (transmission) נמוך, כי זה לא חומר שקוף
//                .setKr(0.9);
//        Material material = new Material()
//                .setShininess(30)
//                .setKd(0.5)//diffuse
//                .setKs(0.5)//specular
//                .setKt(0)//transmission
//                .setKr(0);//reflection
//        Geometries geometries = Stl.ConvertStlToGeometrys
//                ("unittests/finalScene/Soda_Can.stl", material1, color);
//
//        final Scene canScene = new Scene("canScene").setBackground(new Color(0, 0, 0)).setAmbientLight(new AmbientLight(new Color(0, 216, 230), 0.5));
//        /**
//         * Camera builder for the tests with triangles
//         */
//      //  Sphere sphere = new Sphere(new Point(0, 0, 0), 5d).setMaterial(material);
////        for (int i = 0; i < 7; i++) {
////
////        }
//
//        Camera.Builder cameraBuilder = Camera.getBuilder()
//                .setDirection( new Vector(-1, 0, -0.5),new Vector(-1, 0, -0.5).crossProduct(Vector.Y).scale(-1))
//                .setRayTracer(new SimpleRayTracer(canScene));
//        Random random = new Random();
//        canScene.geometries.add(geometries,
//                new Sphere(new Point(0,-1,6),0.3d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,-1.3,7),0.2d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,-0.7,5),0.25d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,-0.3,6.4),0.22d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,-0.5,5.6),0.21d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,0,5.9),0.19d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,0,6.9),0.23d).setEmission(new Color(java.awt.Color.blue)),
//                new Sphere(new Point(0,-1.4,5),0.23d).setEmission(new Color(java.awt.Color.blue))
//                ,
//                new Polygon(
//                        new Point(20,10,-10),
//                        new Point(-20,10,-10),
//                        new Point(-20,-10,-10),
//                        new Point(20,-10,-10)
//                ).setEmission(new Color(java.awt.Color.RED))
//        );
//        canScene.lights.add(
//                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
//                        .setKl(0.0004).setKq(0.0000006)
//        );
//        canScene.lights.add(
//                new PointLight(new Color(20, 128, 46), new Point(0, 0, 0))
//                        .setKl(0.0004).setKq(0.0000006)
//        );
//
//
//        cameraBuilder.setLocation(new Point(15, 0, 10)).setVpDistance(500d)
//                .setVpSize(500d, 500d)
//                .setImageWriter(new ImageWriter("canScene", 500, 500))
//                .build()
//                .renderImage()
//                .writeToImage();
//    }.
//}

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


public class Can {
    @Test
    public void can() {
        Color canColor = new Color(100,120,10);
        Color bubbleColor = new Color(10, 10, 10);

        Material material1 = new Material()
                .setShininess(100) // shine
                .setKd(0.4)        //diffuse
                .setKs(0.9)          // specular
                .setKt(0)          //reflection
                .setKr(0.9);    //refrection

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
                .setKt(0)
                .setKr(1);

        Material wallsMaterial = new Material()
                .setShininess(50)
                .setKd(0.5)
                .setKs(0.3)
                .setKt(0)
                .setKr(0.1);

        Geometries geometries = Stl.ConvertStlToGeometrys
                ("unittests/finalScene/Soda_Can.stl", material1, canColor);

        final Scene canScene = new Scene("canScene").setBackground(new Color(0, 0, 10)).setAmbientLight(new AmbientLight(new Color(10, 20, 30), 1d));

        Camera.Builder cameraBuilder = Camera.getBuilder()
                .setDirection( new Vector(-1, 0, -0.5),new Vector(-1, 0, -0.5).crossProduct(Vector.Y).scale(-1))
                .setRayTracer(new SimpleRayTracer(canScene));

        canScene.geometries.add(geometries,
                new Sphere(new Point(0,-1,6),0.3d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,-1.3,7),0.2d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0,-0.7,5),0.25d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,-0.3,6.4),0.22d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0,-0.5,5.6),0.21d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,0,5.9),0.19d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),
                new Sphere(new Point(0,0,6.9),0.23d).setEmission(bubbleColor).setMaterial(transparentMaterial),
                new Sphere(new Point(0,-1.4,5),0.23d).setEmission(bubbleColor).setMaterial(reflectiveMaterial),

                // floor
                new Polygon(
                        new Point(20,10,-5),
                        new Point(-15,10,-5),
                        new Point(-15,-10,-5),
                        new Point(20,-10,-5)
                ).setEmission(new Color(0,0,0)).setMaterial(wallsMaterial),

                // walls
                // right wall
                new Polygon(
                        new Point(-15, 10, -5),
                        new Point(20, 10, -5),
                        new Point(20,10,12),
                        new Point(-15,10,12)
                ).setEmission(new Color(0,0,0)).setMaterial(wallsMaterial),

                // back
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(-15, 10, -5),
                        new Point(-15, 10, 12),
                        new Point(-15, -10, 12)
                ).setEmission(new Color(0,0,0)).setMaterial(wallsMaterial),

                // left
                new Polygon(
                        new Point(-15, -10, -5),
                        new Point(20, -10, -5),
                        new Point(20, -10, 12),
                        new Point(-15, -10, 12)
                ).setEmission(new Color(0,0,0)).setMaterial(wallsMaterial)
        );
        canScene.lights.add(
                new SpotLight(new Color(255,255,255), new Point(-100, -100, 300), new Vector(-1, -1, -2))
                        .setKl(0.004).setKq(0.00006)
        );
        canScene.lights.add(
                new PointLight(new Color(255,255,255), new Point(-10, -5, 10))
                        .setKl(0.0004).setKq(0.00006)
        );

        cameraBuilder.setLocation(new Point(15, 0, 10)).setVpDistance(370d)
                .setVpSize(500d, 500d)
                .setImageWriter(new ImageWriter("test7-newCanColor-newAmbientColor-2", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}

