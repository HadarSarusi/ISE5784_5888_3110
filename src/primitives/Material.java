package primitives;

/**
 * The Material class represents the material properties of an object in a 3D scene.
 * It defines the diffuse reflection coefficient (kD), specular reflection coefficient (kS),
 * and the shininess exponent (nShininess) for determining the appearance of the material.
 */
public class Material {

    /**
     * The diffuse reflection coefficient of the material.
     */
    public Double3 kD = Double3.ZERO;

    /**
     * The specular reflection coefficient of the material.
     */
    public Double3 kS = Double3.ZERO;

    /**
     * The shininess exponent of the material, affecting the size and intensity of specular highlights.
     */
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient (kD) of the material.
     *
     * @param kd the diffuse reflection coefficient to set
     * @return the Material object itself for method chaining
     */
    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient (kD) of the material.
     *
     * @param kd the diffuse reflection coefficient to set as a Double3 object
     * @return the Material object itself for method chaining
     */
    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }

    /**
     * Sets the specular reflection coefficient (kS) of the material.
     *
     * @param ks the specular reflection coefficient to set
     * @return the Material object itself for method chaining
     */
    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    /**
     * Sets the specular reflection coefficient (kS) of the material.
     *
     * @param ks the specular reflection coefficient to set as a Double3 object
     * @return the Material object itself for method chaining
     */
    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }

    /**
     * Sets the shininess exponent (nShininess) of the material.
     *
     * @param nShininess the shininess exponent to set
     * @return the Material object itself for method chaining
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}

