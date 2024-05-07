package geometries;
//1
import primitives.*;


public class Cylinder extends Tube {
    private final double height;
    Cylinder(double height, Ray axis, double radius)
    {
        super(axis, radius);
        this.height = height;
    }
    public Vector getNormal(Point point){return null;}
}
