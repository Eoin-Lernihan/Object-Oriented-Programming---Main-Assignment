package ie.gmit.sw;

import ie.gmit.sw.filter.FilteringInterface;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadForImageProcessing implements Runnable {
  private final BlockingQueue<FileWithData> queue;
  private String folderName;
  private FilteringInterface filter;

  ThreadForImageProcessing(BlockingQueue<FileWithData> q, String folderName, FilteringInterface r) {
    queue = q;
    this.folderName = folderName;
    this.filter = r;
  }

  /** It's runs the tread. */
  
  public void run() {
    try {
      boolean queueIsEmpty = false;
      while (!queueIsEmpty) {
        FileWithData poll = queue.poll(5, TimeUnit.SECONDS);
        if (poll == null) {
          queueIsEmpty = true;
        } else {
          consume(poll);
        }
      }
    } catch (InterruptedException ex) {
      // nothing to do here or see Mr cop
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  /**
   * Creates a data to store file path name and name of file.
   *
   * @param fileToProcess It's the file that has to be processed.
   * @throws Exception out of the ordinary.
   */
  
  void consume(FileWithData fileToProcess) throws Exception {
    String imagePath = fileToProcess.getPathName();
    String imagePath2 = folderName + "\\filtered" + fileToProcess.getFileName();

    filter.processImage(imagePath, imagePath2);
  }
}
