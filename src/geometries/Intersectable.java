package geometries;

import primitives.*;

import java.util.List;

/**
 * The {@code Intersectable} interface represents geometric objects that can be intersected by rays.
 * <p>
 * Implementations of this interface should define the specific intersection logic for the geometric shape.
 * </p>
 */
public interface Intersectable {

    /**
     * Finds the intersection points of a given ray with the geometric object.
     *
     * @param ray the ray to intersect with the geometric object
     * @return a list of intersection points with the geometric object.
     * If there are no intersections, null is returned.
     */
    public List<Point> findIntersections(Ray ray);
}

