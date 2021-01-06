package imageFiltering;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import static java.lang.System.out;
import java.io.*;
import java.util.Scanner;
import java.io.ObjectInputStream;

public class Runner {
  static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    String option = "6";
    do {
      System.out.println(
          "Please enter what you would like to do \n"
              + "1.Enter Image Directory\n"
              + "2.Select a certain image\n"
              + "3.add a custom filter\n"
              + "4.Exit(but you wouldn't do that, right?)\n");
      option = input.nextLine();
      if (option.equalsIgnoreCase("1")) {
        System.out.println("");
      } else if (option.equalsIgnoreCase("2")) {
        System.out.println("");
      } else if (option.equalsIgnoreCase("3")) {
        String customFilter = input.nextLine();
        System.out.println(customFilter);
      } else if (option.equalsIgnoreCase("4")) {
        System.out.println("Have a nice day :{");
      } else {
        System.out.println(
            "Error 404, Not a possible option and I my existance of having feelings/n");
      }

    } while (!option.equalsIgnoreCase("4"));
    Runner r = new Runner();
    r.name();
  }
/**
 * 
 * @param pixel
 * @param x
 * @param y
 * @return
 */
  public int apply(int pixel, int x, int y) {
    int element = pixel;
    int filter[][] = null;

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

  public void name() {
    // Read in an image and convert to a BufferedImage

    BufferedImage image = null;
    try {
      File input = new File("C:\\data\\picture.png");
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
        image.setRGB(x, y, 0x00FF0000); // Set the pixel colour at (x, y) to red
        // We can get the RGB colour channels out of a 32-bit int as follows:
        int red = (pixel >> 16) & 0xff;
        int green = (pixel >> 8) & 0xff;
        int blue = pixel & 0xff;
        // We can re-create a 32-bit RGB pixel from the channels as follows;
        int rgb = 0;
        rgb = rgb | (red << 16);
        rgb = rgb | (green << 8);
        rgb = rgb | blue;
      }
    }
    try {
      ImageIO.write(image, "png", new File("C:\\data\\out.png"));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
