package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {

    private final Vector direction;
    private double narrowBeam = 1;


    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    public SpotLight setKc(double kC) {
        return (SpotLight) super.setKc(kC);
    }

    public SpotLight setKl(double kL) {
        return (SpotLight) super.setKl(kL);
    }

    public SpotLight setKq(double kQ) {
        return (SpotLight) super.setKq(kQ);
    }

    public SpotLight setNarrowBeam(double num) {
        this.narrowBeam = num;
        return this;
    }

    public Vector getL(Point point) {
        return super.getL(point);
    }

    public Color getIntensity(Point p) {
        double projection = this.direction.dotProduct(getL(p));
        double factor = Math.max(0, projection);
        factor = Math.pow(factor, narrowBeam);
        return super.getIntensity(p).scale(factor);

    }
}
