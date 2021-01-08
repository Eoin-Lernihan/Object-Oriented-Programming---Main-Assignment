package ie.gmit.sw.filter;

import java.awt.image.BufferedImage;

public abstract class SuperApply extends AbtractImageProccessor {

  public SuperApply() {
    super();
  }
  /**
   * This method applies the matrix to the pixel.
   *
   * @param image The image that is given.
   * @param pixel The current pixel that needs to tranformed.
   * @param x The x location of the pixel.
   * @param y The y location of the pixel.
   * @return the transformed pixel
   */
  
  public int apply(BufferedImage image, int pixel, int x, int y) {
    int element = pixel;

    int newPixelValue = 0;
    for (int row = 0; row < filterLenght(); row++) {
      for (int col = 0; col < filterWitdth(row); col++) {
        try {
          int relativeCol = x + (col);
          int relativeRow = y + (row);
          if (relativeRow < image.getHeight() && relativeCol < image.getWidth()) {
            element = image.getRGB(relativeCol, relativeRow);
            newPixelValue = newPixelValue + (element * filterValue(row, col));
          }
        } catch (Exception e) {
          continue; // Ignore any exception and keep going.
        }
      }
    }
    return newPixelValue;
  }

  /**
   * Returns the matrix value given the row and col.
   *
   * @param row The row on the matrix.
   * @param col The col on the matrix.
   * @return returns an int of the value.
   */
  
  protected abstract int filterValue(int row, int col);

  /**
   * Returns the number cols in this row.
   *
   * @param row The row on the matrix.
   * @return returns an int of cols.
   */
  
  protected abstract int filterWitdth(int row);
  /**
   * Finds the number of row.
   *
   * @return returns the number of row.
   */
  
  protected abstract int filterLenght();
}
