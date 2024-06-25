package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.util.Collections.max;
import static primitives.Util.alignZero;

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
    public SimpleRayTracer(Scene scene) {
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
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        return intersections == null
                ? this.scene.background : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * Calculates the color at the specified point.
     * Currently, it returns the intensity of the scene's ambient light.
     *
     * @param geoPoint the point at which the color is calculated
     * @return the color at the specified point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {

        return this.scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl))).add(iL.scale(calcSpecular(material, n, l, nl, v)));
            }
        }
        return color;
    }

    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2));
        return (material.kS.scale(Math.pow(Math.max(0, v.scale(-1).dotProduct(r)), material.nShininess)));
    }
}

