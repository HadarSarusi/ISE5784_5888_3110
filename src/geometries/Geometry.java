package geometries;
import primitives.*;

/**
 * Geometry interface is the base level of all the geometries.
 * This interface defines a method to retrieve the normal vector to a geometry at a given point.
 *
 * @author Lea &amp; Hadar
 */
public interface Geometry {
    /**
     * Calculates the normal vector to the geometry at the specified point.
     *
     * @param point The point on the geometry's surface.
     * @return The normal vector to the geometry at the given point.
     */
    public Vector getNormal(Point point);
}
