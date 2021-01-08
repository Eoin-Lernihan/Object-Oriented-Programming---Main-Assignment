package ie.gmit.sw.filter;
/**
 * Interface for all filters.
 * @author eoinb
 *
 */

public interface FilteringInterface {

  /**
   * It processes an image given a path and creates an image in the give output location.
   *
   * @param inputFilePath Provides an inputed file path.
   * @param outputFilePath Provides the path to write the path to
   */
    
  void processImage(String inputFilePath, String outputFilePath);
}
