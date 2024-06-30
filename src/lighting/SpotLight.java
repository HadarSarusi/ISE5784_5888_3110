package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The SpotLight class represents a spotlight with a specific direction and narrow beam effect.
 * It extends the PointLight class and adds a direction vector and a narrow beam factor to control the spread of the light.
 */
public class SpotLight extends PointLight {

    /**
     * The direction of the light.
     */
    private final Vector direction;
    /**
     * The size of the spotlight.
     */
    private double narrowBeam = 1;

    /**
     * Constructs a new SpotLight with the specified intensity, position, and direction.
     *
     * @param intensity the color intensity of the light
     * @param position  the position of the light
     * @param direction the direction of the light beam
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    /**
     * Sets the constant attenuation factor for the light.
     *
     * @param kC the constant attenuation factor
     * @return the SpotLight object itself for method chaining
     */
    public SpotLight setKc(double kC) {
        return (SpotLight) super.setKc(kC);
    }

    /**
     * Sets the linear attenuation factor for the light.
     *
     * @param kL the linear attenuation factor
     * @return the SpotLight object itself for method chaining
     */
    public SpotLight setKl(double kL) {
        return (SpotLight) super.setKl(kL);
    }

    /**
     * Sets the quadratic attenuation factor for the light.
     *
     * @param kQ the quadratic attenuation factor
     * @return the SpotLight object itself for method chaining
     */
    public SpotLight setKq(double kQ) {
        return (SpotLight) super.setKq(kQ);
    }

    /**
     * Sets the narrow beam factor for the spotlight. A higher value results in a narrower beam.
     *
     * @param num the narrow beam factor
     * @return the SpotLight object itself for method chaining
     */
    public SpotLight setNarrowBeam(double num) {
        this.narrowBeam = num;
        return this;
    }

    /**
     * Gets the direction vector from the light to the specified point.
     *
     * @param point the point to calculate the direction vector to
     * @return the direction vector from the light to the point
     */
    public Vector getL(Point point) {
        return super.getL(point);
    }

    /**
     * Gets the intensity of the light at the specified point, taking into account the spotlight's direction and narrow beam factor.
     *
     * @param p the point to calculate the light intensity at
     * @return the color intensity of the light at the point
     */
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.pow(Math.max(0, this.direction.dotProduct(getL(p))), narrowBeam));
    }
}

