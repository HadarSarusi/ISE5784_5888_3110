package primitives;

public class Ray {
    final Point head;
    final Vector direction;
    public Ray(Point head, Vector direction)
    {
        this.head = head;
        this.direction=direction.normalize();

    }

}
