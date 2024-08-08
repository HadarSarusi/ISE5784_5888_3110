package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The {@code Plane} class represents a two-dimensional plane in a 3D Cartesian coordinate system.
 * It is defined by a point on the plane and a normal vector perpendicular to the plane.
 * The plane can be used for geometric calculations and intersection tests with rays.
 *
 * @author Lea &amp; Hadar
 */
public class Plane extends Geometry {

    /**
     * A point on the plane.
     */
    private final Point point;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane using three distinct points. The normal vector is calculated as the cross product
     * of vectors formed by these points.
     *
     * @param point1 The first point on the plane.
     * @param point2 The second point on the plane.
     * @param point3 The third point on the plane.
     */
    Plane(Point point1, Point point2, Point point3) {
        this.point = point1;
        this.normal = (point2.subtract(point1)).crossProduct(point3.subtract(point1)).normalize();
    }

    /**
     * Constructs a plane using a point and a normal vector. The normal vector is normalized.
     *
     * @param point  The point on the plane.
     * @param normal The normal vector to the plane.
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Retrieves the normal vector to the plane at a given point on its surface.
     *
     * @param point The point on the surface of the plane.
     * @return The normal vector to the plane at the given point. For a plane, the normal vector is constant
     * across the entire surface, so the returned vector is always the same.
     */
    public Vector getNormal(Point point) {
        return normal;
    }

    /**
     * Retrieves the normal vector to the plane.
     *
     * @return The normal vector to the plane.
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Finds the intersections of a given ray with the plane.
     *
     * <p>This method calculates the intersection points of the ray with the plane. If the ray intersects the plane,
     * the intersection points are returned as a list. If there are no intersections, the method returns {@code null}.
     * </p>
     *
     * @param ray The ray to find intersections with.
     * @return A list of intersection points, or {@code null} if there are no intersections.
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        double nv = this.normal.dotProduct(v);
        if (isZero(nv) || this.point.equals(p0))
            return null;

        double nQMinusP0 = this.normal.dotProduct(this.point.subtract(p0));
        double t = alignZero(nQMinusP0 / nv);
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }

    /**
     * Finds two vectors that lie on the plane and are perpendicular to each other.
     * These vectors can be used to describe the orientation of the plane in 3D space.
     *
     * @return A list of two orthogonal vectors that lie on the plane.
     */
    public List<Vector> findVectorsOfPlane() {
        double nX = this.normal.getX(), nY = this.normal.getY(), nZ = this.normal.getZ();
        Vector v1;
        if (!isZero(nX)) {
            v1 = new Vector(nY, -nX, 0);
        } else if (!isZero(nY)) {
            v1 = new Vector(0, nZ, -nY);
        } else {
            v1 = new Vector(-nZ, 0, nX);
        }

        Vector v2 = v1.crossProduct(this.normal);
        return List.of(v1.normalize(), v2.normalize());
    }
}

