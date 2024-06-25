package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

  private Point position;
  private double kC=1,kL=0,kQ=0;

  public PointLight(Color intensity,Point position )
  {
      super(intensity);
      this.position=position;
  }
    public PointLight setKc(double kC)
    {
        this.kC=kC;
        return this;
    }
    public PointLight setKl(double kL)
    {
        this.kL=kL;
        return this;
    }
    public PointLight setKq(double kQ)
    {
        this.kQ=kQ;
        return this;
    }
    public Vector getL(Point point){
         return point.subtract(position).normalize();
    }
    public Color getIntensity(Point p)
    {
       return getIntensity().scale(1/(kC+p.distance(position)*kL+kQ*p.distanceSquared(position)));
    }
}
