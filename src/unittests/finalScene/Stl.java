package finalScene;

import geometries.Geometries;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.LinkedList;
import java.util.List;

/**
 * Utility class for converting STL files into geometrical representations.
 */
public class Stl {

    /**
     * Converts an STL file to a Geometries object.
     *
     * @param path     the path of the STL file to be converted.
     * @param material the material to be applied to the geometries.
     * @param color    the color to be applied to the geometries.
     * @return a Geometries object representing the STL file.
     */
    public static Geometries ConvertStlToGeometrys(String path, Material material, Color color) {
        List<Triangle> triangles = convertSTLToTriangles(path);
        System.out.print("convertSTLToTriangles\n");
        Geometries geometries = triangleToGeometries(triangles, material, color);
        System.out.print("triangleToGeometries\n");
        return geometries;
    }

    /**
     * Converts an STL file to a list of triangles.
     *
     * @param filePath the path of the STL file to be converted.
     * @return a list of triangles representing the STL file.
     */
    private static List<Triangle> convertSTLToTriangles(String filePath) {
        List<Triangle> triangles = new LinkedList<>();

        try (RandomAccessFile binaryFile = new RandomAccessFile(filePath, "r")) {
            byte[] header = new byte[80];
            binaryFile.read(header); // Read the binary STL file header (80 bytes)

            byte[] numTrianglesBytes = new byte[4];
            binaryFile.read(numTrianglesBytes); // Read the number of triangles (4 bytes)
            int numTriangles = ByteBuffer.wrap(numTrianglesBytes).order(ByteOrder.LITTLE_ENDIAN).getInt();

            for (int i = 0; i < numTriangles; i++) {
                byte[] normalBytes = new byte[12];
                binaryFile.read(normalBytes); // Read the normal vector (12 bytes)
                float[] normal = new float[3];
                ByteBuffer.wrap(normalBytes).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer().get(normal);

                float[][] vertices = new float[3][3];
                for (int j = 0; j < 3; j++) {
                    byte[] vertexBytes = new byte[12];
                    binaryFile.read(vertexBytes); // Read each vertex of the triangle (12 bytes per vertex)
                    ByteBuffer.wrap(vertexBytes).order(ByteOrder.LITTLE_ENDIAN).asFloatBuffer().get(vertices[j]);
                }

                byte[] garbage = new byte[2];
                binaryFile.read(garbage); // Read the garbage data (2 bytes)

                // Create a triangle using the extracted vertex coordinates
                Triangle triangle = new Triangle(
                        new Point(vertices[0][0], vertices[0][1], vertices[0][2]),
                        new Point(vertices[1][0], vertices[1][1], vertices[1][2]),
                        new Point(vertices[2][0], vertices[2][1], vertices[2][2])
                );

                triangles.add(triangle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return triangles;
    }

    /**
     * Converts a list of triangles to a Geometries object with specified material and color.
     *
     * @param triangles the list of triangles to be converted.
     * @param material  the material to be applied to the geometries.
     * @param color     the color to be applied to the geometries.
     * @return a Geometries object containing the triangles with the specified material and color.
     */
    private static Geometries triangleToGeometries(List<Triangle> triangles, Material material, Color color) {
        Geometries geometries = new Geometries();
        for (Triangle triangle : triangles) {
            geometries.add(triangle.setMaterial(material).setEmission(color));
        }
        return geometries;
    }
}

