package ie.gmit.sw.filter;

import java.awt.image.BufferedImage;

public class Bit32Convertor extends AbtractImageProccessor {

  @Override 
  protected int apply(BufferedImage image, int pixel, int x, int y) {
    // We can get the RGB colour channels out of a 32-bit int as follows:
    int red = (pixel >> 16) & 0xff;
    int green = (pixel >> 8) & 0xff;
    int blue = pixel & 0xff;
    // We can re-create a 32-bit RGB pixel from the channels as follows
    int rgb = 0;
    rgb = pixel | (red << 16);
    rgb = pixel | (green << 8);
    rgb = pixel | blue;
    return rgb;
  }
}
