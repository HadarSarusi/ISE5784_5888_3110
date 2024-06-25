package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

public class SpotLight extends PointLight{
    private Vector direction;


    public SpotLight(Color intensity,Point position,Vector direction)
    {
        super(intensity,position);
        this.direction = direction.normalize();
    }
    public SpotLight setKc(double kC)
    {
        return (SpotLight)super.setKc(kC);
    }
    public SpotLight setKl(double kL)
    {
        return (SpotLight)super.setKc(kL);
    }
    public SpotLight setKq(double kQ)
    {
        return (SpotLight)super.setKc(kQ);
    }
    public Vector getL(Point point){
        return super.getL(point);
    }
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0, this.direction.dotProduct(getL(p))));
    }
}
