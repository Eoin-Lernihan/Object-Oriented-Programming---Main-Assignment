package ie.gmit.sw.filter;

import java.awt.image.BufferedImage;

public abstract class SuperApply extends AbtractImageProccessor {

  public SuperApply() {
    super();
  }

public int apply(BufferedImage image,int pixel,int x,int y){
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
          continue; // Ignore any exception and keep going. It’s good enough J
        }
      }
    }
    return newPixelValue;
  }

protected abstract int filterValue(int row, int col);

protected abstract int filterWitdth(int row) ;

protected abstract int filterLenght();
}