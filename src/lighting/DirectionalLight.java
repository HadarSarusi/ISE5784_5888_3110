package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * DirectionalLight represents a directional light source in a 3D scene.
 * It extends {@code Light} and implements {@code LightSource}, providing methods
 * to retrieve the direction and intensity of the light.
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * The direction vector of the directional light.
     */
    private final Vector direction;

    /**
     * Constructs a directional light with the specified intensity and direction.
     *
     * @param intensity The intensity/color of the directional light.
     * @param direction The direction vector of the directional light.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    /**
     * Gets the direction vector from the light source to a given point (p).
     * For directional lights, this direction is constant and normalized.
     *
     * @param p The point to which the direction vector is calculated (not used for directional lights).
     * @return The normalized direction vector of the light source.
     */
    public Vector getL(Point p) {
        return this.direction;
    }

    /**
     * Gets the intensity (color) of the light at a given point (p).
     * For directional lights, the intensity is constant regardless of the point.
     *
     * @param p The point at which the intensity/color of the light is queried (not used for directional lights).
     * @return The intensity (color) of the directional light.
     */
    public Color getIntensity(Point p) {
        return intensity;
    }

}

