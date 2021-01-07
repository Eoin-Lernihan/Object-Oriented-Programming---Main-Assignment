package ie.gmit.sw;

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
  
  /**
   *It's runs the tread. 
   */
  
  public void run() {
    try {
      while (true) {
        consume(queue.poll(5, TimeUnit.SECONDS));
      }
    } catch (InterruptedException ex) {
      System.out.println("");
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void consume(FileWithData fileList) throws Exception {
    if (fileList == null) {
      throw new Exception("File is empty, like my wallet");
    }
    String imagePath = fileList.getPathName();
    String imagePath2 = folderName + "\\filtered" + fileList.getFileName();

    filter.processImage(imagePath, imagePath2);
  }
}
