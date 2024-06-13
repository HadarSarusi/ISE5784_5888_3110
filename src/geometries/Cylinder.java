package geometries;

import primitives.*;


import java.util.List;

import static primitives.Util.alignZero;

/**
 * Cylinder class representing a three-dimensional cylinder in 3D Cartesian coordinate system.
 *
 * @author lea &amp; hadar
 */
public class Cylinder extends Tube {

    /**
     * The height of the cylinder.
     */
    private final double height;

    /**
     * Constructs a cylinder with the specified height, axis, and radius.
     *
     * @param height The height of the cylinder.
     * @param axis   The axis of the cylinder (a ray representing the direction and position of the cylinder's central line).
     * @param radius The radius of the cylinder (the distance from the axis to any point on the circular base).
     */
    Cylinder(double height, Ray axis, double radius) {
        super(axis, radius);
        this.height = height;
    }

    /**
     * Retrieves the normal vector to the cylinder at a given point on its surface.
     *
     * @param point The point on the surface of the cylinder.
     * @return The normal vector to the cylinder at the given point.
     */
    public Vector getNormal(Point point) {

        if (super.axis.getHead().equals(point)) {
            return super.axis.getDirection().normalize();
        }
        //Calculate the vector from the point P0 to the point we got
        Vector v = point.subtract(axis.getHead());//There may be an exception here that the vector is zero
        //We will do a scalar multiplication between the vector of the line and the vector we got.
        // And because the vector of the line is normalized, the result will be the projection of
        // our vector on the line
        double d = alignZero(v.dotProduct(axis.getDirection()));
        if (d <= 0) {
            return super.axis.getDirection().normalize();//.scale(-1)
        }

        if (alignZero(height - d) > 0) {
            return super.getNormal(point);
        }
        if (d >= height) {
            return super.axis.getDirection().normalize();
        }
        return null;//impossible case
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}

