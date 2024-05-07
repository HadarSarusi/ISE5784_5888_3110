package primitives;

public class Point {

    protected final Double3 xyz;
    public static final Point ZERO1 = new Point(0, 0, 0);

    public Point(double x, double y, double z)
    {
        xyz = new Double3(x,y,z);
    }

    Point(Double3 xyz)
    { //c-tor
        this.xyz = xyz;
    }


//    public boolean equals(Object obj)
//    {
//       if (this == obj) return true;
//       return obj instanceof Point other  && xyz.equals(other.xyz);
//    }
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

    public Point add(Vector vector)
    {
        return new Point(xyz.add(vector.xyz));
    }

    public Vector subtract(Point point)
    {
        return new Vector(xyz.subtract(point.xyz));
    }
    public Point product(Point point)
    {
        return new Point(this.xyz.product(point.xyz));
    }
    public double distanceSquared(Point point)
    {

//        Point sumResult=this.subtract(point);
//        sumResult=sumResult.product(sumResult);
//        return sumResult.xyz.d1+sumResult.xyz.d2+sumResult.xyz.d3;
        double distanceX = (this.xyz.d1 - point.xyz.d1);
        double distanceY = (this.xyz.d2 - point.xyz.d2);
        double distanceZ = (this.xyz.d3 - point.xyz.d3);

        double distance = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        return distance;


    }
    public double distance(Point point)
    {
        return Math.sqrt(this.distanceSquared(point));
    }

}
