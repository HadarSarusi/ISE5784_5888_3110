package primitives;

/**
 * class Point representing a point in 3 domination.
 *
 * @author Lea &amp; Hadar
 */
public class Point {
    /**
     * The field of the coordinate values
     */
    protected final Double3 xyz;
    /**
     * Zero point (0,0,0)
     */
    public static final Point ZERO = new Point(0, 0, 0);


    /**
     * Point c-tor receiving 3 double values:
     *
     * @param x double value
     * @param y double value
     * @param z double value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Point c-tor receiving Double3 value:
     *
     * @param xyz Double3 value
     */
    public Point(Double3 xyz) { //c-tor
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
     * add method return new Poind witch his coordinates is the sum of the
     * receiving vector's coordinates and 'this' point's coordinates.
     *
     * @param vector Vector value
     * @return Point value
     */
    public Point add(Vector vector) {
        return new Point(xyz.add(vector.xyz));
    }

    /**
     * subtract method return new Vector witch his coordinates is the substract of the
     * receiving point's coordinates from 'this' point's coordinates.
     *
     * @param point Point value
     * @return Vector value
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * distanceSquared method return the distance between 2 points-> power 2.
     *
     * @param point Point value
     * @return double value
     */
    public double distanceSquared(Point point) {
        double dx = this.xyz.d1 - point.xyz.d1;
        double dy = this.xyz.d2 - point.xyz.d2;
        double dz = this.xyz.d3 - point.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * distance method return the distance between 2 points.
     *
     * @param point Point value
     * @return double value
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }

}
