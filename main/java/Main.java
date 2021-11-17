
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

  public static void main (String[] args) {
    String data = "";
    try {
      File file = new File("src/input.txt");
      Scanner myReader = new Scanner(file);
      while (myReader.hasNextLine()) {
        data += myReader.nextLine();
      }
      myReader.close();

    } 
    catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  
    HuffmanCompression com =new HuffmanCompression();
    com.compress(data);
    HuffmanDecompression dcom =new HuffmanDecompression();
    dcom.decompress();
  }

  
}