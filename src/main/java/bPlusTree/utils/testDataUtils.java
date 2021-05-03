package bPlusTree.utils;

import bPlusTree.Node;
import bPlusTree.bplus_Tree;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.google.common.io.*;

public class testDataUtils {
    private static final String BASEPATH = "/Users/ruixi/Documents/B+Tree/tree_structure_saves/";

    /**
     * 添加测试数据
     * @param tree
     */
    public static void addTestData(bplus_Tree tree, int size) {
        Random random = new Random();
        int count = 0;
        long current = System.currentTimeMillis();
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < 100; i++) {
                int randomNumber = random.nextInt(1000);
                count++;
                tree.add(randomNumber, randomNumber);
            }
        }
        long duration = System.currentTimeMillis() - current;
        System.out.println(String.format("Inserted %s" ,(count)));
        System.out.println("time elapsed for duration: " + duration);
    }

    public static void Export(bplus_Tree tree, Node head) throws IOException, FileNotFoundException {
        File file = new File(BASEPATH+String.valueOf(1) + ".txt");
        head.setFile(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(head);
        objectOutputStream.close();
    }

    /**
     * 将 B+ 树和叶子节点组成的链表通过序列化从内存存储到磁盘上
     * @param tree
     * @throws IOException
     * @throws FileNotFoundException
     */
    private static Node WriteToFile(bplus_Tree tree, Node temp, int ct) throws IOException, FileNotFoundException {
        int count = 0;
        Node head = temp;
            ++count;
            //List<Map.Entry<Comparable, Object>> entries = head.getEntries();
            File file = new File(BASEPATH+String.valueOf(count) + ".txt");
            head.setFile(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(head);
            objectOutputStream.close();

            ct--;
            if(ct ==0){
                return head;
            }
            else {
                head = head.getNext();
            }
        return head;

    }
    public static Node Import() throws IOException, FileNotFoundException, ClassNotFoundException {

        int count = 0;
        Node newnode =null;
            File file = new File(BASEPATH+String.valueOf(1) + ".txt");
            if(file.isFile()){
                FileInputStream fileIn = new FileInputStream(file);
                ObjectInputStream objIn = new ObjectInputStream(fileIn);
                newnode = (Node)objIn.readObject();
                //System.out.println(newnode.getParent().toString());
            }

        return  newnode;
    }

    public static Node findRoot(Node head ){
        Node temp = head;
        while(temp.getParent()!= null){
            temp = temp.getParent();
        }
        return temp;
    }

}
