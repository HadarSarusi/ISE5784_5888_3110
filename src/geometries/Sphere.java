package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * This class extends the `RadialGeometry` class and represents a sphere with a given center and radius.
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
}

