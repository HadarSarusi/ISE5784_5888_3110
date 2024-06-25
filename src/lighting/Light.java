package lighting;

import primitives.Color;

abstract class Light {
    /**
     * The intensity of the light.
     */
    protected Color intensity;
    protected Light(Color intensity)
    {
        this.intensity=intensity;
    }
    /**
     * Returns the intensity of the ambient light.
     *
     * @return the intensity of the ambient light
     */
   public Color getIntensity()
    {
        return this.intensity;
    }

}
