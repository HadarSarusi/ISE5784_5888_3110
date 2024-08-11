package primitives;

import geometries.Intersectable;
import geometries.Plane;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.randomDoubleBetweenTwoNumbers;

public class TargetArea {
    // פונקציה לחישוב סינוס
    private static double sin(double x) {
        double term = x, sum = x;
        for (int i = 3; term != 0; i += 2) {
            term *= -x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    // פונקציה לחישוב קוסינוס
    private static double cos(double x) {
        double term = 1, sum = 1;
        for (int i = 2; term != 0; i += 2) {
            term *= -x * x / (i * (i - 1));
            sum += term;
        }
        return sum;
    }

    // פונקציה לחישוב טנגנס באמצעות סינוס וקוסינוס
    private static double tan(double x) {
        double sinX = sin(x);
        double cosX = cos(x);
        return sinX / cosX;
    }


    /**
     * Generates a list of random vectors within a specified cone.
     *
     * @param gp        gp the GeoPoint at the surface of the geometry
     * @param n         n the normal to the surface of the geometry at the point of gp.point
     * @param coneAngle coneAngle the angle of the cone in which the random rays will be generated (in radians)
     * @param amount    the number of random vector to generate
     * @return list of random direction vector within the cone defined by the normal vector
     */
    public static List<Vector> generateRandomDirectionInCone(Intersectable.GeoPoint gp, Vector n, double coneAngle, int amount, Plane plane) {
        List<Vector> result = new LinkedList<>();

        //double radius1 = Math.tan(coneAngle) / 2;
        double radius2 = tan(coneAngle)/2;
        //System.out.println("this is tan"+ radius1);
        //System.out.println("this is sin"+radius2);

        //Plane plane = new Plane(gp.point, n);
        List<Vector> vectors = plane.findVectorsOfPlane();
        Vector x = vectors.get(0), y = vectors.get(1);

        List<Point> points = generatePoints(y, x, amount, gp.point.add(n), radius2);

        for (Point p : points) {
            result.add(
                    p.subtract(gp.point)
            );
        }

        return result;
    }

    /**
     * * Generates a random vector within a specified range using two base vectors.
     * *
     *
     * @param vX   the x vector of the plane
     * @param vY   the y vector of the plane
     * @param size the size of the circle of the generation
     * @return a random combination of a*vX + b*vY such as a,b in [-size,size]
     */
    public static Vector generateVector(Vector vX, Vector vY, double size) {
        while (true) {
            try {
                return vX.scale(randomDoubleBetweenTwoNumbers(-size, size))
                        .add(vY.scale(randomDoubleBetweenTwoNumbers(-size, size)));
            } catch (Exception e) {
                // if the random number is 0, and we don't have 0 vector
            }
        }

    }

    /**
     * Generates a list of points distributed within a square grid around a center point,
     * with added random jitter to each point.
     *
     * @param vX     the x vector of the plane used to determine the grid orientation.
     * @param vY     the y vector of the plane used to determine the grid orientation.
     * @param amount the number of points to generate.
     * @param center the center point of the grid.
     * @param radius the size of the circle of the grid.
     * @return a list of generated points with jitter applied.
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

                // adding some random jitter
                pIJ = pIJ.add(generateVector(vX, vY, r));

                points.add(pIJ);
            }
        }

        return points;
    }
}
