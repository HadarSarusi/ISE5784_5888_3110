package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * This class extends the `RadialGeometry` class and represents a sphere with a given center and radius.
 *
 * @author Lea &amp; Hadar
 */
public class Sphere extends RadialGeometry {
    /**
     * The center point of the sphere.
     */
    private final Point center;

    /**
     * Constructs a sphere with the specified center point and radius.
     *
     * @param center The center point of the sphere.
     * @param radius The radius of the sphere.
     */
    Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    /**
     * Calculates the normal vector to the sphere at the specified point.
     *
     * @param point The point on the surface of the sphere.
     * @return The normal vector to the sphere at the given point.
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }

    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        if(this.center.equals(p0)){
            return List.of(p0.add(v.scale(this.radius)));
        }
        Vector u = this.center.subtract(p0);
        double tm = v.dotProduct(u);
        double d = Math.sqrt((u.lengthSquared()) - (Math.pow(tm, 2)));
        double th = Math.sqrt((Math.pow(this.radius, 2)) - (Math.pow(d, 2)));

        if (d >= radius) {
            return null;//no intersection
        }
        double t1 = tm - th;
        double t2 = tm + th;
        if (t1 > 0 && t2 > 0) {
            return List.of(p0.add(v.scale(t1)), p0.add(v.scale(t2)));
        }

        if (t1 > 0) {
            return List.of(p0.add(v.scale(t1)));
        }
        if (t2 > 0) {
            return List.of(p0.add(v.scale(t2)));
        }
        return null;
    }
}

