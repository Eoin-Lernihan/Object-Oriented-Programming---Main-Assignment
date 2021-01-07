package ie.gmit.sw;

public class Filler implements FilteringInterface {

  @Override
  public void processImage(String pathName, String outputPathName) {
    System.out.println("Filler " + pathName + " " + outputPathName);
  }
}
