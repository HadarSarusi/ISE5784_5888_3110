package renderer;

import geometries.Plane;
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
     * A flag to enable or disable adaptive super sampling.
     */
    private static boolean adaptiveSuperSampling = false;

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
     * Enables or disables adaptive super sampling.
     *
     * @param adaptiveSuperSampling {@code true} to enable adaptive super sampling; {@code false} to disable it.
     * @return the current instance of {@code SimpleRayTracer} for method chaining.
     */
    public SimpleRayTracer setAdaptiveSuperSampling(boolean adaptiveSuperSampling) {
        this.adaptiveSuperSampling = adaptiveSuperSampling;
        return this;
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
        Color color = calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE);
        return color.add(scene.ambientLight.getIntensity());

//        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, Double3.ONE)
//                .add(scene.ambientLight.getIntensity());
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
        return 1 == level ? color : color.add(calcGlobalEffects(gp, level, k, material, ray, n));


    }

    /**
     * Calculates the global effects (reflection and refraction) at the specified point.
     *
     * @param gp       the point at which the global effects are calculated
     * @param level    the recursion level for global effects calculation
     * @param k        the attenuation coefficient for the global effects
     * @param material the material properties of the geometry at the given point
     * @param v        the view vector from the point to the camera
     * @param n        the normal vector at the point on the geometry
     * @return the color at the specified point after applying global effects
     */
    private Color calcGlobalEffects(GeoPoint gp, int level, Double3 k, Material material, Ray ray, Vector n) {
        Plane plane = new Plane(gp.point, n);
        if (!adaptiveSuperSampling) {
            return calcGlobalEffect(constructRefractedRays(gp, ray.getDirection(), n, plane), level, k, material.kT).
                    add(calcGlobalEffect(constructReflectedRays(gp, ray.getDirection(), n, plane), level, k, material.kR));
        } else {
            return ReflectedAddaptiveRays(ray.getHead(), material.numRaysReflected, plane, level, k, material.kR).
                    add(ReflectedAddaptiveRays(ray.getHead(), material.numRaysRefracted, plane, level, k, material.kT));
        }
    }

    /**
     * Generates and traces reflected rays using adaptive super sampling.
     * <p>
     * This method calculates the number of reflected rays to be generated based on the
     * specified resolution and uses adaptive super sampling to improve rendering efficiency.
     * It calculates the pixel size based on the square root of the number of rays and then
     * delegates the actual sampling to the {@code AdaptiveSuperSampling} method.
     *
     * @param head             The starting point of the reflected rays.
     * @param numRaysReflected The total number of reflected rays to generate.
     * @param plane            The plane on which the reflection occurs.
     * @param level            The current recursion level for tracing rays.
     * @param k                The coefficient for attenuation of the reflected color.
     * @param kR               The reflection coefficient.
     * @return The accumulated color from the reflected rays.
     */
    private Color ReflectedAddaptiveRays(Point head, int numRaysReflected, Plane plane, int level, Double3 k, Double3 kR) {
        //Calculate the size of each pixel
        double rX = Math.sqrt(numRaysReflected);
        double rY = rX;
        //HashMap<Ray,Color> storeColor = new HashMap<>();
        return AdaptiveSuperSampling(head, rX, rY, numRaysReflected, plane, level, k, kR);
    }

    /**
     * Performs adaptive super sampling by recursively subdividing pixels and sampling colors.
     *
     * @param head             The starting point for the sampling rays.
     * @param rX               The horizontal size of the pixel.
     * @param rY               The vertical size of the pixel.
     * @param numRaysReflected The number of rays to cast for reflection.
     * @param plane            The plane on which sampling is performed.
     * @param level            The current recursion level for ray tracing.
     * @param k                The attenuation coefficient for the color.
     * @param kR               The reflection coefficient.
     * @return The averaged color from the sampled rays.
     */
    private Color AdaptiveSuperSampling(Point head, double rX, double rY, int numRaysReflected, Plane plane, int level, Double3 k, Double3 kR) {
        List<Vector> vectors = plane.findVectorsOfPlane();
        // Cast rays for the four corners of the pixel
        Color topLeft = castRayAndColor(rX, rY, -1, -1, head, vectors, level, k, kR);
        Color topRight = castRayAndColor(rX, rY, 1, -1, head, vectors, level, k, kR);
        Color bottomLeft = castRayAndColor(rX, rY, -1, 1, head, vectors, level, k, kR);
        Color bottomRight = castRayAndColor(rX, rY, 1, 1, head, vectors, level, k, kR);

        // Check if all four colors are similar or the rayNum has reached the limit
        if ((topLeft.equals(topRight)
                && topLeft.equals(bottomLeft)
                && topLeft.equals(bottomRight))
                || numRaysReflected <= 0) {

            // Return the colorRay if all four colors are similar or rayNum limit reached
            return topLeft;
        } else {
            // Recursively divide the pixel and perform adaptive supersampling
            double newRx = rX / 2;
            double newRy = rY / 2;

            // Compute the four subpixel points within the pixel
            Point A = head
                    .add(vectors.get(1).scale((newRy) * -1)
                            .add(vectors.get(0).scale((newRx) * -1)));

            Point B = head
                    .add(vectors.get(1).scale((newRy) * 1)
                            .add(vectors.get(0).scale((newRx) * -1)));

            Point C = head
                    .add(vectors.get(1).scale((newRy) * -1)
                            .add(vectors.get(0).scale((newRx) * 1)));

            Point D = head
                    .add(vectors.get(1).scale((newRy) * 1)
                            .add(vectors.get(0).scale((newRx) * 1)));

            // Recursively compute the color of the subpixels
            Color topLeftSubpixel = AdaptiveSuperSampling(A, newRx, newRy, numRaysReflected / 4, plane, level, k, kR);
            Color topRightSubpixel = AdaptiveSuperSampling(B, newRx, newRy, numRaysReflected / 4, plane, level, k, kR);
            Color bottomLeftSubpixel = AdaptiveSuperSampling(C, newRx, newRy, numRaysReflected / 4, plane, level, k, kR);
            Color bottomRightSubpixel = AdaptiveSuperSampling(D, newRx, newRy, numRaysReflected / 4, plane, level, k, kR);

            // Compute the average color of the subpixels
            return topLeftSubpixel
                    .add(topRightSubpixel)
                    .add(bottomLeftSubpixel)
                    .add(bottomRightSubpixel)
                    .reduce(4);

        }
    }

    /**
     * Casts a ray from the given point and calculates the resulting color using global effects.
     *
     * @param rX      The horizontal size of the pixel.
     * @param rY      The vertical size of the pixel.
     * @param offsetX The horizontal offset for the ray direction.
     * @param offsetY The vertical offset for the ray direction.
     * @param head    The starting point for the ray.
     * @param vectors The basis vectors of the plane for calculating direction.
     * @param level   The current recursion level for ray tracing.
     * @param k       The attenuation coefficient for color.
     * @param kR      The reflection coefficient.
     * @return The color calculated from the cast ray.
     */
    private Color castRayAndColor(double rX, double rY, int offsetX, int offsetY, Point head, List<Vector> vectors, int level, Double3 k, Double3 kR) {
        //צריך לקבל את ו y x
        Vector x = vectors.get(0), y = vectors.get(1);
        Point cornerPoint = head.add(y.scale((rY / 2) * offsetY)
                .add(x.scale((rX / 2) * offsetX)));
        List<Ray> cornerRay = new ArrayList<>(List.of(new Ray(head, cornerPoint.subtract(head))));
        // Perform ray casting and return the ColorRay
        return calcGlobalEffect(cornerRay, level, k, kR);
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
//    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
//        Double3 kkx = kx.product(k);
//        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
//        GeoPoint gp = findClosestIntersection(ray);
//        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx))
//                .scale(kx);
//    }

    /**
     * Calculates the local lighting effects at a given point on a geometry in the scene.
     * The method considers the emission of the geometry, as well as the diffuse and
     * specular reflections from each light source.
     *
     * @param gp       The GeoPoint representing the point on the geometry.
     * @param material The material properties of the geometry at the given point.
     * @param v        The view vector from the point to the camera.
     * @param n        The normal vector at the point on the geometry.
     * @param k        The attenuation factor of the reflection.
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
                Double3 ktr = transparency(gp, l, n, lightSource, k);
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
     * @deprecated Please use {@link #transparency(GeoPoint, Vector, Vector, LightSource, Double3)} instead
     */
    @Deprecated(forRemoval = true)
    @SuppressWarnings("unused")
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
     * @param gp    The GeoPoint representing the point on the geometry.
     * @param l     The light vector.
     * @param n     The normal vector at the point on the geometry.
     * @param nl    The dot product of the normal vector and the light vector.
     * @param light The light source.
     * @param k     The attenuation factor of the reflection.
     * @return The transparency factor.
     */
    private Double3 transparency(GeoPoint gp, Vector l, Vector n, LightSource light, Double3 k) {
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
     * Calculates the global effect of a given list of rays on the color at a point.
     *
     * @param rays  the list of rays hitting the geometry
     * @param level the level of recursion if level == 1 we stop the recursion
     * @param k     the parameter helping us calculate how much color each ray is giving to the final pixel
     * @param kx    a parameter helping us stop the recursion is the effect of the recursion is too small to notice
     * @return the color at the intersection with ray
     */
    private Color calcGlobalEffect(List<Ray> rays, int level, Double3 k, Double3 kx) {
        Color color = Color.BLACK;

        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return scene.background;

        for (Ray ray : rays) {
            GeoPoint gp = findClosestIntersection(ray);
            if (gp == null) return scene.background.scale(kx);
            color = color.add(isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDirection())) ? Color.BLACK : calcColor(gp, ray, level - 1, kkx).scale(kx));
        }
        return color.scale((double) 1 / rays.size());
//            if (gp == null) return scene.background;
//            //color = (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
//            color = color.add(isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDirection()))
//                  ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx));
//        }
//        return color.reduce(rays.size());
    }

    /**
     * Constructs a list of random reflected rays within the cone of the normal vector at the given surface point.
     *
     * @param gp The GeoPoint at the surface of the geometry.
     * @param v  The direction of the original ray.
     * @param n  The normal to the surface of the geometry at the point of gp.point.
     * @return A list of random reflected rays within the cone of the normal vector.
     */
    private List<Ray> constructReflectedRays(GeoPoint gp, Vector v, Vector n, Plane plane) {
        Material material = gp.geometry.getMaterial();

        if (material.numRaysReflected == 1 || isZero(material.coneAngleReflected))
            return List.of(constructReflectedRay(gp.point, v, n));

        List<Ray> rays = new ArrayList<>();

        // Generate random direction vectors within the cone of the normal vector
        List<Vector> randomDirection = TargetArea.generateRandomDirectionInCone(gp, n, material.coneAngleReflected, material.numRaysReflected, plane);

        // Construct rays using the random direction vectors and add them to the list
        for (int i = 0; i < randomDirection.size() && i < material.numRaysReflected; i++) {
            Vector u = randomDirection.get(i);
            Ray reflectedRay = new Ray(gp.point, u, n);
            rays.add(reflectedRay);
        }

        rays.add(constructReflectedRay(gp.point, v, n));

        return rays;
    }

    /**
     * Constructs a list of random refracted rays within the cone of the inverted normal vector at the given surface point.
     *
     * @param gp The GeoPoint at the surface of the geometry.
     * @param v  The direction of the original ray.
     * @param n  The normal to the surface of the geometry at the point of gp.point.
     * @return A list of random refracted rays within the cone of the inverted normal vector.
     */
    private List<Ray> constructRefractedRays(GeoPoint gp, Vector v, Vector n, Plane plane) {
        Material material = gp.geometry.getMaterial();
        if (material.numRaysRefracted == 1 || isZero(material.coneAngleRefracted))
            return List.of(constructRefractedRay(gp.point, v, n));
        List<Ray> rays = new ArrayList<>();
        // Generate random direction vectors within the cone of the inverted normal vector
        List<Vector> randomDirection = TargetArea.generateRandomDirectionInCone(gp, v, material.coneAngleRefracted, material.numRaysRefracted, plane);
        // Construct rays using the random direction vectors and add them to the list
        for (int i = 0; i < randomDirection.size() && i < material.numRaysRefracted; i++) {
            Vector u = randomDirection.get(i);
            Ray refractedRay = new Ray(gp.point, u, n);
            rays.add(refractedRay);
        }
        rays.add(constructRefractedRay(gp.point, v, n));
        return rays;


    }

}



