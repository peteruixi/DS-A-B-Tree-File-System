package bPlusTree;

import bPlusTree.utils.fileDataUtils;
import bPlusTree.utils.testDataUtils;

import java.io.IOException;

public class main extends testDataUtils {

    private static final String BASEPATH = "/Users/ruixi/Documents/B+Tree/src/test_data/";


    public static void main(String[] args) throws IOException, ClassNotFoundException {

//        int data_size = 8192;
//        int order =32;
//        for(int i =4; i < 19; i++){
//            for(int j = 2; j<= 5;j ++){
                int data_size =  262144;//4*(int)Math.pow(2,i);
                int order = 32;//(int)Math.pow(2,j);



                bplus_Tree tree = new bplus_Tree(order);
                fileDataUtils r = new fileDataUtils();
                System.out.println("Results of B+ tree when m = [%d] when data size is [%d]".formatted(order,data_size));
                r.readFile(tree,4, String.valueOf(data_size*1000));
        Export(tree, tree.getHead());
                traverse(tree);
                r.searchTree(tree,4, String.valueOf(data_size*1000));
                r.deleteTree(tree,4, String.valueOf(data_size*1000));
//                traverse(tree);
//
//            }


//        }

//        tree.add(1,1);
//        tree.add(2,2);
//        tree.add(3,3);
//        tree.add(4,4);
//        tree.add(5,5);
//        traverse(tree);
//        tree.remove(1);
//        tree.remove(2);
//        tree.remove(3);
//        tree.remove(4);
//        tree.remove(5);
//        traverse(tree);

//        addTestData(tree,1000);
//        traverse(tree);
//        traverse(tree);
//        Export(tree, tree.getHead());

//        Node head = Import();
//        Node root = findRoot(head);
//        tree.setHead(head);
//        tree.setRoot(root);



        //traverse(tree);
        //tree.treeTraversal();

    }




    /**
     * 查询一个 B+ 树上的节点，并返回叶子节点链表的头节点
     * @param tree
     * @return
     */
    private static Node findOneNode(bplus_Tree tree) {
        int search = 80;
        System.out.print(tree.search(search));
        Node head = tree.getHead();
        return head;
    }




    public static void traverse( bplus_Tree tree){
        int count = 0;
        Node head = tree.getHead();
        StringBuilder str = new StringBuilder();
        while(head!=null){

            for( int i = 0; i<head.getEntries().size();i++){
                int b = (int)head.getEntries().get(i).getValue();
//                str.append(head.getEntries().get(i).getValue());
//                str.append(Integer.toBinaryString((b & 0xFF) + 0x100).substring(1));
                str.append(Integer.toBinaryString(b));
                str.append(" ");
                //System.out.println(head.getEntries().get(i).getValue());
            }
            count++;
            if(head.next!= null && head.getParent().equals(head.next.getParent())){
                str.append(" | ");
            }
            else{
                str.append("- ");
            }
            head = head.getNext();
        }
       //System.out.println(str.toString());
        System.out.println("number of nodes:"+count);
    }
}