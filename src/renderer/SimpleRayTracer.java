package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * The {@code SimpleRayTracer} class is a concrete implementation of {@code RayTracerBase}
 * that performs basic ray tracing by finding the closest intersection point and calculating
 * the color based on the scene's ambient light.
 */
public class SimpleRayTracer extends RayTracerBase {

    /**
     * Constructs a {@code SimpleRayTracer} with the specified scene.
     *
     * @param scene the scene to be used for ray tracing
     */
    SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Traces the given ray and returns the color determined by the ray tracing algorithm.
     * If there are no intersections, the background color is returned.
     * If there are intersections, the color at the closest intersection point is calculated.
     *
     * @param ray the ray to be traced
     * @return the color determined by the ray tracing algorithm
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = this.scene.geometries.findIntersections(ray);
        if (intersections == null) {
            return this.scene.background;
        }
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * Calculates the color at the specified point.
     * Currently, it returns the intensity of the scene's ambient light.
     *
     * @param point the point at which the color is calculated
     * @return the color at the specified point
     */
    private Color calcColor(Point point) {
        return this.scene.ambientLight.getIntensity();
    }
}

