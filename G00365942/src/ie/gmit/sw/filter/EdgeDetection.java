package ie.gmit.sw.filter;


public class EdgeDetection extends SuperApply {
  int[][] filter = {{1, 0, -1}, {0, 0, 0}, {-1, 0, 1}};
  
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
