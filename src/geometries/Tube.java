package geometries;


import primitives.*;

public class Tube extends RadialGeometry {
    protected final Ray axis;
    Tube(Ray axis,double radius)
    {
        super(radius);
        this.axis = axis;
    }
    public Vector getNormal(Point point){return null;}

}
