package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The {@code SimpleRayTracer} class is a concrete implementation of {@code RayTracerBase}
 * that performs basic ray tracing by finding the closest intersection point and calculating
 * the color based on the scene's ambient light and local effects such as diffuse and specular reflection.
 */
public class SimpleRayTracer extends RayTracerBase {
    /**
     * Recursion stopping condition for reflection and refraction calculations.
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * Minimum coefficient for color contribution to stop recursion.
     */
    private static final Double3 MIN_CALC_COLOR_K = new Double3(0.001);

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
        GeoPoint intersection = findClosestIntersection(ray);
        return intersection == null
                ? this.scene.background : calcColor(intersection, ray);
    }

    /**
     * Finds the closest intersection point between the ray and the geometries in the scene.
     *
     * @param ray the ray to be traced
     * @return the closest intersection point, or null if no intersections are found
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersections = this.scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersections);
    }

    /**
     * Calculates the color at the specified point by adding the ambient light intensity
     * and the local effects such as diffuse and specular reflection.
     *
     * @param geoPoint the point at which the color is calculated
     * @param ray      the ray that intersected the geometry
     * @return the color at the specified point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculates the color at the specified point by considering both local and global effects.
     *
     * @param gp    the point at which the color is calculated
     * @param ray   the ray that intersected the geometry
     * @param level the recursion level for global effects calculation
     * @param k     the attenuation coefficient for the global effects
     * @return the color at the specified point
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Material material = gp.geometry.getMaterial();
        Vector v = ray.getDirection();
        Vector n = gp.geometry.getNormal(gp.point);
        Color color = calcLocalEffects(gp, material, v, n, k);
        return 1 == level ? color : color.add(calcGlobalEffects(gp, level, k, material, v, n));
    }

    /**
     * Calculates the global effects (reflection and refraction) at the specified point.
     *
     * @param gp     the point at which the global effects are calculated
     * @param level  the recursion level for global effects calculation
     * @param k      the attenuation coefficient for the global effects
     * @param material the material properties of the geometry at the given point
     * @param v      the view vector from the point to the camera
     * @param n      the normal vector at the point on the geometry
     * @return the color at the specified point after applying global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, int level, Double3 k, Material material, Vector v, Vector n) {
        return calcGlobalEffect(constructRefractedRay(gp.point, v, n), material.kT, level, k)
                .add(calcGlobalEffect(constructReflectedRay(gp.point, v, n), material.kR, level, k));
    }

    /**
     * Constructs a reflected ray from the specified point.
     *
     * @param p the point from which the reflected ray is constructed
     * @param v the view vector
     * @param n the normal vector at the point on the geometry
     * @return the reflected ray
     */
    private Ray constructReflectedRay(Point p, Vector v, Vector n) {
        return new Ray(p, v.subtract(n.scale(v.dotProduct(n) * 2)), n);
    }

    /**
     * Constructs a refracted ray from the specified point.
     *
     * @param p the point from which the refracted ray is constructed
     * @param v the view vector
     * @param n the normal vector at the point on the geometry
     * @return the refracted ray
     */
    private Ray constructRefractedRay(Point p, Vector v, Vector n) {
        return new Ray(p, v, n);
    }

    /**
     * Calculates the global effect (reflection or refraction) at the specified point.
     *
     * @param ray   the ray to be traced
     * @param kx    the attenuation coefficient for the effect
     * @param level the recursion level for global effects calculation
     * @param k     the attenuation coefficient for the global effects
     * @return the color at the specified point after applying the global effect
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx))
                .scale(kx);
    }

    /**
     * Calculates the local lighting effects at a given point on a geometry in the scene.
     * The method considers the emission of the geometry, as well as the diffuse and
     * specular reflections from each light source.
     *
     * @param gp The GeoPoint representing the point on the geometry.
     * @param material The material properties of the geometry at the given point.
     * @param v The view vector from the point to the camera.
     * @param n The normal vector at the point on the geometry.
     * @param k The attenuation factor of the reflection.
     * @return The color at the point after applying local lighting effects.
     */
    private Color calcLocalEffects(GeoPoint gp, Material material, Vector v, Vector n, Double3 k) {
        double nv = alignZero(n.dotProduct(v));
        Color color = gp.geometry.getEmission();
        if (nv == 0) return color;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if ((nl * nv > 0)) {// sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, l, n, nl, lightSource, k);
                if (!ktr.equals(Double3.ZERO)) {
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)))
                            .add(iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }
        }
        return color;
    }

    /**
     * Calculates the diffuse reflection component.
     *
     * @param material the material of the intersected geometry
     * @param nl       the dot product of the normal vector and the light vector
     * @return the diffuse reflection component
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Calculates the specular reflection component.
     *
     * @param material the material of the intersected geometry
     * @param n        the normal vector at the intersection point
     * @param l        the light vector
     * @param nl       the dot product of the normal vector and the light vector
     * @param v        the view vector
     * @return the specular reflection component
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl * 2));
        double minusVR = -alignZero(v.dotProduct(r));
        return minusVR <= 0 ? Double3.ZERO : material.kS.scale(Math.pow(minusVR, material.nShininess));
    }

    /**
     * Determines if a given point is unshaded by any objects, considering a specific light source.
     *
     * @param gp    The geographic point to be checked for shading.
     * @param l     The vector representing the direction from the point to the light source.
     * @param n     The normal vector at the geographic point.
     * @param nl    The vector representing the dot product of the normal vector and the light direction vector.
     * @param light The light source being considered.
     * @return A boolean value indicating whether the point is unshaded (true) or shaded (false).
     */
    @Deprecated(forRemoval = true)
    private boolean unshaded(GeoPoint gp, Vector l, Vector n, double nl, LightSource light) {
        Vector lightDirection = l.scale(-1); // from point to light source
        if (gp.geometry.getMaterial().kT == Double3.ZERO) {
            Ray ray = new Ray(gp.point, lightDirection, n);
            List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
            if (intersections == null) return true;

            double lightDistance = light.getDistance(gp.point);
            for (GeoPoint intersection : intersections) {
                if (alignZero(lightDistance - gp.point.distanceSquared(intersection.point)) >= 0)
                    if (intersection.geometry.getMaterial().kT.equals(Double3.ZERO)) return false;
            }
        }
        return true;
    }

    /**
     * Calculates the transparency factor at the specified point considering the light source.
     *
     * @param gp The GeoPoint representing the point on the geometry.
     * @param l The light vector.
     * @param n The normal vector at the point on the geometry.
     * @param nl The dot product of the normal vector and the light vector.
     * @param light The light source.
     * @param k The attenuation factor of the reflection.
     * @return The transparency factor.
     */
    private Double3 transparency(GeoPoint gp, Vector l, Vector n, double nl, LightSource light, Double3 k) {
        Vector lightDirection = l.scale(-1);
        Ray ray = new Ray(gp.point, lightDirection, n);
        Double3 ktr = Double3.ONE;// from point to light source
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return ktr;

        double lightDistance = light.getDistance(gp.point);
        for (GeoPoint intersection : intersections) {
            if (alignZero(lightDistance - gp.point.distanceSquared(intersection.point)) >= 0) {
                ktr = intersection.geometry.getMaterial().kT.product(ktr);
                if (ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }
    /**
     * @param rays the list of rays hitting the geometry
     * @param level the level of recursion if level == 1 we stop the recursion
     * @param k the parameter helping us calculate how much color each ray is giving to the final pixel
     * @param kx a parameter helping us stop the recursion is the effect of the recursion is too small to notice
     * @return the color at the intersection with ray
     */
    private Color calcGlobalEffect(List<Ray> rays, int level, Double3 k, Double3 kx) {
        Color color = new Color(0,0,0);

        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;

        for(Ray ray: rays) {
            GeoPoint gp = findClosestIntersection(ray);
            if (gp == null) return scene.background.scale(kx);
            color = color.add(isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDirection())) ? Color.BLACK : calcColor(gp, ray, level - 1, kkx).scale(kx));
        }
        return color.scale((double) 1 / rays.size());
    }
    /**
     * Constructs a list of random reflected rays within the cone of the normal vector at the given surface point.
     *
     * @param gp The GeoPoint at the surface of the geometry.
     * @param v The direction of the original ray.
     * @param n The normal to the surface of the geometry at the point of gp.point.
     * @return A list of random reflected rays within the cone of the normal vector.
     */
    private List<Ray> constructReflectedRays(GeoPoint gp, Vector v, Vector n) {
        Material material = gp.geometry.getMaterial();

        if (material.numRaysReflected == 1 || isZero(material.coneAngleReflected))
            return List.of(constructReflectedRay(gp.point, v, n));

        List<Ray> rays = new ArrayList<>();

        // Generate random direction vectors within the cone of the normal vector
        List<Vector> randomDirection = Vector.generateRandomDirectionInCone(gp, n, material.coneAngleReflected, material.numRaysReflected);

        // Construct rays using the random direction vectors and add them to the list
        for (int i = 0; i < randomDirection.size() && i < material.numRaysReflected; i++) {
            Vector u = randomDirection.get(i);
            Ray reflectedRay = new Ray(gp.point, u, n);
            rays.add(reflectedRay);
        }

        rays.add(constructRefractedRay(gp.point, v, n));

        return rays;
    }
    /**
     * Constructs a list of random refracted rays within the cone of the inverted normal vector at the given surface point.
     *
     * @param gp The GeoPoint at the surface of the geometry.
     * @param v The direction of the original ray.
     * @param n The normal to the surface of the geometry at the point of gp.point.
     * @return A list of random refracted rays within the cone of the inverted normal vector.
     */
    private List<Ray> constructRefractedRays(GeoPoint gp, Vector v, Vector n) {
        Material material = gp.geometry.getMaterial();

        if (material.numRaysRefracted == 1 || isZero(material.coneAngleRefracted))
            return List.of(constructRefractedRay(gp.point, v, n));

        List<Ray> rays = new ArrayList<>();

        // Generate random direction vectors within the cone of the inverted normal vector
        List<Vector> randomDirection = Vector.generateRandomDirectionInCone(gp, v, material.coneAngleRefracted, material.numRaysRefracted);

        // Construct rays using the random direction vectors and add them to the list
        for (int i = 0; i < randomDirection.size() && i < material.numRaysRefracted; i++) {
            Vector u = randomDirection.get(i);
            Ray refractedRay = new Ray(gp.point, u, n);
            rays.add(refractedRay);
        }
        rays.add(constructRefractedRay(gp.point , v, n));

        return rays;
    }
}



