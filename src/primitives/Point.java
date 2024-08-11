package primitives;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;

/**
 * The `Point` class represents a point in 3D space.
 * It provides methods to manipulate and perform operations with points.
 * This class includes methods for point addition, subtraction, distance calculations,
 * and generating points in a grid pattern with random jitter.
 *
 * <pre>
 * Point p1 = new Point(1, 2, 3);
 * Point p2 = new Point(4, 5, 6);
 * Vector v = p2.subtract(p1);
 * Point p3 = p1.add(v);
 * double distance = p1.distance(p2);
 * </pre>
 *
 * @author Lea &amp; Hadar
 */
public class Point {
    /**
     * The field representing the coordinates of the point.
     */
    protected final Double3 xyz;

    /**
     * Represents the zero point (0,0,0).
     */
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructs a `Point` object with the specified coordinates.
     *
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @param z The z-coordinate.
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructs a `Point` object with the specified `Double3` object.
     *
     * @param xyz The `Double3` object representing the coordinates.
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Copy constructor that creates a new `Point` instance with the same coordinates
     * as the specified `Point` instance.
     *
     * @param point The `Point` instance to copy the coordinates from.
     */
    public Point(Point point) {
        this.xyz = point.xyz;
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate.
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate.
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * Returns the z-coordinate of this point.
     *
     * @return the z-coordinate.
     */
    public double getZ() {
        return xyz.d3;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point point) && this.xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return "" + xyz;
    }

    /**
     * Returns a new `Point` whose coordinates are the sum of the
     * coordinates of this point and the specified vector.
     *
     * @param vector The vector to add.
     * @return A new `Point` representing the result of the addition.
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * Returns a new `Vector` whose coordinates are the result of
     * subtracting the coordinates of the specified point from
     * the coordinates of this point.
     *
     * @param point The point to subtract.
     * @return A new `Vector` representing the result of the subtraction.
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Returns the square of the distance between this point and the specified point.
     *
     * @param point The point to calculate the distance to.
     * @return The square of the distance between the points.
     */
    public double distanceSquared(Point point) {
        double dx = this.xyz.d1 - point.xyz.d1;
        double dy = this.xyz.d2 - point.xyz.d2;
        double dz = this.xyz.d3 - point.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * Returns the distance between this point and the specified point.
     *
     * @param point The point to calculate the distance to.
     * @return The distance between the points.
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }


}

