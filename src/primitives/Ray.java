package primitives;

/**
 * Ray class representing a ray.
 *
 * @author Lea &amp; Hadar
 */
public class Ray {

    final private Point head;
    final private Vector direction;

    /**
     * Ray c-tor receiving a Point and a Vector.
     *
     * @param head      Point value
     * @param direction Vector value
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Ray other && this.head.equals(other.head)&&
                this.direction.equals(other.direction);
    }
    @Override
    public String toString() {
        return "->" + super.toString();
    }

    public Point getHead(){
        return head;
    }

    public Vector getDirection(){
        return direction;
    }
}
