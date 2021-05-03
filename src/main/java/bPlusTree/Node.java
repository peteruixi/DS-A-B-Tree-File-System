package bPlusTree;

import java.io.File;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Node implements Serializable{
    protected boolean isLeaf;
    protected boolean isRoot;
    protected Node parent;
    protected Node previous;
    protected Node next;
    protected List<Node> children;
    protected List<Map.Entry<Comparable, Object>> entries;


    protected File file;

    public Node(boolean isLeaf){
        this.isLeaf = isLeaf;
        entries = new ArrayList<Map.Entry<Comparable, Object>>();
        if (!isLeaf){
            children = new ArrayList<Node>();
        }
    }

    public Node(boolean isLeaf, boolean isRoot){
        this(isLeaf);
        this.isRoot = isRoot;
    }



    public Object search(Comparable key) {

        if(isLeaf){
            for(Map.Entry<Comparable, Object> entry: entries){
                if(entry.getKey().compareTo(key)==0){
                   //  System.out.println("entry value "+entry.getValue());
                    return  entry.getValue();
                }
            }
            return null;
        }
        else{
            if(key.compareTo(entries.get(0).getKey())<=0){
                return children.get(0).search(key);
            }
            else if(key.compareTo(entries.get(entries.size()-1).getKey())>=0){
                return children.get(entries.size()-1).search(key);
            }
            else{
                for(int i = 0; i<entries.size();i++){
                    if(entries.get(i).getKey().compareTo(key)<=0 && entries.get(i+1).getKey().compareTo(key)>0){
                        return children.get(i).search(key);
                    }
                }
            }
        }
        return null;

    }


    public void remove(Comparable key, bplus_Tree tree) throws NullPointerException {
//        if(isLeaf == true){
//
//            if(check(key) == false){
//                return;
//            }
//            if (isRoot){
//                internalRemove(key);
//            }
//            else{
//                int entry_size = entries.size();
//                int entry_cap = (int) bplus_tree.getM()/2;
//                // If size of entry > M/2, delete
//                if(entry_size > entry_cap && entries.size() >2){
//                    internalRemove(key);
//                }
//                else{
//                    // If node at the left is not null, borrow to balance
//                    if(previous != null && previous.getEntries().size() > entry_cap &&
//                            previous.getEntries().size()>2 && previous.getParent() == parent){
//                        // 1. |1|2|3|4|    |5|6|
//                        // 2. |1|2|3|      |4|5|6|
//                        int last_idx = previous.getEntries().size()-1;
//                        entries.add(0,previous.getEntries().get(last_idx));
//                        previous.getEntries().remove(last_idx);
//                        internalRemove(key);
//
//                    }
//                    // If node at right is not null, borrow to balance
//                    else if(next != null && next.getEntries().size() > entry_cap &&
//                            next.getEntries().size()>2 && next.getParent() == parent){
//                        // 1. |1|2|      |3|4|5|6|
//                        // 2. |1|2|3|    |4|5|6|
//
//                        entries.add(next.getEntries().get(0));
//                        next.getEntries().remove(next.getEntries().get(0));
//                        internalRemove(key);
//                    }
//                    else{
//                        //No borrowing, merging
//                        if(previous != null &&
//                                (previous.getEntries().size() <= entry_cap || previous.getEntries().size() <=2) &&
//                        previous.getParent() == parent){
//                            // 1. |1|      |2|3|
//                            // 2.          |1|2|3|
//                            for(int i = previous.getEntries().size()-1; i>= 0; i--){
//                                entries.add(0,previous.getEntries().get(i));
//                            }
//                            internalRemove(key);
//                            previous.setParent(null);
//                            previous.setEntries(null);
//                            parent.getChildren().remove(previous);
//                            if(previous.getPrevious() !=null){
//                                Node prev = previous.getPrevious();
//                                prev.setNext(this);
//                                previous.setPrevious(null);
//                                previous.setNext(null);
//                                previous = prev;
//                            }
//                            else{
//                                bplus_tree.setHead(this);
//                                previous.setNext(null);
//                                previous = null;
//                            }
//
//                        }
//                        else if(next != null &&
//                                (next.getEntries().size() <= entry_cap || next.getEntries().size() <=2) &&
//                                next.getParent() == parent){
//                            // 1. |1|2|     |3|
//                            // 2. |1|2|3|
//                            for(int i = next.getEntries().size()-1; i>= 0; i--){
//                                entries.add(0,next.getEntries().get(i));
//                            }
//                            internalRemove(key);
//                            next.setParent(null);
//                            next.setEntries(null);
//                            parent.getChildren().remove(next);
//
//                            if(next.getNext() != null){
//                                Node nex = next.getNext();
//                                nex.setPrevious(this);
//                                next.setNext(null);
//                                next.setPrevious(null);
//                                next = nex;
//                            }
//                            else{
//                                next.setPrevious(null);
//                                next = null;
//                            }
//                        }
//                    }
//
//                }
//
//
//                parent.removeBalancing(bplus_tree);
//            }
//
//        }
//        else{
//            if (entries.get(0).getKey()!= null && key.compareTo(entries.get(0).getKey()) <= 0) {
//                children.get(0).remove(key, bplus_tree);
//            } else if (entries.get(entries.size() - 1).getKey()!= null && key.compareTo(entries.get(entries.size() - 1).getKey()) >= 0) {
//                children.get(children.size() - 1).remove(key, bplus_tree);
//            }
//            else {
//                for (int i = 0; i < entries.size()-2; i++) {
//                    if (entries.get(i).getKey().compareTo(key) <= 0 && entries.get(i + 1).getKey().compareTo(key) > 0) {
//                        children.get(i).remove(key, bplus_tree);
//                        break;
//                    }
//
//                }
//            }
//        }

        if (isLeaf) {
            if (!check(key)) {
                return;
            }
            if (isRoot) {
                internalRemove(key);
            } else {
                if (entries.size() > tree.getM() / 2 && entries.size() > 2) {
                    internalRemove(key);
                } else {
                    if (previous != null && previous.getEntries().size() > tree.getM() / 2
                            && previous.getEntries().size() > 2 && previous.getParent() == parent) {
                        int size = previous.getEntries().size();
                        Map.Entry<Comparable, Object> entry = previous.getEntries().get(size - 1);
                        previous.getEntries().remove(entry);
                        entries.add(0, entry);
                        internalRemove(key);
                    } else if (next != null && next.getEntries().size() > tree.getM() / 2
                            && next.getEntries().size() > 2 && next.getParent() == parent) {
                        Map.Entry<Comparable, Object> entry = next.getEntries().get(0);
                        next.getEntries().remove(entry);
                        // 添加到末尾
                        entries.add(entry);
                        internalRemove(key);
                    } else {
                        if (previous != null && (previous.getEntries().size() <= tree.getM() / 2
                                || previous.getEntries().size() <= 2) && previous.getParent() == parent) {
                            for (int i = previous.getEntries().size() - 1; i >= 0; i--) {
                                entries.add(0, previous.getEntries().get(i));
                            }
                            internalRemove(key);
                            previous.setParent(null);
                            previous.setEntries(null);
                            parent.getChildren().remove(previous);
                            if (previous.getPrevious() != null) {
                                Node temp = previous;
                                temp.getPrevious().setNext(this);
                                previous = temp.getPrevious();
                                temp.setPrevious(null);
                                temp.setNext(null);
                            } else {
                                tree.setHead(this);
                                previous.setNext(null);
                                previous = null;
                            }
                        } else if (next != null
                                && (next.getEntries().size() <= tree.getM() / 2 || next.getEntries().size() <= 2)
                                && next.getParent() == parent) {
                            for (int i = 0; i < next.getEntries().size(); i++) {
                                entries.add(next.getEntries().get(i));
                            }
                            internalRemove(key);
                            next.setParent(null);
                            next.setEntries(null);
                            parent.getChildren().remove(next);
                            if (next.getNext() != null) {
                                Node temp = next;
                                temp.getNext().setPrevious(this);
                                next = temp.getNext();
                                temp.setPrevious(null);
                                temp.setNext(null);
                            } else {
                                next.setPrevious(null);
                                next = null;
                            }
                        }
                    }
                }
                parent.removeBalancing(tree);
            }
        } else {
            if (key.compareTo(entries.get(0).getKey()) <= 0) {
                children.get(0).remove(key, tree);
            } else if (key.compareTo(entries.get(entries.size() - 1).getKey()) >= 0) {
                children.get(children.size() - 1).remove(key, tree);
            } else {
                for (int i = 0; i < entries.size(); i++) {
                    if (entries.get(i).getKey().compareTo(key) <= 0 && entries.get(i + 1).getKey().compareTo(key) > 0) {
                        children.get(i).remove(key, tree);
                        break;
                    }
                }
            }
        }

    }

    private void removeBalancing(bplus_Tree tree) {
//        validate(this, bplus_tree);
//        int leaf_cap = bplus_tree.getM()/2;
//        int leaf_count = children.size();
//        if(leaf_count < leaf_cap || leaf_count<2){
//            if(isRoot){
//                if(leaf_count >=2){
//                    return ;
//                }
//                else{
//                    /**
//                     * When leaf_count == 1
//                     * set leftmost child as new root, and update root.
//                     */
//                    Node new_root = children.get(0);
//                    bplus_tree.setRoot(new_root);
//                    new_root.setParent(null);
//                    new_root.setRoot(true);
//                    setEntries(null);
//                    setChildren(null);
//                }
//            }
//            else{
//                int cur_idx = parent.getChildren().indexOf(this);
//                int prev_idx =  cur_idx-1, next_idx = cur_idx+1;
//                Node prev = null, next =null;
//                if(prev_idx >=0){
//                    previous = parent.getChildren().get(prev_idx);
//                }
//                if(next_idx < parent.getChildren().size()){
//                    next = parent.getChildren().get(next_idx);
//                }
//                if(previous != null &&
//                        (previous.getChildren().size() > leaf_cap && previous.getChildren().size() > 2) ){
//                    int idx = previous.getChildren().size()-1;
//                    Node node = previous.getChildren().get(idx);
//                    previous.getChildren().remove(idx);
//                    node.setParent(this);
//                    children.add(0,node);
//                    validate(previous,bplus_tree);
//                    validate(this, bplus_tree);
//                    parent.removeBalancing(bplus_tree);
//
//                }
//                else if(next != null &&
//                        (next.getChildren().size() > leaf_cap && next.getChildren().size() > 2) ){
//                    Node node = next.getChildren().get(0);
//                    next.getChildren().remove(0);
//                    node.setParent(this);
//                    children.add(node);
//                    validate(this,bplus_tree);
//                    validate(this,bplus_tree);
//                    parent.removeBalancing(bplus_tree);
//                }
//                else{
//                    if(previous != null && (previous.getChildren().size() <= leaf_cap || previous.getChildren().size() <=2)) {
//                        for (int i = previous.getChildren().size()-1; i>= 0;i--){
//                            Node temp_child = previous.getChildren().get(i);
//                            temp_child.setParent(this);
//                            children.add(0, temp_child);
//                        }
//                        previous.setChildren(null);
//                        previous.setEntries(null);
//                        previous.setParent(null);
//                        parent.getChildren().remove(previous);
//                        validate(this,bplus_tree);
//                        parent.removeBalancing(bplus_tree);
//                    }
//                    else if(next != null &&
//                            (next.getChildren().size() <= leaf_cap || next.getChildren().size() <=2)) {
//                        for (int i = 0; i<=next.getChildren().size()-1; i++){
//                            Node temp_child = next.getChildren().get(i);
//                            temp_child.setParent(this);
//                            children.add(0, temp_child);
//                        }
//                        next.setChildren(null);
//                        next.setEntries(null);
//                        next.setParent(null);
//                        parent.getChildren().remove(next);
//                        validate(this,bplus_tree);
//                        parent.removeBalancing(bplus_tree);
//                    }
//                }
//            }
//        }
        validate(this, tree);
        if (children.size() < tree.getM() / 2 || children.size() < 2) {
            if (isRoot) {
                if (children.size() >= 2) {
                    return;
                } else {
                    Node root = children.get(0);
                    tree.setRoot(root);
                    root.setParent(null);
                    root.setRoot(true);
                    setEntries(null);
                    setChildren(null);
                }
            } else {
                int currIdx = parent.getChildren().indexOf(this);
                int prevIdx = currIdx - 1;
                int nextIdx = currIdx + 1;
                Node previous = null, next = null;
                if (prevIdx >= 0) {
                    previous = parent.getChildren().get(prevIdx);
                }
                if (nextIdx < parent.getChildren().size()) {
                    next = parent.getChildren().get(nextIdx);
                }
                if (previous != null && previous.getChildren().size() > tree.getM() / 2 && previous.getChildren().size() > 2) {
                    int idx = previous.getChildren().size() - 1;
                    Node borrow = previous.getChildren().get(idx);
                    previous.getChildren().remove(idx);
                    borrow.setParent(this);
                    children.add(0, borrow);
                    validate(previous, tree);
                    validate(this, tree);
                    parent.removeBalancing(tree);
                } else if (next != null && next.getChildren().size() > tree.getM() / 2 && next.getChildren().size() > 2) {
                    Node borrow = next.getChildren().get(0);
                    next.getChildren().remove(0);
                    borrow.setParent(this);
                    children.add(borrow);
                    validate(next, tree);
                    validate(this, tree);
                    parent.removeBalancing(tree);
                } else {
                    if (previous != null && (previous.getChildren().size() <= tree.getM() / 2 || previous.getChildren().size() <= 2)) {
                        for (int i = previous.getChildren().size() - 1; i >= 0; i--) {
                            Node child = previous.getChildren().get(i);
                            children.add(0, child);
                            child.setParent(this);
                        }
                        previous.setChildren(null);
                        previous.setEntries(null);
                        previous.setParent(null);
                        parent.getChildren().remove(previous);
                        validate(this, tree);
                        parent.removeBalancing(tree);
                    } else if (next != null && (next.getChildren().size() <= tree.getM() / 2 || next.getChildren().size() <= 2)) {
                        for (int i = 0; i < next.getChildren().size(); i++) {
                            Node child = next.getChildren().get(i);
                            children.add(child);
                            child.setParent(this);
                        }
                        next.setChildren(null);
                        next.setEntries(null);
                        next.setParent(null);
                        parent.getChildren().remove(next);
                        validate(this, tree);
                        parent.removeBalancing(tree);
                    }
                }
            }
        }
    }

    private void internalRemove(Comparable key) {
        int index = -1;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getKey().compareTo(key) == 0) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            entries.remove(index);
        }
    }

    protected boolean check(Comparable key) {
        for (Map.Entry<Comparable, Object> entry : entries) {
            if (entry.getKey().compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }


    public void create(Comparable key, Object obj, bplus_Tree bplus_tree) {
        if(isLeaf){
            // if key already exist in node, and balanced
            if(check(key) || entries.size() < bplus_tree.getM()){
                // insert leaf
                insertLeaf(key,obj);
                if(parent != null){
                    parent.insertBalancing(bplus_tree);
                }
            }
            // needs split and re-balance
            else{

                Node left = new Node(true); // leaf node
                Node right = new Node(true);
                // build linked list
                if(previous != null){
                    previous.setNext(left);
                    left.setPrevious(previous);
                }
                if(next!=null){
                    next.setPrevious(right);
                    right.setNext(next);
                }
                if(previous ==null){
                    bplus_tree.setHead(left);
                }
                left.setNext(right);
                right.setPrevious(left);
                previous = null;
                next = null;


                //(tree.getOrder() + 1) / 2 + (tree.getOrder() + 1) % 2;
                int leftSize =(bplus_tree.getM() + 1) / 2 + (bplus_tree.getM() + 1) % 2;
                int rightSize = (bplus_tree.getM() + 1) / 2;
                // insert leaf
                insertLeaf(key, obj);
                for (int i = 0; i<leftSize;i++){
                    left.getEntries().add(entries.get(i));
                }
                for(int j = 0; j<rightSize;j++){
                    right.getEntries().add(entries.get(leftSize+j));
                }
                // overflow in non leaf node
                if(parent!=null){
                    int index = parent.getChildren().indexOf(this);
                    parent.getChildren().remove(this);
                    left.setParent(parent);
                    right.setParent(parent);
                    parent.getChildren().add(index,left);
                    parent.getChildren().add(index+1, right);
                    setEntries(null);
                    setChildren(null);

                    parent.insertBalancing(bplus_tree);
                    setParent(null);
                }
                // overflow in leaf node
                else{
                    isRoot = false;
                    Node parent = new Node(false, true);
                    bplus_tree.setRoot(parent);
                    left.setParent(parent);
                    right.setParent(parent);
                    parent.getChildren().add(left);
                    parent.getChildren().add(right);
                    setEntries(null);
                    setChildren(null);
                    parent.insertBalancing(bplus_tree);
                }
            }
        }
        else{
            // If key less than the leftmost key, traverse left
            if (key.compareTo(entries.get(0).getKey()) <= 0) {
                children.get(0).create(key, obj, bplus_tree);
            } else if (key.compareTo(entries.get(entries.size() - 1).getKey()) >= 0) {
                children.get(children.size() - 1).create(key, obj, bplus_tree);
            } else {
                for (int i = 0; i < entries.size(); i++) {
                    if (entries.get(i).getKey().compareTo(key) <= 0 && entries.get(i + 1).getKey().compareTo(key) > 0) {
                        children.get(i).create(key, obj, bplus_tree);
                        break;
                    }
                }
            }

        }

    }

    private void insertBalancing(bplus_Tree bplus_tree) {
        validate(this, bplus_tree);// 如果子节点数超出阶数,则需要分裂该节点
        if (children.size() > bplus_tree.getM()) {
            // 分裂成左右两个节点
            Node left = new Node(false);
            Node right = new Node(false);
            // 左右两个节点关键字长度
            int leftSize = (bplus_tree.getM() + 1) / 2 + (bplus_tree.getM() + 1) % 2;
            int rightSize = (bplus_tree.getM() + 1) / 2;
            // 复制子节点到分裂出来的新节点,并更新关键字
            for (int i = 0; i < leftSize; i++) {
                left.getChildren().add(children.get(i));
                left.getEntries().add(new AbstractMap.SimpleEntry(children.get(i).getEntries().get(0).getKey(), null));
                children.get(i).setParent(left);
            }
            for (int i = 0; i < rightSize; i++) {
                right.getChildren().add(children.get(leftSize + i));
                right.getEntries().add(new AbstractMap.SimpleEntry(children.get(leftSize + i).getEntries().get(0).getKey(), null));
                children.get(leftSize + i).setParent(right);
            } // 如果不是根节点
            if (parent != null) {
                // 调整父子节点关系
                int index = parent.getChildren().indexOf(this);
                parent.getChildren().remove(this);
                left.setParent(parent);
                right.setParent(parent);
                parent.getChildren().add(index, left);
                parent.getChildren().add(index + 1, right);
                setEntries(null);
                setChildren(null);// 父节点更新关键字
                parent.insertBalancing(bplus_tree);
                setParent(null);
                // 如果是根节点
            } else {
                isRoot = false;
                Node parent = new Node(false, true);
                bplus_tree.setRoot(parent);
                left.setParent(parent);
                right.setParent(parent);
                parent.getChildren().add(left);
                parent.getChildren().add(right);
                setEntries(null);
                setChildren(null);// 更新根节点
                parent.insertBalancing(bplus_tree);
            }
        }
    }

    private void validate(Node node, bplus_Tree bplus_tree) {
        // if number of pointers = number of leaf nodes, checking for orphans
        if (node.getEntries().size() == node.getChildren().size()) {
            for (int i = 0; i < node.getEntries().size(); i++) {
                if(node.getChildren().get(i).getEntries()!= null) {
                    Comparable key = node.getChildren().get(i).getEntries().get(0).getKey();

                    if (node.getEntries().get(i).getKey().compareTo(key) != 0) {
                        node.getEntries().remove(i);
                        node.getEntries().add(i, new AbstractMap.SimpleEntry(key, null));
                        if (!node.isRoot()) {
                            validate(node.getParent(), bplus_tree);
                        }
                    }
                }
            }
        } else if (node.isRoot() && node.getChildren().size() >= 2 || node.getChildren().size() >= bplus_tree.getM() / 2
                && node.getChildren().size() <= bplus_tree.getM() && node.getChildren().size() >= 2) {
            node.getEntries().clear();
            for (int i = 0; i < node.getChildren().size(); i++) {
                if(node.getChildren().get(i).getEntries()!= null){

                    Comparable key = node.getChildren().get(i).getEntries().get(0).getKey();
                    node.getEntries().add(new AbstractMap.SimpleEntry(key, null));
                }

                if (!node.isRoot()) {
                    validate(node.getParent(), bplus_tree);
                }
            }
        }

    }


    private void insertLeaf(Comparable key, Object obj) {
        Map.Entry<Comparable,Object> entry = new AbstractMap.SimpleEntry<Comparable, Object>(key,obj);
        // if empty list, insert
        if(entries.size() ==0){
            entries.add(entry);
            return;
        }
        for( int i = 0;i<entries.size();i++){
            // If keyword exist, update
            if(entries.get(i).getKey().compareTo(key)==0){
                entries.get(i).setValue(obj);
                return;
            }
            else if(entries.get(i).getKey().compareTo(key)>0){
                if (i==0){
                    entries.add(0,entry);
                    return;
                }
                else{
                    entries.add(i,entry);
                    return;
                }
            }
        }
        entries.add(entries.size(),entry);
    }


    public void traverse(Comparable key1, Comparable key2) {

    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public List<Map.Entry<Comparable, Object>> getEntries() {
        return entries;
    }

    public void setEntries(List<Map.Entry<Comparable, Object>> entries) {
        this.entries = entries;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
