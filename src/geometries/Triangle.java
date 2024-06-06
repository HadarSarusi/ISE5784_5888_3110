package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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

    /**
     * Finds the intersections of a given ray with a Triangle.
     *
     * <p>This method calculates the intersection points of the ray with the Triangle defined
     * by the object on which the method is called. If the ray intersects the Triangle,
     * the intersection points are returned as a list. If there are no intersections,
     * the method returns {@code null}.
     * </p>
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    public List<Point> findIntersections(Ray ray) {
        var intersection = this.plane.findIntersections(ray);
        if (intersection == null) return null;

        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        Vector v1 = this.vertices.get(0).subtract(p0);
        Vector v2 = this.vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double s1 = alignZero(v.dotProduct(n1));
        if (s1 == 0) return null;

        Vector v3 = this.vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double s2 = alignZero(v.dotProduct(n2));
        if (s1 * s2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double s3 = alignZero(v.dotProduct(n3));
        if (s1 * s3 <= 0) return null;

        return intersection;
    }
}
