package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import ie.gmit.sw.filter.FilteringInterface;

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
      System.out.println("");
    } catch (Exception e) { // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  void consume(FileWithData fileList) throws Exception {
    String imagePath = fileList.getPathName();
    String imagePath2 = folderName + "\\filtered" + fileList.getFileName();

    filter.processImage(imagePath, imagePath2);
  }
}
