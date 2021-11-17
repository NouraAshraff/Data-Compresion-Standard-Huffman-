

import java.io.FileWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


//sort nodes accending order
class CompareNode implements Comparator<HuffmanNode> {
  public int compare(HuffmanNode x, HuffmanNode y) {
    return x.count - y.count;
  }
}

public class HuffmanCompression {
  static Map<Character, String> arrNode = new HashMap<Character, String>();

  public static void printInorder(HuffmanNode node, String code) {

    if (node == null)
      return;

    /* first recur on left child */
    printInorder(node.left, code + "0");

    /* then print the data of HuffmanNode */
    if ((node.c) != '0') {

      node.code = code;
      try {
        FileWriter fw = new FileWriter(System.getProperty("user.home") + "\\Desktop"+"Dictionary.txt", true);
        System.out.println(System.getProperty("user.home") + "\\Desktop"+"Dictionary.txt");
        if ((node.c) == ' ') {
          fw.write(node.code + " " + "(space character encoding)"+ ", ");  
        }else{

          fw.write(node.c + " " + node.code + ", ");
        }
        fw.close();
      } catch (Exception e) {
        System.out.println(e);
      }
      System.out.println(node.c + " " + node.code);
      arrNode.put(node.c, node.code);

    }
    /* now recur on right child */
    printInorder(node.right, code + "1");
  }

  public void compress(String data) {
    System.out.println(data);

    int dataSize = data.length();
    Map<Character, Integer> freqMap = new HashMap<>(); // to get frequency
    for (int i = 0; i < dataSize; i++) {
      if (freqMap.containsKey(data.charAt(i))) {
        freqMap.put(data.charAt(i), freqMap.get(data.charAt(i)) + 1);
      } else {
        freqMap.put(data.charAt(i), 1);
      }
    }

    System.out.println("print the frq map");
    // print the freq map
    for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }

    // store data in priority queue
    PriorityQueue<HuffmanNode> HuffmanTree = new PriorityQueue<HuffmanNode>(freqMap.size(), new CompareNode());
    for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
      HuffmanNode node = new HuffmanNode();
      // System.out.println(entry.getKey() + " " + entry.getValue());
      node.c = (Character) entry.getKey();
      node.count = (Integer) entry.getValue();
      node.left = null;
      node.right = null;
      node.code = "";
      HuffmanTree.add(node);

    }
    System.out.println("hi");

    // operation on the HuffmanTree to sum the nodes and put the data only on the
    // leaves

    HuffmanNode root = null;
    while (HuffmanTree.size() > 1) {
      // the min two nodes
      HuffmanNode x = HuffmanTree.peek();
      HuffmanTree.poll();
      HuffmanNode y = HuffmanTree.peek();
      HuffmanTree.poll();

      HuffmanNode newNode = new HuffmanNode(); // new node to pick the sum
      newNode.count = x.count + y.count;
      newNode.c = '0';
      newNode.code = "";
      newNode.left = x;
      newNode.right = y;
      root = newNode;

      HuffmanTree.add(newNode);
    }

    // System.out.println("char "+root.c + " count "+ root.count );

    // printCode(root, "");
    printInorder(root, "");
    System.out.println("bye");

    System.out.println("print the compressed map");
    for (Map.Entry<Character, String> entry : arrNode.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
    System.out.println(data);

    for (int i = 0; i < dataSize; i++) {
      System.out.print(arrNode.get(data.charAt(i)));
      try {
        FileWriter fw = new FileWriter(System.getProperty("user.home") + "\\Desktop"+"CompressedStr.txt", true);
        fw.write(arrNode.get(data.charAt(i)));
        fw.close();
      } catch (Exception e) {
        System.out.println(e);
      }

    }

  }

}
