package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class representing three-dimensional sphere in 3D Cartesian coordinate
 * system
 * @author Lea &amp; Hadar
 */
public class Sphere extends RadialGeometry {
    private final Point center;

    /**
     * sphere c-tor receiving Point values and double value
     *
     * @param center Point value
     * @param radius Vector value
     */
    Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
