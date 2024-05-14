package primitives;

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
}

