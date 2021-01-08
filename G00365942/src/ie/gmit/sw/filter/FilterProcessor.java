package ie.gmit.sw.filter;

public class FilterProcessor implements FilterInterface {
  /**
   * applies filter.
   *
   * @param pixel pixels
   * @param x x cor
   * @param y y cor
   * @return
   */
  @Override
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
