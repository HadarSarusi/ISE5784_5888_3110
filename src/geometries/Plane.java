package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Plane class representing a two-dimensional plane in 3D Cartesian coordinate system.
 *
 * @author Lea &amp; Hadar
 */
public class Plane extends Geometry {

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
    public Plane(Point point, Vector normal) {
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

    /**
     * Finds the intersections of a given ray with a surface.
     *
     * <p>This method calculates the intersection points of the ray with the plane defined
     * by the object on which the method is called. If the ray intersects the plane,
     * the intersection points are returned as a list. If there are no intersections,
     * the method returns {@code null}.
     * </p>
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points, or {@code null} if there are no intersections
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

    public List<Vector> findVectorsOfPlane() {
        List<Vector> vectors = new LinkedList<>();

        Vector normalVector = getNormal();
        Point p0 = this.point;

        double nX = normalVector.getX(), nY = normalVector.getY(), nZ = normalVector.getZ();
        double pX = p0.getX(), pY = p0.getY(), pZ = p0.getZ();

        double[] normal = {nX, nY, nZ};


        double d = -(nX * pX + nY * pY + nZ * pZ);


        int i;
        double val = 0;
        for (i = 0; i < 3; i++) {
            if(!isZero(normal[i])) {
                val = normal[i];
                break;
            }
        }

        Vector v1 = null;
        switch (i) {
            case 0 -> v1 = new Vector(val, 0, 0);
            case 1 -> v1 = new Vector(0, val, 0);
            case 2 -> v1 = new Vector(0, 0, val);
        }


        assert v1 != null;
        Vector v2 = v1.crossProduct(normalVector);

        return List.of(v1.normalize(), v2.normalize());
    }
}
