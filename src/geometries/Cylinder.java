package geometries;

import primitives.*;

/**
 * Cylinder class representing a three-dimensional cylinder in 3D Cartesian coordinate
 * system
 */
public class Cylinder extends Tube {

    private final double height;
    /**
     * cylinder c-tor receiving a height, ray and a radius.
     *
     * @param height  double value
     * @param axis Ray value
     * @param radius double value
     */
    Cylinder(double height, Ray axis, double radius)
    {
        super(axis, radius);
        this.height = height;
    }

    public Vector getNormal(Point point){return null;}
}
