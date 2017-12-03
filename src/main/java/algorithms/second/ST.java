package algorithms.second;

/**
 * 符号表定义
 * @param <K>
 */
public interface ST<K extends Comparable<K>> {
    void put(K key, Object value);
    Object get(K key);
    void delete(K key);
    boolean contains(K key);
    boolean isEmpty();
    int size();
    Iterable<K> keys();
}
