package geometries;

import primitives.*;

/**
 * Geometry is an abstract base class for all geometrical shapes.
 * It extends {@code Intersectable} and provides methods to manipulate properties
 * such as emission and material of the geometrical object.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The color emitted by the geometry.
     */
    protected Color emission = Color.BLACK;

    /**
     * The material properties of the geometry.
     */
    private Material material = new Material();

    /**
     * Calculates the normal vector to the geometry at the specified point.
     *
     * @param point The point on the geometry's surface.
     * @return The normal vector to the geometry at the given point.
     */
    public abstract Vector getNormal(Point point);

    /**
     * Gets the emission color of the geometry.
     *
     * @return The emission color of the geometry.
     */
    public Color getEmission() {
        return this.emission;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission The new emission color to set.
     * @return This geometry object for method chaining.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Gets the material of the geometry.
     *
     * @return The material of the geometry.
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * Sets the material of the geometry.
     *
     * @param material The new material to set.
     * @return This geometry object for method chaining.
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}

