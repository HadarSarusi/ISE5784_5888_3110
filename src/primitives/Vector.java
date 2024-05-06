package primitives;

import static primitives.Double3.ZERO;

public class Vector extends Point{

    public Vector(double x, double y, double z)
    {
        super(x,y,z);
        if(xyz.equals(ZERO))
            throw new IllegalArgumentException("vector can't be zero");
    }
    Vector(Double3 xyz) //c-tor
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
         return this.add(vector);
     }
     //לשאול את דן למה לא להוסיף גם מתודת חיסור
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

     public double lenghtSquared()
     {
         return this.dotProduct(this);
     }

     public double lenght()
     {
         return Math.sqrt(lenghtSquared());
     }
     public Vector normalize()
     {//לשאול את דן
      return  new Vector( this.xyz.reduce(lenght())) ;
     }

}