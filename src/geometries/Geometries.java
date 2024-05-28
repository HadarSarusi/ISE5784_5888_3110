package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    private final List<Intersectable> geometries = new LinkedList<>();

    public Geometries(){};
    public Geometries(Intersectable... geometries){
        add(geometries);
    }

    public void add(Intersectable... geometries){
        for(Intersectable geometry: geometries)
        {
            this.geometries.add(geometry);
        }
    }

    public List<Point> findIntersections(Ray ray){
        List<Point> result = new LinkedList<>();
        for(Intersectable geometry: geometries)
        {
           if(geometry.findIntersections(ray) != null){
               result.addAll(geometry.findIntersections(ray));
           }
        }
        if(result.size()==0) {
            return null;
        }
        return result;
    }
}
