package geometries;

import primitives.*;

/**
 * Plane class representing a two-dimensional plane in 3D Cartesian coordinate system.
 *
 * @author Lea &amp; Hadar
 */
public class Plane implements Geometry {

    /**
     * The point on the plane.
     */
    private final Point point;

    /**
     * The normal vector to the plane.
     */
    private final Vector normal;

    /**
     * Constructs a plane with three points.
     * The normal vector is calculated as the cross product of vectors from the first point to the other two points.
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
     * Constructs a plane with a point and a normal vector.
     *
     * @param point  The point on the plane.
     * @param normal The normal vector to the plane.
     */
    Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
    }

    /**
     * Retrieves the normal vector to the plane at a given point on its surface.
     *
     * @param point The point on the surface of the plane.
     * @return The normal vector to the plane at the given point.
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
}
