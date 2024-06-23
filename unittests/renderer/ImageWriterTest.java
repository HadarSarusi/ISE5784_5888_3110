package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

/**
 * The {@code ImageWriterTest} class contains unit tests for the {@code ImageWriter} class.
 */
class ImageWriterTest {

    /**
     * Tests the functionality of the {@code ImageWriter} class by creating an image,
     * filling it with a specific color, and drawing a grid on it.
     */
    @Test
    void imageWriterTest() {
        final ImageWriter imageWriter = new ImageWriter("imageWriterTest", 800, 500);
        final Color background = new Color(255, 192, 203);
        final Color color = new Color(0, 0, 0);
        final int nX = 800;
        final int nY = 500;


        // Fill the image with the specified background
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(i, j, background);
            }
        }

        // Draw vertical grid lines
        for (int i = 0; i < nX; i += 50) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(i, j, color);
            }
        }

        // Draw horizontal grid lines
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j += 50) {
                imageWriter.writePixel(i, j, color);
            }
        }

        // Write the image to a file
        imageWriter.writeToImage();
    }
}
