import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanDecompression {
    public  void decompress() {
        try {
            File Dictionary = new File(System.getProperty("user.home") + "\\Desktop"+"Dictionary.txt");
            File CompressedStr = new File(System.getProperty("user.home") + "\\Desktop"+"CompressedStr.txt");
            HashMap<String, String> map = new HashMap<>();
            Scanner myReader = new Scanner(Dictionary);
            //turn the dictionary into map.
            while (myReader.hasNextLine()) {
                String data = myReader.useDelimiter(", ").next();
                String[] chr= data.split(" ");
                //condition if there an endl between the char and it code.
                if(chr.length == 1){
                    String[] chr1=chr[0].split("\n");
                    if(chr1[0].charAt(0)=='0'||chr1[0].charAt(0)=='1'){
                        map.put(" ",chr1[0]);
                    }else {
                    map.put(chr[0].substring(0,1),chr1[1]);}
                } else if (chr.length>1) {

                    //System.out.println(chr[0] + " " + chr[1] + "\n");
                    if(chr[0].charAt(0)=='0'||chr[0].charAt(0)=='1'){
                        map.put(" ",chr[0]);
                    }else {
                        map.put(chr[0],chr[1]);}
                }

            }
            Scanner Reader=new Scanner(CompressedStr);
            String Compressed="";
            String Result="";
            while(Reader.hasNextLine()){
                Compressed+=Reader.nextLine();
            }
            while(Compressed.length() > 0)
            {
                    for (Map.Entry<String, String> entry : map.entrySet()){
                        if(Compressed.startsWith(entry.getValue()))
                         {
                        Result+=entry.getKey();
                        Compressed = Compressed.substring(entry.getValue().length());
                        break;
                            }
                    }

            }
            //print the result in the output file
            File Output = new File(System.getProperty("user.home") + "\\Desktop"+"Output.txt");
            Output.createNewFile();
            FileWriter Writer=new FileWriter("Output.txt");
            Writer.write(Result);
            myReader.close();
            Writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}