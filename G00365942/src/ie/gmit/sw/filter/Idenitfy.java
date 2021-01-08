package ie.gmit.sw.filter;

public class Idenitfy extends SuperApply {
  int[][] filter = {{0, 0, 0}, {0, 1, 0},  {0, 0, 0}};
  
  @Override
  protected int filterValue(int row, int col) {
    return filter[row][col];
  }
  
  @Override
  protected int filterWitdth(int row) {
    return filter[row].length;
  }
  
  @Override
  protected int filterLenght() {
    return filter.length;
  }
}
