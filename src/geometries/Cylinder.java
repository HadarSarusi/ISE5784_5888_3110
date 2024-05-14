package geometries;
import primitives.*;

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
        return null;
    }
}

