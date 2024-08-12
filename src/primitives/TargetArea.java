package primitives;

import geometries.Intersectable;
import geometries.Plane;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.randomDoubleBetweenTwoNumbers;

/**
 * Utility class for generating random vectors and points within a defined cone or area,
 * useful for ray tracing and generating rays in different directions.
 */
public class TargetArea {

    /**
     * Calculates the sine of an angle (in radians) using a Taylor series expansion.
     *
     * @param x The angle in radians.
     * @return The sine of the angle.
     */
    private static double sin(double x) {
        double term = x, sum = x;
        for (int i = 3; term != 0; i += 2) {
            term *= -x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    /**
     * Calculates the cosine of an angle (in radians) using a Taylor series expansion.
     *
     * @param x The angle in radians.
     * @return The cosine of the angle.
     */
    private static double cos(double x) {
        double term = 1, sum = 1;
        for (int i = 2; term != 0; i += 2) {
            term *= -x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    /**
     * Calculates the tangent of an angle (in radians) using sine and cosine.
     *
     * @param x The angle in radians.
     * @return The tangent of the angle.
     */
    private static double tan(double x) {
        double sinX = sin(x);
        double cosX = cos(x);
        return sinX / cosX;
    }

    /**
     * Generates a list of random vectors within a specified cone.
     *
     * @param gp        The GeoPoint at the surface of the geometry.
     * @param n         The normal vector to the surface of the geometry at the point of gp.point.
     * @param coneAngle The angle of the cone in which the random rays will be generated (in radians).
     * @param amount    The number of random vectors to generate.
     * @param plane     The plane in which the random directions will be generated.
     * @return A list of random direction vectors within the cone defined by the normal vector.
     */
    public static List<Vector> generateRandomDirectionInCone(Intersectable.GeoPoint gp, Vector n, double coneAngle, int amount, Plane plane) {
        List<Vector> result = new LinkedList<>();

        double radius2 = tan(coneAngle) / 2;
        List<Vector> vectors = plane.findVectorsOfPlane();
        Vector x = vectors.get(0), y = vectors.get(1);

        List<Point> points = generatePoints(y, x, amount, gp.point.add(n), radius2);

        for (Point p : points) {
            result.add(p.subtract(gp.point));
        }

        return result;
    }

    /**
     * Generates a random vector within a specified range using two base vectors.
     *
     * @param vX   The x vector of the plane.
     * @param vY   The y vector of the plane.
     * @param size The size of the circle for the generation.
     * @return A random combination of a*vX + b*vY such that a,b are in the range [-size, size].
     */
    public static Vector generateVector(Vector vX, Vector vY, double size) {
        while (true) {
            try {
                return vX.scale(randomDoubleBetweenTwoNumbers(-size, size))
                        .add(vY.scale(randomDoubleBetweenTwoNumbers(-size, size)));
            } catch (Exception e) {
                // Retry if the random numbers result in a zero vector.
            }
        }
    }

    /**
     * Generates a list of points distributed within a square grid around a center point,
     * with added random jitter to each point.
     *
     * @param vX     The x vector of the plane used to determine the grid orientation.
     * @param vY     The y vector of the plane used to determine the grid orientation.
     * @param amount The number of points to generate.
     * @param center The center point of the grid.
     * @param radius The radius of the circle defining the grid size.
     * @return A list of generated points with jitter applied.
     */
    public static List<Point> generatePoints(Vector vX, Vector vY, int amount, Point center, double radius) {
        List<Point> points = new LinkedList<>();

        double divider = Math.sqrt(amount);
        double r = radius / divider;

        for (int k = 0; k < divider; k++) {
            for (int l = 0; l < divider; l++) {
                double yI = alignZero(-(k - (divider - 1) / 2) * r);
                double xJ = alignZero(-(l - (divider - 1) / 2) * r);

                Point pIJ = center;

                if (xJ != 0) pIJ = pIJ.add(vX.scale(xJ));
                if (yI != 0) pIJ = pIJ.add(vY.scale(yI));

                // Adding some random jitter
                pIJ = pIJ.add(generateVector(vX, vY, r));

                points.add(pIJ);
            }
        }

        return points;
    }
}

