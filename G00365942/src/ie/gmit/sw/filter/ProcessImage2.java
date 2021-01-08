package ie.gmit.sw.filter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcessImage2 implements FilteringInterface {

  @Override
  public void processImage(String pathname, String pathname2) {
    // Read in an image and convert to a BufferedImage

    BufferedImage image = null;
    try {
      //  String pathname = "C:\\data\\picture.png";
      File input = new File(pathname);
      image = ImageIO.read(input);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println(image); // This writes out a lot of useful meta-data about the image.
    ;
    for (int y = 0; y < image.getHeight(); y++) { // Loop over the 2D image pixel-by-pixel
      for (int x = 0; x < image.getWidth(); x++) {
        int pixel = image.getRGB(x, y); // Get the pixel at an (x, y) coordinate
        int transformedPixel= apply(pixel, x, y);
        image.setRGB(x, y, transformedPixel); // Set the pixel to be transformed at (x, y)
      }
    }
    try {
      // String pathname2 = "C:\\data\\out.png";
      ImageIO.write(image, "png", new File(pathname2));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public int apply(int pixel, int x, int y) {
      int element = pixel;
      int[][] filter = null;

      for (int row = 0; row < filter.length; row++) {
        for (int col = 0; col < filter[row].length; col++) {
          try {
            // This will cause an exception if we overrun the edges of the image
            // element = image.getRGB(x + col, y + row);
            // Do lots of cool stuff here…. Maybe use some of the code above…
          } catch (Exception e) {
            continue; // Ignore any exception and keep going. It’s good enough J
          }
        }
      }
      return element;
    }
  
}
