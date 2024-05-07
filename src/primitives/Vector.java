package primitives;

import static primitives.Double3.ZERO;

public class Vector extends Point{

    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(xyz.equals(ZERO))
            throw new IllegalArgumentException("vector can't be zero");
    }
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

     public Vector add(Vector vector)
     {
         Vector v=new Vector(this.xyz.add(vector.xyz) );
         if(v.xyz.equals(ZERO))
             throw new IllegalArgumentException("vector can't be zero");
        return v;
     }

     public Vector scale(double scale)
     {
         return this.scale(scale);
     }
     public double dotProduct(Vector vector)
     {
        return (this.xyz.d1*vector.xyz.d1) + (this.xyz.d2*vector.xyz.d2) + (this.xyz.d3*vector.xyz.d3);
     }

     public Vector crossProduct(Vector vector)
     {
         return new Vector((this.xyz.d2*vector.xyz.d3)-(this.xyz.d3*vector.xyz.d2),
                 (this.xyz.d3*vector.xyz.d1)-(this.xyz.d1*vector.xyz.d3),
                 (this.xyz.d1*vector.xyz.d2)-(this.xyz.d2*vector.xyz.d1));
     }

     public double lengthSquared()
     {
         return this.dotProduct(this);
     }

     public double length()
     {
         return Math.sqrt(lengthSquared());
     }
     public Vector normalize()
     {//לשאול את דן
      return  new Vector( this.xyz.reduce(length())) ;
     }

}