package geometries;

import primitives.*;

/**
 * RadialGeometry class for representing geometries with a radius.
 *
 * @author Lea &amp; Hadar
 */
public abstract class RadialGeometry extends Geometry {
    /**
     * The radius of the geometry.
     */
    protected final double radius;
    /**
     * The squared radius of the geometry.
     */
    protected final double radiusSquared;

    /**
     * Constructs a `RadialGeometry` object with the specified radius.
     *
     * @param radius The radius of the geometry.
     */
    RadialGeometry(double radius) {
        this.radius = radius;
        this.radiusSquared = radius * radius;
    }

    /**
     * Retrieves the normal vector to the geometry at the specified point.
     *
     * @param point The point on the surface of the geometry.
     * @return The normal vector to the geometry at the given point.
     */
    public abstract Vector getNormal(Point point);
}

