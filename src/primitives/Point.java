package primitives;

public class Point {

    protected final Double3 xyz;
    public static final Double3 ZERO = new Double3(0, 0, 0);

    public Point(double x, double y, double z)
    {
        xyz = new Double3(x,y,z);
    }

    Point(Double3 xyz)
    { //c-tor
        this.xyz = xyz;
    }
    @Override
    public boolean equals(Object obj)
    {
       if (this == obj) return true;
       return (obj instanceof Point )

               && xyz.equals(point.xyz); //delegation
    }
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if ((obj instanceof Point)) return false; // Check if obj is an instance of Point
//        Point point = (Point) obj; // Cast obj to Point
//        return xyz.equals(point.xyz); // Compare the xyz fields of both points
//    }

    @Override
   public String toString()
    {
       return ""+xyz;
    }

    public Point add(Point point)
    {
        return new Point(this.xyz.add(point.xyz));
    }

    public Vector subtract(Point point)
    {
        point = new Point(this.xyz.subtract(point.xyz));
        if(point.xyz.equals(ZERO))
            throw new IllegalArgumentException("vector can't be zero");
        return new Vector(point.xyz);
    }
    public Point product(Point point)
    {
        return new Point(this.xyz.product(point.xyz));
    }
    public double distanceSquared(Point point)
    {
        Point sumResult=this.subtract(point);
        sumResult=sumResult.product(sumResult);
        return sumResult.xyz.d1+sumResult.xyz.d2+sumResult.xyz.d3;
    }
    public double distance(Point point)
    {
        return Math.sqrt(this.distanceSquared(point));
    }

}
