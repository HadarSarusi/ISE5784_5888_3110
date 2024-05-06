package geometries;

import primitives.*;

public class Plane implements Geometry{
    private final Point point;
    private final Vector normal;

    Plane(Point point1, Point point2, Point point3)
    {
        this.point = point1;
        this.normal = null;
    }

    Plane(Point point, Vector normal)
    {
        this.point = point;
        this.normal = normal.normalize();
    }
    public Vector getNormal(Point point){return normal;}
    public Vector getNormal(){return normal;}

}
