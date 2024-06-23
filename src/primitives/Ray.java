package primitives;

import java.util.*;

import static primitives.Util.isZero;

/**
 * Ray class representing a ray.
 * This class represents a ray in 3D space, consisting of a starting point (head) and a direction vector.
 *
 * @author Lea &amp; Hadar
 */
public class Ray {

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Ray other && this.head.equals(other.head) &&
                this.direction.equals(other.direction);
    }

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
     * The parameter value along the ray. If t is zero, the head point is returned.
     *
     * @param t The parameter value along the ray. If t is zero, the head point is returned.
     * @return The point on the ray at the parameter t.
     */
    public Point getPoint(double t) {
        return isZero(t) ? head : head.add(direction.scale(t));
    }

    /**
     * Finds and returns the closest point from a list of points to this point.
     *
     * @param points a list of points to search from
     * @return the closest point to this point, or {@code null} if the list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        if (points.isEmpty()) {
            return null;
        }
        double tempDistanceSquare = points.get(0).distanceSquared(this.head);
        double distanceSquare;
        Point minPoint = points.get(0);
        for (Point point : points) {
            distanceSquare = point.distanceSquared(this.head);
            if (distanceSquare < tempDistanceSquare) {
                tempDistanceSquare = distanceSquare;
                minPoint = point;
            }
        }
        return minPoint;
    }

}

