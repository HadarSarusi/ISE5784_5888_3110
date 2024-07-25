package primitives;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Vector.generateVector;

/**
 * The `Point` class represents a point in 3D space.
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
     * Copy constructor that creates a new Point instance with the same coordinates
     * as the specified Point instance.
     *
     * @param point The Point instance to copy the coordinates from.
     */
    public Point(Point point) {

        this.xyz = point.xyz;
    }


    /**
     * @return the x value
     */
    public double getX() {
        return xyz.d1;
    }

    /**
     * @return the y value
     */
    public double getY() {
        return xyz.d2;
    }

    /**
     * @return the z value
     */
    public double getZ() {
        return xyz.d3;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point point) &&
                this.xyz.equals(point.xyz);
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
    /**
     * @param vX the x vector of the plane
     * @param vY the y vector of the plane
     * @param amount the amount of point to generate
     * @param center the 'center' of the generation
     * @param size the size of the circle of the generation
     * @return a list of point generated using Jittered Pattern inside a circle around center
     */
    public static List<Point> generatePoints(Vector vX, Vector vY, int amount, Point center, double size) {
        List<Point> points = new LinkedList<>();

//        amount = (int) (1.273 * amount);

        double divider = Math.sqrt(amount);
        double r = size / divider;

//        double size2 = size * size;

        for (int k = 0; k < divider; k++) {
            for (int l = 0; l < divider; l++) {
                double yI = alignZero( -(k - (divider - 1) / 2) * r);
                double xJ = alignZero( -(l - (divider - 1) / 2) * r);

                Point pIJ = center;

                if (xJ != 0) pIJ = pIJ.add(vX.scale(xJ));
                if (yI != 0) pIJ = pIJ.add(vY.scale(yI));

                // adding some random jitter
                pIJ = pIJ.add(generateVector(vX, vY, r));

//                if(alignZero(pIJ.distanceSquared(center)) < size2)
                points.add(pIJ);
            }
        }

        return points;
    }
}

