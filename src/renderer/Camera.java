package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera implements Cloneable {
    private Point p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double width = 0.0;
    private double height = 0.0;
    private double distance = 0.0;

    private Camera() {}

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public Point getP0() {
        return p0;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }

    public static class Builder {
        private final Camera camera = new Camera();

        public Builder setLocation(Point location) {
            camera.p0 = location;
            return this;
        }

        public Builder setDirection(Vector to, Vector up) {
            if (isZero(to.dotProduct(up))) {
                camera.vTo = to.normalize();
                camera.vUp = up.normalize();
                return this;
            }
            throw new IllegalArgumentException("the vector are not orthogonal");
        }

        public Builder setVpSize(Double width, Double height) {
            camera.width = width;
            camera.height = height;
            return this;
        }

        public Builder setVpDistance(Double distance) {
            camera.distance = distance;
            return this;
        }

        public Camera build() {
            //if (camera.imageWriter == null) throw new MissingResourceException(…);
            //if (camera.rayTracer == null) throw new ...
            String errorMessage = "Missing data for rendering";
            if (camera.p0 == null) throw new MissingResourceException(errorMessage, "Camera", "p0");
            if (camera.vUp == null) throw new MissingResourceException(errorMessage, "Camera", "vUp");
            if (camera.vTo == null) throw new MissingResourceException(errorMessage, "Camera", "vTo");
            if (Util.alignZero(camera.width) <= 0) throw new IllegalArgumentException("width can't be negative or 0");
            if (Util.alignZero(camera.height) <= 0) throw new IllegalArgumentException("height can't be negative or 0");
            if (Util.alignZero(camera.distance) <= 0)
                throw new IllegalArgumentException("distance can't be negative or 0");
            if (!isZero(camera.vRight.dotProduct(camera.vTo)))
                throw new IllegalArgumentException("wrong values in vRight");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            //camera.viewPlanePC = camera.p0.add(camera.vTo.scale(camera.distance));
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
    }


}


}
