package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry{

    protected final double radius;
    public RadialGeometry(double radius)
    {
        this.radius = radius;
    }
    public abstract Vector getNormal(Point point);

}
