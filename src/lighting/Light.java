package lighting;

import primitives.Color;

/**
 * The Light abstract class represents a light source in a 3D scene.
 * It defines the intensity of the light and provides a method to retrieve it.
 */
abstract class Light {
    /**
     * The intensity of the light.
     */
    protected final Color intensity;

    /**
     * Constructs a light with the specified intensity.
     *
     * @param intensity The intensity (color) of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Returns the intensity of the ambient light.
     *
     * @return the intensity of the ambient light
     */
    public Color getIntensity() {
        return this.intensity;
    }

}
