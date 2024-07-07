package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The {@code AmbientLight} class represents the ambient light in a scene,
 * providing a base level of illumination to all objects uniformly.
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * Constructs an {@code AmbientLight} with the specified intensity and attenuation factor.
     *
     * @param iA the base color of the ambient light
     * @param kA the attenuation factor as a {@code Double3}
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Constructs an {@code AmbientLight} with the specified intensity and attenuation factor.
     *
     * @param iA the base color of the ambient light
     * @param kA the attenuation factor as a {@code Double}
     */
    public AmbientLight(Color iA, Double kA) {
        super(iA.scale(kA));
    }
}

