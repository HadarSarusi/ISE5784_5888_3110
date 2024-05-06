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
    //    @Override
//    public boolean equals(Object obj)
//    {
//          return super.equals(obj);
//    }
    @Override
    public boolean equals(Object obj)
    {
        if(this==obj) return true;
        if(obj instanceof Vector vector)
            return super.equals(vector);
        return false;
    }
     @Override
    public String toString()
     {
         return "->"+super.toString();
     }
     public Vector add(Vector vector)
     {
         if( this.add(vector).equals(ZERO))
             throw new IllegalArgumentException("vector can't be zero");
         return this.add(vector);
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