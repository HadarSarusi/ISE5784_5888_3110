package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.isZero;

/**
 * Tube class representing a three-dimensional tube in 3D Cartesian coordinate system.
 * This class extends the `RadialGeometry` class and represents a tube with a given axis and radius
 *
 * @author Lea &amp; Hadar
 */
public class Tube extends RadialGeometry {

    /**
     * The axis of the tube.
     */
    protected final Ray axis;

    /**
     * Constructs a tube with the specified axis and radius.
     *
     * @param axis   The axis of the tube.
     * @param radius The radius of the tube.
     */
    Tube(Ray axis, double radius) {
        super(radius);
        this.axis = axis;
    }

    /**
     * Calculates the normal vector at a given point on the surface of the tube.
     *
     * @param point The point on the surface of the tube.
     * @return The normal vector at the given point.
     * @throws IllegalArgumentException if the given point is on the axis of the tube.
     */
    public Vector getNormal(Point point) {
        double t = axis.getDirection().dotProduct(point.subtract(axis.getHead()));
        return point.subtract(axis.getPoint(t)).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
