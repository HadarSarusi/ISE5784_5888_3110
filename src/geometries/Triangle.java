package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * This class extends the `Polygon` class and represents a triangle with three vertices.
 *
 * @author Lea &amp; Hadar
 */
public class Triangle extends Polygon {
    /**
     * Constructs a triangle with the specified vertices.
     *
     * @param point1 The first vertex of the triangle.
     * @param point2 The second vertex of the triangle.
     * @param point3 The third vertex of the triangle.
     */
    Triangle(Point point1, Point point2, Point point3) {
        super(point1, point2, point3);
    }
    /**
     * Retrieves the normal vector to the triangle.
     *
     * @param point The point on the triangle.
     * @return The normal vector to the triangle.
     */
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }
    public List<Point> findIntersections(Ray ray){return null;}
}
