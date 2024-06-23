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
        final Color color = new Color(255, 192, 203);

        // Fill the image with the specified color
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                imageWriter.writePixel(i, j, color);
            }
        }

        // Draw vertical grid lines
        for (int i = 0; i < 800; i += 50) {
            for (int j = 0; j < 500; j++) {
                imageWriter.writePixel(i, j, Color.BLACK);
            }
        }

        // Draw horizontal grid lines
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j += 50) {
                imageWriter.writePixel(i, j, Color.BLACK);
            }
        }

        // Write the image to a file
        imageWriter.writeToImage();
    }
}
