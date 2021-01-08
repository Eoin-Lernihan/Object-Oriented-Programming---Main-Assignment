package ie.gmit.sw.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class AbtractImageProccessor implements FilteringInterface {

  public AbtractImageProccessor() {
    super();
  }

  @Override
  public void processImage(String inputFilePath, String outputFilePath) {
    // Read in an image and convert to a BufferedImage

    BufferedImage image = null;
    try {
      File input = new File(inputFilePath);
      image = ImageIO.read(input);
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(image); // This writes out a lot of useful meta-data about the image.

    for (int y = 0; y < image.getHeight(); y++) { // Loop over the 2D image pixel-by-pixel
      for (int x = 0; x < image.getWidth(); x++) {
        int pixel = image.getRGB(x, y); // Get the pixel at an (x, y) coordinate
        int transformedPixel = apply(image, pixel, x, y);
        image.setRGB(x, y, transformedPixel); // Set the pixel to be transformed at (x, y)
      }
    }
    try {
      ImageIO.write(image, "png", new File(outputFilePath));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  protected abstract int apply(BufferedImage image, int pixel, int x, int y);
}
