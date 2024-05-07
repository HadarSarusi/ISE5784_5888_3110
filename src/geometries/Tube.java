package geometries;
import primitives.*;

/**
 * Tube class representing three-dimensional tube in 3D Cartesian coordinate
 * system
 */
public class Tube extends RadialGeometry {

    protected final Ray axis;
    /**
     * sphere c-tor receiving Ray value and double value.
     *
     * @param axis  Ray value
     * @param radius  double value
     *
     */
    Tube(Ray axis,double radius)
    {
        super(radius);
        this.axis = axis;
    }
    public Vector getNormal(Point point){return null;}

}
