package primitives;

/**
 * class Point representing a point in 3 domination.
 */
public class Point {
    /** The field of the coordinate values*/
    protected final Double3 xyz;
    /** Zero point (0,0,0) */
    public static final Point ZERO1 = new Point(0, 0, 0);


    /**
     * Point c-tor receiving 3 double values:
     *
     * @param x double value
     * @param y double value
     * @param z double value
     */
    public Point(double x, double y, double z)
    {
        xyz = new Double3(x,y,z);
    }
    /**
     * Point c-tor receiving Double3 value:
     *
     * @param xyz Double3 value
     */
    Point(Double3 xyz)
    { //c-tor
        this.xyz = xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return this.xyz.equals(other.xyz);
    }

    @Override
   public String toString()
    {
       return ""+xyz;
    }
    /**
     * add method return new Poind witch his coordinates is the sum of the
     * receiving vector's coordinates and 'this' point's coordinates.
     *
     * @param vector Vector value
     * @return Point value
     */
    public Point add(Vector vector)
    {
        return new Point(xyz.add(vector.xyz));
    }
    /**
     * subtract method return new Vector witch his coordinates is the subtract of the
     * receiving point's coordinates from 'this' point's coordinates.
     *
     * @param point Point value
     * @return Vector value
     */
    public Vector subtract(Point point)
    {
        return new Vector(xyz.subtract(point.xyz));
    }
    /**
     * product method return a point that is a product of the
     * 'this' point and the point received as a parameter
     *
     * @param point Point value
     * @return Point value
     */
    public Point product(Point point)
    {
        return new Point(this.xyz.product(point.xyz));
    }
    /**
     * distanceSquared method return the distance between 2 points-> power 2.
     *
     * @param point Point value
     * @return double value
     */
    public double distanceSquared(Point point)
    {
        double distanceX = (this.xyz.d1 - point.xyz.d1);
        double distanceY = (this.xyz.d2 - point.xyz.d2);
        double distanceZ = (this.xyz.d3 - point.xyz.d3);
        double distance = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        return distance;
    }
    /**
     * distance method return the distance between 2 points.
     *
     * @param point Point value
     * @return double value
     */
    public double distance(Point point)
    {
        return Math.sqrt(this.distanceSquared(point));
    }

}
