# **DSA_21_group3**

### **File System Implementation with B+Tree**

**Members.**

- Ruixi Li 
  - NetID: rl813
  - Email: rl813@scarletmail.rutgers.edu
- Zhihao Bai 
  - NetID: zb122
  - Email: zb122@scarletmail.rutgers.edu![img](https://mail.google.com/mail/u/1/images/cleardot.gif)

**Description.**

When reading the file, the file uses `countingInputStream` from the `Common.google.api` library. The dependency is manage by maven. The testing starts from the main function in main.java.

```java
private static final String BASEPATH = "/Users/.....";
int data_size =  4096;//data size in unit of Kilobytes
int order = 8; //value of degree M
bplus_Tree tree = new bplus_Tree(order);
fileDataUtils r = new fileDataUtils();
System.out.println("Results of B+ tree when m = [%d] when data size is [%d]".formatted(order,data_size));
r.readFile(tree, 4, String.valueOf(data_size*1024)); // 4 is block size
traverse(tree);
Export(tree, tree.getHead());
r.deleteTree(tree,4, String.valueOf(data_size*1024));
traverse(tree);
Node head = Import();
Node root = findRoot(head);
bplus_Tree new_tree = new bplus_Tree(order);
new_tree.setRoot(root);
new_tree.setHead(head);
traverse(new_tree);
```

The variable `BASEPATH` located in different program needs to change correspondingly.

