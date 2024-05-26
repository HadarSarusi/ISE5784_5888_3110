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
        Point p0 = axis.getHead();
        Vector v = axis.getDirection();
        double t = v.dotProduct(point.subtract(p0));
        // calculate a point on the axis that is located against the point
        Point o = isZero(t) ? p0 : p0.add(v.scale(t));
        return point.subtract(o).normalize();
    }
    public List<Point> findIntersections(Ray ray){return null;}
}
