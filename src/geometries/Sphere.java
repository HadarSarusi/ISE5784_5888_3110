package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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
    public Sphere(Point center, double radius) {
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

    /**
     * Finds the intersections of a given ray with a Sphere.
     *
     * <p>This method calculates the intersection points of the ray with the Sphere defined
     * by the object on which the method is called. If the ray intersects the Sphere,
     * the intersection points are returned as a list. If there are no intersections,
     * the method returns {@code null}.
     * </p>
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    protected  List<GeoPoint> findGeoIntersectionsHelper(Ray ray){
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        if (this.center.equals(p0)) {
            return List.of(new GeoPoint(this,ray.getPoint(this.radius)));
        }
        Vector u = this.center.subtract(p0);
        double tm = v.dotProduct(u);
        double dSquared = u.lengthSquared() - tm * tm;
        double thSquared = this.radiusSquared - dSquared;
        if (alignZero(thSquared) <= 0) return null;//no intersection

        double th = Math.sqrt(thSquared);

        double t2 = tm + th; // always: t2 > t1
        if (alignZero(t2) <= 0) return null;

        double t1 = tm - th;
        return alignZero(t1) <= 0
                ? List.of(new GeoPoint(this,ray.getPoint(t2)))
                : List.of((new GeoPoint(this,ray.getPoint(t1))),( new GeoPoint(this,ray.getPoint(t2))));
    }
}

