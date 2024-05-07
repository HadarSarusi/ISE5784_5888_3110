package primitives;
/**
 * Ray class representing a ray.
 */
public class Ray {

    final Point head;
    final Vector direction;

    /**
     * Ray c-tor receiving a Point and a Vector.
     *
     * @param head  Point value
     * @param direction Vector value
     */
    public Ray(Point head, Vector direction)
    {
        this.head = head;
        this.direction=direction.normalize();

    }

}
