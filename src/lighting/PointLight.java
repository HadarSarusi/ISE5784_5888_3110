package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The PointLight class represents a point light source in a 3D scene.
 * It extends the Light class and implements the LightSource interface.
 * Point lights emit light uniformly in all directions from a single point in space.
 */
public class PointLight extends Light implements LightSource {

    /**
     * The position of the point light in 3D space.
     */
    private final Point position;

    /**
     * The constant attenuation factor for the point light.
     */
    private double kC = 1;

    /**
     * The linear attenuation factor for the point light.
     */
    private double kL = 0;

    /**
     * The quadratic attenuation factor for the point light.
     */
    private double kQ = 0;

    /**
     * Constructs a PointLight with the specified intensity and position.
     *
     * @param intensity the color intensity of the light
     * @param position  the position of the light source
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor (kC) for the point light.
     *
     * @param kC the constant attenuation factor
     * @return the PointLight object itself for method chaining
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor (kL) for the point light.
     *
     * @param kL the linear attenuation factor
     * @return the PointLight object itself for method chaining
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor (kQ) for the point light.
     *
     * @param kQ the quadratic attenuation factor
     * @return the PointLight object itself for method chaining
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    /**
     * Calculates the direction vector from the light source to a specified point.
     *
     * @param point the point to calculate the direction to
     * @return the direction vector from the light source to the point
     */
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    /**
     * Calculates the intensity of the light at a specified point.
     * Takes into account the attenuation factors based on the distance from the light source.
     *
     * @param p the point at which to calculate the light intensity
     * @return the color intensity of the light at the point
     */
    public Color getIntensity(Point p) {
        double distanceSquared = p.distanceSquared(position);
        return this.intensity.scale(1 / (kC + Math.sqrt(distanceSquared) * kL + kQ * distanceSquared));
    }
}

