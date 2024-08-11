package primitives;

import geometries.Intersectable;
import geometries.Plane;

import java.util.LinkedList;
import java.util.List;

import static primitives.Double3.ZERO;
import static primitives.Util.randomDoubleBetweenTwoNumbers;

/**
 * Vector class representing a vector.
 * This class extends the Point class and represents a vector in 3D space.
 *
 * @author Lea &amp; Hadar
 */
public class Vector extends Point {

    /**
     * Represents the unit vector along the x-axis.
     */
    public final static Vector X = new Vector(1, 0, 0);
    /**
     * Represents the unit vector along the y-axis.
     */
    public final static Vector Y = new Vector(0, 1, 0);
    /**
     * Represents the unit vector along the z-axis.
     */
    public final static Vector Z = new Vector(0, 0, -1);

    /**
     * Constructs a vector with the specified coordinates.
     *
     * @param x The x-coordinate of the vector.
     * @param y The y-coordinate of the vector.
     * @param z The z-coordinate of the vector.
     * @throws IllegalArgumentException if the vector is zero.
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector can't be zero");
    }

    /**
     * Constructs a vector with the specified `Double3` object.
     *
     * @param xyz The `Double3` object representing the coordinates of the vector.
     * @throws IllegalArgumentException if the vector is zero.
     */
    public Vector(Double3 xyz) {
        super(xyz);
        if (xyz.equals(Double3.ZERO))
            throw new IllegalArgumentException("vector can't be zero");
    }

    /**
     * Constructs a new {@code Vector} object by copying the values from an existing {@code Vector}.
     *
     * @param vector the {@code Vector} object to copy from
     */
    public Vector(Vector vector) {
        super(vector.xyz);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj instanceof Vector other && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

    /**
     * Returns the sum of this vector and the specified vector.
     *
     * @param vector The vector to add.
     * @return A new vector representing the result of the addition.
     */
    public Vector add(Vector vector) {
        return new Vector(this.xyz.add(vector.xyz));
    }

    /**
     * Scales this vector by the specified scalar.
     *
     * @param scale The scalar value.
     * @return A new vector representing the result of the scaling.
     */
    public Vector scale(double scale) {
        return new Vector(this.xyz.scale(scale));
    }

    /**
     * Calculates the dot product of this vector and the specified vector.
     *
     * @param vector The vector to calculate the dot product with.
     * @return The dot product of the vectors.
     */
    public double dotProduct(Vector vector) {
        return (this.xyz.d1 * vector.xyz.d1) + (this.xyz.d2 * vector.xyz.d2) + (this.xyz.d3 * vector.xyz.d3);
    }

    /**
     * Calculates the cross product of this vector and the specified vector.
     *
     * @param vector The vector to calculate the cross product with.
     * @return A new vector representing the result of the cross product.
     */
    public Vector crossProduct(Vector vector) {
        return new Vector((this.xyz.d2 * vector.xyz.d3) - (this.xyz.d3 * vector.xyz.d2),
                (this.xyz.d3 * vector.xyz.d1) - (this.xyz.d1 * vector.xyz.d3),
                (this.xyz.d1 * vector.xyz.d2) - (this.xyz.d2 * vector.xyz.d1));
    }

    /**
     * Calculates the square of the length of this vector.
     *
     * @return The square of the length of the vector.
     */
    public double lengthSquared() {
        return this.dotProduct(this);
    }

    /**
     * Calculates the length of this vector.
     *
     * @return The length of the vector.
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * Normalizes this vector.
     *
     * @return A new vector representing the normalized vector.
     */
    public Vector normalize() {
        return new Vector(this.xyz.reduce(length()));
    }

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
}
