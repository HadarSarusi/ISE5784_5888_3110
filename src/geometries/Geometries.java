package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The {@code Geometries} class represents a collection of geometric objects that can be intersected by rays.
 * This class implements the {@code Intersectable} interface.
 * <p>
 * It supports adding multiple geometric objects and finding intersections of a given ray with these objects.
 * </p>
 */
public class Geometries implements Intersectable {

    /**
     * A list of geometries to be intersected, initialized as an empty {@link LinkedList}.
     */
    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor that initializes an empty list of geometries.
     */
    public Geometries() {
    }

    /**
     * Constructor that initializes the geometries list with the given geometries.
     *
     * @param geometries the geometric objects to add to the list
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds the given geometries to the list of geometries.
     *
     * @param geometries the geometric objects to add
     */
    public void add(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }

    /**
     * Finds the intersections of a given ray with all the geometries in the list.
     * <p>
     * This method iterates through all the geometries and collects all intersection points.
     * If no intersections are found, it returns {@code null}.
     * </p>
     *
     * @param ray the ray to find intersections with
     * @return a list of intersection points, or {@code null} if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (Intersectable geometry : geometries) {
            List<Point> intersections = geometry.findIntersections(ray);
            if (intersections != null) {
                if (result == null)
                    result = new LinkedList<>(intersections);
                else
                    result.addAll(intersections);
            }
        }
        return result;
    }
}

