package primitives;

/**
 * The {@code Material} class represents the material properties of an object in a 3D scene.
 * It defines various attributes that affect the appearance of the material, such as reflection, specular highlights,
 * and transparency. The class supports configuring material properties using the builder pattern for convenience.
 */
public class Material {

    /**
     * The diffuse reflection coefficient of the material, determining how much light is scattered when it hits the surface.
     */
    public Double3 kD = Double3.ZERO;

    /**
     * The specular reflection coefficient of the material, influencing the size and intensity of specular highlights.
     */
    public Double3 kS = Double3.ZERO;

    /**
     * The shininess exponent of the material, affecting the sharpness of specular highlights.
     */
    public int nShininess = 0;

    /**
     * The attenuation coefficient of transparency, determining how much light is transmitted through the material.
     */
    public Double3 kT = Double3.ZERO;

    /**
     * The reflection attenuation coefficient, affecting how much light is reflected off the surface.
     */
    public Double3 kR = Double3.ZERO;

    /**
     * The number of rays used for simulating reflection. Set to 1 to disable glossy surfaces.
     */
    public int numRaysReflected = 1;

    /**
     * The angle of the cone used for generating reflected rays. A larger angle scatters rays more.
     */
    public double coneAngleReflected = 0.0;

    /**
     * The number of rays used for simulating refraction. Set to 1 to disable diffused glass effects.
     */
    public int numRaysRefracted = 1;

    /**
     * The angle of the cone used for generating refracted rays. A larger angle scatters rays more.
     */
    public double coneAngleRefracted = 0.0;

    /**
     * Sets the diffuse reflection coefficient ({@code kD}) of the material.
     *
     * @param kd the diffuse reflection coefficient to set
     * @return the {@code Material} object itself for method chaining
     */
    public Material setKd(double kd) {
        this.kD = new Double3(kd);
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient ({@code kD}) of the material using a {@code Double3} object.
     *
     * @param kd the diffuse reflection coefficient to set as a {@code Double3} object
     * @return the {@code Material} object itself for method chaining
     */
    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }

    /**
     * Sets the specular reflection coefficient ({@code kS}) of the material.
     *
     * @param ks the specular reflection coefficient to set
     * @return the {@code Material} object itself for method chaining
     */
    public Material setKs(double ks) {
        this.kS = new Double3(ks);
        return this;
    }

    /**
     * Sets the specular reflection coefficient ({@code kS}) of the material using a {@code Double3} object.
     *
     * @param ks the specular reflection coefficient to set as a {@code Double3} object
     * @return the {@code Material} object itself for method chaining
     */
    public Material setKs(Double3 ks) {
        this.kS = ks;
        return this;
    }

    /**
     * Sets the shininess exponent ({@code nShininess}) of the material.
     *
     * @param nShininess the shininess exponent to set
     * @return the {@code Material} object itself for method chaining
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Sets the attenuation coefficient of transparency ({@code kT}) of the material using a {@code Double3} object.
     *
     * @param kT the attenuation coefficient of transparency to set
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Sets the reflection attenuation coefficient ({@code kR}) of the material using a {@code Double3} object.
     *
     * @param kR the reflection attenuation coefficient to set
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Sets the attenuation coefficient of transparency ({@code kT}) of the material.
     *
     * @param kT the attenuation coefficient of transparency to set
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Sets the reflection attenuation coefficient ({@code kR}) of the material.
     *
     * @param kR the reflection attenuation coefficient to set
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Sets the number of rays used for simulating reflection.
     *
     * @param amount the number of rays for glossy surfaces
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setNumRaysReflected(int amount) {
        this.numRaysReflected = amount;
        return this;
    }

    /**
     * Sets the angle of the cone used for generating reflected rays.
     *
     * @param angle the angle of the cone in degrees
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setConeAngleReflected(double angle) {
        this.coneAngleReflected = Math.toRadians(angle);
        return this;
    }

    /**
     * Sets the number of rays used for simulating refraction.
     *
     * @param amount the number of rays for diffused glass
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setNumRaysRefracted(int amount) {
        this.numRaysRefracted = amount;
        return this;
    }

    /**
     * Sets the angle of the cone used for generating refracted rays.
     *
     * @param angle the angle of the cone in degrees
     * @return the current instance of the {@code Material} class for method chaining
     */
    public Material setConeAngleRefracted(double angle) {
        this.coneAngleRefracted = Math.toRadians(angle);
        return this;
    }
}


