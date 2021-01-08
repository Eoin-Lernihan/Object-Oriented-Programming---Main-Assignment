package ie.gmit.sw;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class DiretoryReader {

  private BlockingQueue<FileWithData> qfileWithDatas;

  public DiretoryReader(BlockingQueue<FileWithData> qfileWithDatas) {
    this.qfileWithDatas = qfileWithDatas;
  }
  
  /**
   * get all the files and returns them.
   * 
   * @param folder It is the directory where the files are
   * @return  returns a blocking queue with all the files in it
   */
  
  public BlockingQueue<FileWithData> listfilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
      if (!fileEntry.isDirectory()) {
        FileWithData fileWithData = new FileWithData();
        String pathName = folder.getPath() + "\\" + fileEntry.getName();
        System.out.println(pathName);
        fileWithData.setPathName(pathName);
        fileWithData.setFileName(fileEntry.getName());
        try {
          qfileWithDatas.put(fileWithData);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    return qfileWithDatas;
  }
}
