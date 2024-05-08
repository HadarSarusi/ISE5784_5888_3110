package primitives;

import static primitives.Double3.ZERO;
/**
 * Vector class representing a vector.
 */
public class Vector extends Point{

    /**
     * Vector c-tor receiving 3 double values
     *
     * @param x double value
     * @param y double value
     * @param z double value
     */
    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(xyz.equals(ZERO))
            throw new IllegalArgumentException("vector can't be zero");
    }
    /**
     * Vector c-tor receiving Double3 value.
     *
     * @param xyz Point value
     */
    public Vector(Double3 xyz) //c-tor
    {
        super(xyz);
        if(xyz.equals(ZERO))
            throw new IllegalArgumentException("vector can't be zero");
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector)obj;
        return this.xyz.equals(other.xyz);
    }

     @Override
    public String toString()
     {
         return "->"+super.toString();
     }
    /**
     * add method return vector witch is the sum of two vectors
     *
     * @param vector Vector value
     * @return Vector value
     */
     public Vector add(Vector vector)
     {
         return new Vector(this.xyz.add(vector.xyz) );
     }
    /**
     * scale method return multiple vector by a receiving scalar
     *
     * @param scale double value
     * @return Vector value
     */
     public Vector scale(double scale)
     {
         return this.scale(scale);
     }
    /**
     * dotProduct return sum of multiple between the coordinates of the two vectors
     * *
     * @param vector Vector value
     * @return double value
     */
     public double dotProduct(Vector vector)
     {
        return (this.xyz.d1*vector.xyz.d1) + (this.xyz.d2*vector.xyz.d2) + (this.xyz.d3*vector.xyz.d3);
     }
    /**
     * crossProduct method calculate cartesian product and return the new Vector
     *
     * @param vector Vector value
     * @return Vector value
     */
     public Vector crossProduct(Vector vector)
     {
         return new Vector((this.xyz.d2*vector.xyz.d3)-(this.xyz.d3*vector.xyz.d2),
                 (this.xyz.d3*vector.xyz.d1)-(this.xyz.d1*vector.xyz.d3),
                 (this.xyz.d1*vector.xyz.d2)-(this.xyz.d2*vector.xyz.d1));
     }
    /**
     * lengthSquared method calculate the length of vector ->power 2
     *
     * @return double value
     */
     public double lengthSquared()
     {
         return this.dotProduct(this);
     }

    /**
     * length method calculate the length of vector
     *
     * @return double value
     */
     public double length()
     {
         return Math.sqrt(lengthSquared());
     }
    /**
     * normalize method normalize the vector
     *
     * @return Vector value
     */
     public Vector normalize()
     {
      return  new Vector( this.xyz.reduce(length())) ;
     }

}