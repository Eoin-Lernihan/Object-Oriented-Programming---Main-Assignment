package ie.gmit.sw;

public interface FilterInterface {

  /**
   * applies filter.
   *
   * @param pixel pixels
   * @param x x cor
   * @param y y cor
   * @return
   */
  int apply(int pixel, int x, int y);
}