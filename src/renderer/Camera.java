package renderer;

import primitives.*;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * The {@code Camera} class represents a camera in a 3D scene, used to construct rays
 * for ray tracing.
 *
 * @author Lea &amp; Hadar.
 */
public class Camera implements Cloneable {
    /**
     * The position of the camera in 3D space.
     */
    private Point p0;

    /**
     * The upward direction vector of the camera.
     */
    private Vector vUp;

    /**
     * The forward direction vector of the camera.
     */
    private Vector vTo;

    /**
     * The rightward direction vector of the camera.
     */
    private Vector vRight;

    /**
     * The width of the camera's viewport.
     */
    private double width = 0.0;

    /**
     * The height of the camera's viewport.
     */
    private double height = 0.0;

    /**
     * The distance from the camera to the viewport.
     */
    private double distance = 0.0;
    /**
     * The {@code ImageWriter} used to write the rendered image to a file.
     */
    private ImageWriter imageWriter;

    /**
     * The {@code RayTracerBase} used to perform ray tracing for the camera.
     */
    private RayTracerBase rayTracer;

    /**
     * Private constructor to prevent direct instantiation.
     * Use the {@code Builder} to create instances of {@code Camera}.
     */
    private Camera() {
    }

    /**
     * get width
     *
     * @return the width of the view plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * get height
     *
     * @return the height of the view plane
     */
    public double getHeight() {
        return height;
    }

    /**
     * get distance
     *
     * @return the distance from the camera to the view plane
     */
    public double getDistance() {
        return distance;
    }

    /**
     * get p0
     *
     * @return the location of the camera
     */
    public Point getP0() {
        return p0;
    }

    /**
     * get vup
     *
     * @return the up direction vector of the camera
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * get vto
     *
     * @return the forward direction vector of the camera
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * get builder
     *
     * @return a new {@code Builder} instance
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Constructs a ray through a specific pixel on the view plane.
     *
     * @param nX number of horizontal pixels
     * @param nY number of vertical pixels
     * @param j  horizontal index of the pixel
     * @param i  vertical index of the pixel
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;
        double yi = -(i - (nY - 1) / 2d) * rY;
        double xj = (j - (nX - 1) / 2d) * rX;
        Point pIJ = pC;
        if (!isZero(xj)) pIJ = pIJ.add(vRight.scale(xj));
        if (!isZero(yi)) pIJ = pIJ.add(vUp.scale(yi));
        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }

    /**
     * The {@code Builder} class is used to construct instances of the {@code Camera} class.
     */
    public static class Builder {
        /**
         * A {@link Camera} instance initialized with default settings.
         */
        private final Camera camera = new Camera();

        /**
         * set the location of the camera
         *
         * @param location the location of the camera
         * @return the current {@code Builder} instance
         */
        public Builder setLocation(Point location) {
            camera.p0 = location;
            return this;
        }

        /**
         * set direction of the camera
         *
         * @param to the forward direction vector
         * @param up the up direction vector
         * @return the current {@code Builder} instance
         * @throws IllegalArgumentException if the direction vectors are not orthogonal
         */
        public Builder setDirection(Vector to, Vector up) {
            if (isZero(to.dotProduct(up))) {
                camera.vTo = to.normalize();
                camera.vUp = up.normalize();
                return this;
            }
            throw new IllegalArgumentException("the vector are not orthogonal");
        }

        /**
         * set view plane size
         *
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return the current {@code Builder} instance
         */
        public Builder setVpSize(Double width, Double height) {
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * the distance to the view plane
         *
         * @param distance the distance to the view plane
         * @return the current {@code Builder} instance
         */
        public Builder setVpDistance(Double distance) {
            camera.distance = distance;
            return this;
        }

        /**
         * Sets the ray tracer to be used by the camera.
         *
         * @param rayTracerBase the {@code RayTracerBase} instance to be used by the camera
         * @return the current {@code Builder} instance
         */
        public Builder setRayTracer(RayTracerBase rayTracerBase) {
            this.camera.rayTracer = rayTracerBase;
            return this;
        }

        /**
         * Sets the image writer to be used by the camera.
         *
         * @param imageWriter the {@code ImageWriter} instance to be used by the camera
         * @return the current {@code Builder} instance
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            this.camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Builds and returns a {@code Camera} instance.
         *
         * @return the constructed {@code Camera} instance
         * @throws MissingResourceException if required parameters are missing
         * @throws IllegalArgumentException if dimensions or distance are non-positive
         */
        public Camera build() {
            String errorMessage = "Missing data for rendering";
            if (camera.imageWriter == null) throw new MissingResourceException(errorMessage, "Camera", "p0");
            if (camera.rayTracer == null) throw new MissingResourceException(errorMessage, "Camera", "rayTracer");
            if (camera.p0 == null) throw new MissingResourceException(errorMessage, "Camera", "p0");
            if (camera.vUp == null) throw new MissingResourceException(errorMessage, "Camera", "vUp");
            if (camera.vTo == null) throw new MissingResourceException(errorMessage, "Camera", "vTo");
            if (alignZero(camera.width) <= 0) throw new IllegalArgumentException("width can't be negative or 0");
            if (alignZero(camera.height) <= 0) throw new IllegalArgumentException("height can't be negative or 0");
            if (alignZero(camera.distance) <= 0)
                throw new IllegalArgumentException("distance can't be negative or 0");
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            if (!isZero(camera.vRight.dotProduct(camera.vTo)))
                throw new IllegalArgumentException("wrong values in vRight");
            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                return null;
            }
        }

    }

    /**
     * Renders the image by casting rays through each pixel and setting the color of each pixel accordingly.
     *
     * @return the current {@code Camera} instance
     * @throws UnsupportedOperationException if the image writer or ray tracer is not set
     */
    public Camera renderImage() throws UnsupportedOperationException {
        int nX = this.imageWriter.getNx();
        int nY = this.imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                this.castRay(nY, nX, j, i);
            }
        }
        return this;
    }

    /**
     * Draws a grid on the image with the specified interval and color.
     *
     * @param interval the interval between grid lines
     * @param color    the color of the grid lines
     * @return the current {@code Camera} instance
     */
    public Camera printGrid(int interval, Color color) {
        int nY = this.imageWriter.getNy();
        int nX = this.imageWriter.getNx();
        for (int i = 0; i < nX; i += interval) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(i, j, color);
            }
        }
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j += interval) {
                imageWriter.writePixel(i, j, color);
            }
        }
        return this;
    }

    /**
     * Writes the rendered image to a file.
     */
    public void writeToImage() {
        this.imageWriter.writeToImage();
    }

    /**
     * Casts a ray through the specified pixel and sets the pixel's color based on the result of the ray tracing.
     *
     * @param nX the number of pixels in the X direction
     * @param nY the number of pixels in the Y direction
     * @param i  the row index of the pixel
     * @param j  the column index of the pixel
     */
    private void castRay(int nX, int nY, int i, int j) {
        Ray ray = constructRay(nX, nY, j, i);
        Color color = this.rayTracer.traceRay(ray);
        this.imageWriter.writePixel(j, i, color);
    }
}

