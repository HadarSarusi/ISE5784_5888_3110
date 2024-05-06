package geometries;

import primitives.*;

public abstract class RadialGeometry implements Geometry {
    protected final double radius;
    RadialGeometry(double radius)
    {
        this.radius = radius;
    }

    public abstract Vector getNormal(Point point);
}
