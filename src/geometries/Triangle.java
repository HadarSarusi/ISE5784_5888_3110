package geometries;
import primitives.Point;

/**
 * Triangle class representing a two-dimensional Triangle in 3D Cartesian coordinate
 * system
 */
public class Triangle extends Polygon{
    /**
     * Triangle c-tor receiving 3 Point values.
     *
     * @param point1  Point value
     * @param point2  Point value
     * @param point3  Point value
     */
    Triangle(Point point1, Point point2, Point point3)
    {
        super(point1, point2, point3);
    }
}
