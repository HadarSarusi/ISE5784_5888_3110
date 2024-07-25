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
     * Attenuation coefficient of transparency
     */
    public Double3 kT=Double3.ZERO;
    /**
     *reflection attenuation coefficient
     */
    public Double3 kR=Double3.ZERO;

    /**
     * the number of reflected rays, set to 1 to turn off Glossy Surfaces
     */
    public int numRaysReflected = 1;
    /**
     * the angle of the cone which we generate rays the bigger the cone angle the more the rays are scattered
     */
    public double coneAngleReflected = 0.0;
    /**
     * the number of reflected rays, set to 1 to turn off Diffused Glass
     */
    public int numRaysRefracted = 1;
    /**
     * the angle of the cone which we generate rays the bigger the cone angle the more the rays are scattered
     */
    public double coneAngleRefracted = 0.0;
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
    /**
     * Sets the attenuation coefficient of transparency.
     *
     * @param kT the attenuation coefficient of transparency
     * @return the current instance of the Material class
     */
    public Material setKt(Double3 kT)
    {
        this.kT=kT;
        return this;
    }
    /**
     * Sets the reflection attenuation coefficient.
     *
     * @param kR the reflection attenuation coefficient
     * @return the current instance of the Material class
     */
    public Material setKr(Double3 kR)
    {
        this.kR=kR;
        return this;
    }

    /**
     * Sets the attenuation coefficient of transparency.
     *
     * @param kT the attenuation coefficient of transparency
     * @return the current instance of the Material class
     */
    public Material setKt(double kT)
    {
        this.kT=new Double3(kT);
        return this;
    }
    /**
     * Sets the reflection attenuation coefficient.
     *
     * @param kR the reflection attenuation coefficient
     * @return the current instance of the Material class
     */
    public Material setKr(double kR)
    {
        this.kR=new Double3(kR);
        return this;
    }

    /**
     * @param amount the amount of rays for Glossy Surfaces
     * @return this According to the Builder Pattern
     */
    public Material setNumRaysReflected(int amount) {
        this.numRaysReflected = amount;
        return this;
    }

    /**
     * @param angle the angle of the cone in degree
     * @return this According to the Builder Pattern
     */
    public Material setConeAngleReflected(double angle) {
        this.coneAngleReflected = Math.toRadians(angle);
        return this;
    }
    /**
     * @param amount the amount of rays for Diffused Glass
     * @return this According to the Builder Pattern
     */
    public Material setNumRaysRefracted(int amount) {
        this.numRaysRefracted = amount;
        return this;
    }
    /**
     * @param angle the angle of the cone in degree
     * @return this According to the Builder Pattern
     */
    public Material setConeAngleRefracted(double angle) {
        this.coneAngleRefracted = Math.toRadians(angle);
        return this;
    }
    }

