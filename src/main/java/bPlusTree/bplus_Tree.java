package bPlusTree;

import BPlusTree.utils.fileSystemFunc;

import java.io.Serializable;
import java.util.Map;

public class bplus_Tree implements fileSystemFunc, Serializable {
    protected Node head;
    protected Node root;
    protected int M;

    public bplus_Tree(int order){
        if(order <=2){
            System.out.print("Minimum value of M is 2");
            System.exit(0);
        }
        this.M = order;
        root = new Node(true,true);
        head = root;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node BPlusTree(int M){
        return null;
    }

    @Override
    public Object search(Comparable key) {
        return root.search(key);
    }

    @Override
    public void remove(Comparable key) {
        root.remove(key, this);
    }

    @Override
    public void add(Comparable key, Object obj) {
        root.create(key, obj, this);
//        System.out.println("Added:"+key+obj.toString());
    }

    @Override
    public void update(Comparable key, Object obj) {
        root.create(key, obj,this);
    }

    @Override
    public void traverse(Comparable key1, Comparable key2) {
        head.traverse( key1, key2);
    }

    public void treeTraversal() {
        StringBuilder str = new StringBuilder();
        inorderTraversal(root,str);
        System.out.println(str.toString());

    }
    public void inorderTraversal(Node root, StringBuilder str){
        if (root == null){
            return;
        }
        if(root.isLeaf != true){
            for( int i = 0; i<root.children.size();i++){

                inorderTraversal(root.children.get(i),str);
            }
        }
        else{
            for( int i = 0; i<root.entries.size();i++){
               str.append(root.entries.get(i).getValue());
               str.append(" ");
            }
            str.append("- ");
        }
//        for( int i = 0; i<root.entries.size();i++){
//            System.out.println(root.entries.get(i));
//        }
    }
}
