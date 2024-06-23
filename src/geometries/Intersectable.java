package geometries;

import primitives.*;

import java.util.List;

/**
 * The {@code Intersectable} interface represents geometric objects that can be intersected by rays.
 * <p>
 * Implementations of this interface should define the specific intersection logic for the geometric shape.
 * </p>
 */
public abstract class Intersectable {

    /**
     * Finds the intersection points of a given ray with the geometric object.
     *
     * @param ray the ray to intersect with the geometric object
     * @return a list of intersection points with the geometric object.
     * If there are no intersections, null is returned.
     */
    public  List<Point> findIntersections(Ray ray){
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    public static class GeoPoint {
        public Geometry geometry;
        public Point point;
        public GeoPoint(Geometry geometry,Point point)
        {
            this.geometry=geometry;
            this.point=point;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            return (obj instanceof GeoPoint geoPoint) &&
                    this.geometry.equals(geoPoint.geometry)
                    &&this.point.equals(geoPoint.point);
        }
        @Override
        public String toString() {
            return "" + geometry+""+point;
        }

    }
    public List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}

