package geometries;
import primitives.*;

/**
 * Geometry interface is the base level of all the geometries'
 */
public interface Geometry {
    /**
     *  getNormal abstract method receives one parameter of type point and returns the normal (vertical)
     *  vector to the body at this point.
     * @param point
     * @return Vector
     */
    public Vector getNormal(Point point);
}
