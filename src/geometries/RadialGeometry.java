package geometries;

import primitives.*;

/**
 * RadialGeometry- abstract class for representing geometries with a radius
 *
 * @author Lea &amp; Hadar
 */
public abstract class RadialGeometry implements Geometry {
    protected final double radius;

    /**
     * RadialGeometry c-tor receiving double value
     *
     * @param radius double value
     */
    RadialGeometry(double radius) {
        this.radius = radius;
    }

    public abstract Vector getNormal(Point point);
}
