package ie.gmit.sw.filter;

public class Filler implements FilteringInterface {

  @Override
  public void processImage(String pathName, String outputPathName) {
    System.out.println("Filler " + pathName + " " + outputPathName);
  }
}
