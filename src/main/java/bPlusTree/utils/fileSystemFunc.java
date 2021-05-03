package BPlusTree.utils;

public interface fileSystemFunc {
    Object search(Comparable key);

    void remove(Comparable key);

    void add(Comparable key, Object obj);

    void update(Comparable key, Object obj);

    void traverse(Comparable key1, Comparable key2);

}
