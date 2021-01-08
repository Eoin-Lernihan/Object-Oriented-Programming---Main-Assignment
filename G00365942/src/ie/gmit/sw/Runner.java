package ie.gmit.sw;


import ie.gmit.sw.filter.Bit32Convertor;
import ie.gmit.sw.filter.EdgeDetection;
import ie.gmit.sw.filter.FilteringInterface;
import ie.gmit.sw.filter.Idenitfy;
import ie.gmit.sw.filter.SobelVertical;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Runner {
  static Scanner input = new Scanner(System.in);
  /**
   * opens an image file and save stats.
   *
   * @param args yar
   */
  
  public static void main(String[] args) {
    String option = "6";
    BlockingQueue<FileWithData> listOfFiles = null;
    BlockingQueue<FileWithData> qfileWithDatas = new ArrayBlockingQueue<FileWithData>(100);
    String folderName = "";

    int customFilter = 1;
    boolean canProcess = false;
    do {
      System.out.println(
          "Please enter what you would like to do \n"
              + "1.Enter Image Directory\n"
              + "2.Select a certain image\n"
              + "3.Add a custom filter\n"
              + "4.Process all images now\n"
              + "5.Exit(but you wouldn't do that, right?)\n");
      option = input.nextLine();
      if (option.equalsIgnoreCase("1")) {
        canProcess = false;

        DiretoryReader readFilesInFolder = new DiretoryReader(qfileWithDatas);
        System.out.println("Please type the file name");
        folderName = input.nextLine();
        final File folder = new File(folderName);
        listOfFiles = readFilesInFolder.listfilesForFolder(folder);
        canProcess = true;

      } else if (option.equalsIgnoreCase("2")) {
        canProcess = false;
        System.out.println("Please type the png name");
        String fileName = input.nextLine();
        File tempFile = new File(fileName);
        if (tempFile.exists()) {
          listOfFiles = createQueueForAnImage(tempFile);
          folderName = tempFile.getParent();
          canProcess = true;

        } else {
          System.out.println("file does not exists");
        }

      } else if (option.equalsIgnoreCase("3")) {
        System.out.println(
            "Current Kernel Filter are\n"
                + "1.Default(32 bit convertor)\n"
                + "2.Edgy detection\n"
                + "3.Identity\n"
                + "4.Sobel Vertical\n");

        customFilter = Integer.valueOf(input.nextLine());

      } else if (option.equalsIgnoreCase("4")) {
        if (canProcess) {
          System.out.println("Filtering all images");

          List<Thread> threads = startAllThreads(listOfFiles, folderName, customFilter);

          waitForAllThreads(threads);
        } else {
          System.out.println("Need an image to start");
        }

      } else if (option.equalsIgnoreCase("5")) {
        System.out.println("Have a nice day :{");
      } else {
        System.out.println(
            "Error 404, Not a possible option and I my existance of having feelings");
      }

    } while (!option.equalsIgnoreCase("5"));
  }

  private static BlockingQueue<FileWithData> createQueueForAnImage(File tempFile) {
    BlockingQueue<FileWithData> listOfFiles;
    FileWithData fileWithData = new FileWithData();
    fileWithData.setFileName(tempFile.getName());
    fileWithData.setPathName(tempFile.getPath());
    listOfFiles = new ArrayBlockingQueue<>(1);
    try {
      listOfFiles.put(fileWithData);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return listOfFiles;
  }
  /**
   * Waits for all treads to finish before continuning.
   *
   * @param threads list of threads that we are waiting for.
   */
  
  private static void waitForAllThreads(List<Thread> threads) {
    for (Thread thread : threads) {
      try {
        thread.join();

      } catch (InterruptedException e) { 
        e.printStackTrace();
      }
    }
  }
  /**
   * Starts all possible treads.
   *
   * @param listOfFiles list of files to process.
   * @param folderName Floder name of where out put goes.
   * @return list of threads.
   */
  
  private static List<Thread> startAllThreads(
      BlockingQueue<FileWithData> listOfFiles, String folderName, int customFilterChoice) {
    int num = 3;
    List<Thread> threads = new ArrayList<>();

    for (int i = 0; i < num; i++) {
      // default one
      FilteringInterface customFilter = new Bit32Convertor();

      if (customFilterChoice == 2) {
        customFilter = new EdgeDetection();
      }
      if (customFilterChoice == 3) {
        customFilter = new Idenitfy();
      }
      if (customFilterChoice == 4) {
        customFilter = new SobelVertical();
      }
      ThreadForImageProcessing imageProcess =
          new ThreadForImageProcessing(listOfFiles, folderName, customFilter);
      threads.add(new Thread(imageProcess));
    }
    for (Thread thread : threads) {
      thread.start();
    }
    return threads;
  }
}
