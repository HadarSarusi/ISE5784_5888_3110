package geometries;

import primitives.*;

import java.util.List;

/**
 * The {@code Intersectable} interface represents geometric objects that can be intersected by rays.
 * Implementations of this interface should define the specific intersection logic for the geometric shape.
 */
public abstract class Intersectable {

    /**
     * Finds the intersection points of a given ray with the geometric object.
     *
     * @param ray the ray to intersect with the geometric object
     * @return a list of intersection points with the geometric object.
     * If there are no intersections, null is returned.
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the geometric intersections of a given ray with the geometric object.
     * This method should be implemented by subclasses to compute actual intersection points.
     *
     * @param ray the ray to intersect with the geometric object
     * @return a list of {@code GeoPoint} objects representing the geometric intersections.
     * If there are no intersections, null is returned.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Helper method to be implemented by subclasses to find the geometric intersections
     * of a given ray with the geometric object.
     *
     * @param ray the ray to intersect with the geometric object
     * @return a list of {@code GeoPoint} objects representing the geometric intersections.
     * If there are no intersections, null is returned.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Represents a geometric intersection point consisting of a geometry and a point in 3D space.
     */
    public static class GeoPoint {
        /**
         * The geometry associated with the intersection point.
         */
        public Geometry geometry;
        /**
         * The intersection point in 3D space.
         */
        public Point point;

        /**
         * Constructs a {@code GeoPoint} with the specified geometry and point.
         *
         * @param geometry the geometry associated with the intersection point
         * @param point    the intersection point in 3D space
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof GeoPoint geoPoint) &&
                    this.geometry.equals(geoPoint.geometry) &&
                    this.point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}


