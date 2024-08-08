package lighting;

import primitives.*;

/**
 * The LightSource interface represents a light source in a 3D scene.
 * It defines methods for retrieving the intensity of the light at a given point,
 * and for calculating the direction vector from the light source to a given point.
 */
public interface LightSource {

    /**
     * Returns the intensity of the light at a specific point in the scene.
     *
     * @param p the point in 3D space
     * @return the intensity of the light at the given point
     */
    public Color getIntensity(Point p);

    /**
     * Calculates the direction vector from the light source to a specific point in the scene.
     *
     * @param p the point in 3D space
     * @return the direction vector from the light source to the given point
     */
    public Vector getL(Point p);

    /**
     * Calculates the distance from this point to the specified point.
     *
     * @param point The point to which the distance is to be calculated.
     * @return The distance between this point and the specified point.
     */
    double getDistance(Point point);
}

