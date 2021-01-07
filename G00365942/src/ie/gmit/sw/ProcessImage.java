package ie.gmit.sw;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProcessImage implements FilteringInterface {

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
        //  image.setRGB(x, y, 0x00FF0000); // Set the pixel colour at (x, y) to red
        // We can get the RGB colour channels out of a 32-bit int as follows:
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        // We can re-create a 32-bit RGB pixel from the channels as follows;
        int rgb = 0;
        rgb = pixel | (red << 16);
        rgb = pixel | (green << 8);
        rgb = pixel | blue;
        image.setRGB(x, y, rgb); // Set the pixel colour at (x, y) to red
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
}
