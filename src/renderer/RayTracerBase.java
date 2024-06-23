package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * The {@code RayTracerBase} class is an abstract base class for ray tracing operations.
 * It contains a reference to a {@code Scene} and provides an abstract method for tracing rays.
 */
public abstract class RayTracerBase {
    /**
     * The scene to be used for ray tracing.
     */
    protected Scene scene;

    /**
     * Constructs a {@code RayTracerBase} with the specified scene.
     *
     * @param scene the scene to be used for ray tracing
     */
    RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces the given ray and returns the color determined by the ray tracing algorithm.
     *
     * @param ray the ray to be traced
     * @return the color determined by the ray tracing algorithm
     */
    public abstract Color traceRay(Ray ray);
}

