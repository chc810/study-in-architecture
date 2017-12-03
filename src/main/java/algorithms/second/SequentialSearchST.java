package algorithms.second;

public class SequentialSearchST<K extends Comparable<K>> implements ST<K>{

    private Node first;

    public void put(K key, Object value) {
        Node  node = first;
        while (node.key.compareTo(key) != 0 ) {
            node = node.next;
        }
    }

    public Object get(K key) {
        Node  node = first;
        while (node.key.compareTo(key) != 0 ) {
            node = node.next;
        }
        if (node != null) {
            return node.value;
        }
        return null;
    }

    public void delete(K key) {

    }

    public boolean contains(K key) {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public int size() {
        return 0;
    }

    public Iterable<K> keys() {
        return null;
    }

    private class Node {
        K key;
        Object value;
        Node next;

        public Node(K key, Object value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
