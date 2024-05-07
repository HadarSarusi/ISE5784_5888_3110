package geometries;
import primitives.*;

/**
 * Plane class representing a two-dimensional plane in 3D Cartesian coordinate
 * system
 */
public class Plane implements Geometry{

    private final Point point;
    private final Vector normal;

    /**
     * plane c-tor receiving 3 Point3D values
     *
     * @param point1 Point value
     * @param point2 Point value
     * @param point3 Point value
     */
    Plane(Point point1, Point point2, Point point3)
    {
        this.point = point1;
        this.normal = null;
    }
    /**
     * plane c-tor receiving Point3D values and Vector value
     *
     * @param point  Point value
     * @param normal Vector value
     */
    Plane(Point point, Vector normal)
    {
        this.point = point;
        this.normal = normal.normalize();
    }
    public Vector getNormal(Point point){return normal;}
    public Vector getNormal(){return normal;}

}
