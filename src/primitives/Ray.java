package primitives;

import java.util.*;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

/**
 * Ray class representing a ray.
 * This class represents a ray in 3D space, consisting of a starting point (head) and a direction vector.
 * <p>
 * The ray can be used to calculate points along its path and find the closest point from a list of points.
 *
 * @author Lea &amp; Hadar
 */
public class Ray {


    /**
     * A small delta value used for epsilon that moves to the direction to the normal.
     */
    private static final double DELTA = 0.1;
    /**
     * The starting point (head) of the ray.
     */
    private final Point head;
    /**
     * The direction vector of the ray.
     */
    private final Vector direction;

    /**
     * Constructs a ray with the specified head point and direction vector.
     *
     * @param head      The starting point of the ray.
     * @param direction The direction vector of the ray.
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Constructs a new Ray with the specified origin point, direction vector, and normal vector.
     * Adjusts the origin point by a small delta value to account for numerical precision.
     *
     * @param head The origin point of the ray.
     * @param direction The direction vector of the ray.
     * @param normal The normal vector used to adjust the origin point for numerical precision.
     */
    public Ray(Point head, Vector direction, Vector normal) {
        Vector delta = normal.scale(normal.dotProduct(direction) >= 0 ? DELTA : -DELTA);
        this.head = head.add(delta);
        this.direction = direction;
    }

    /**
     * Checks if this ray is equal to another object.
     * Rays are considered equal if they have the same head and direction.
     *
     * @param obj The object to compare with.
     * @return true if the rays are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Ray other && this.head.equals(other.head) &&
                this.direction.equals(other.direction);
    }

    /**
     * Returns a string representation of the ray.
     * The format is "head->direction".
     *
     * @return A string representation of the ray.
     */
    @Override
    public String toString() {
        return head + "->" + direction;
    }

    /**
     * Gets the head (starting point) of this ray.
     *
     * @return The head point of the ray.
     */
    public Point getHead() {
        return head;
    }

    /**
     * Gets the direction vector of this ray.
     *
     * @return The direction vector of the ray.
     */
    public Vector getDirection() {
        return direction;
    }

    /**
     * Returns the point on the ray at the specified parameter value.
     * If the parameter t is zero, the head point is returned.
     *
     * @param t The parameter value along the ray.
     * @return The point on the ray at the parameter t.
     */
    public Point getPoint(double t) {
        return isZero(t) ? head : head.add(direction.scale(t));
    }

    /**
     * Finds and returns the closest point from a list of points to the head of this ray.
     *
     * @param points A list of points to search from.
     * @return The closest point to the head of this ray, or {@code null} if the list is empty.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * Finds and returns the closest geometric point from a list of geometric points to the head of this ray.
     *
     * @param intersections A list of geometric points to search from.
     * @return The closest geometric point to the head of this ray, or {@code null} if the list is empty.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> intersections) {

        if (intersections == null || intersections.isEmpty())
            return null;

        double minDistanceSquare = Double.POSITIVE_INFINITY;
        GeoPoint minPoint = null;
        for (GeoPoint geoPoint : intersections) {
            double distanceSquare = geoPoint.point.distanceSquared(this.head);
            if (distanceSquare < minDistanceSquare) {
                minDistanceSquare = distanceSquare;
                minPoint = geoPoint;
            }
        }
        return minPoint;
    }

}


