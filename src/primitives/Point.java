package primitives;

public class Point {

    protected final Double3 xyz;

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
       return (obj instanceof Point point)
               && xyz.equals(point.xyz); //delegation
    }

    @Override
   public String toString()
    {
       return ""+xyz;
    }

    public Point add(Point point)
    {
        return new Point(this.xyz.add(point.xyz));
    }

    public Point subtract(Point point)
    {
        return new Point(this.xyz.subtract(point.xyz));
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
