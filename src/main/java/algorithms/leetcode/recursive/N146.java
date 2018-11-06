package algorithms.leetcode.recursive;

import java.util.HashMap;
import java.util.Map;

/**
 * <dl>
 * <dt>N146</dt>
 * <dd>Description: 146. Least Recently Used (LRU) cache</dd>
 * <dd>Copyright: Copyright (C) 2006</dd>
 * <dd>Company: 青牛（北京）技术有限公司</dd>
 * <dd>CreateDate: 2018/11/5</dd>
 * </dl>
 *
 *     Design and implement a data structure for Least Recently Used (LRU) cache.
 *     It should support the following operations: get and put.
 *
 *     Could you do both operations in O(1) time complexity?
 *
 *     为了达到O(1)的时间复杂度，使用hashmap和双向链表结合完成。
 *     特别注意双向链表的删除操作，在头，在尾和在中间等边界条件。
 *
 * @author cuihc
 */
public class N146 {

    class LRUCache {

        class Element {
            private Element f;
            private Element t;
            private int key;
            private int value;

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Element element = (Element) o;

                return key == element.key;
            }

            @Override
            public int hashCode() {
                return key;
            }
        }

        private int capacity;
        private Map<Integer,Element> map;
        private Element front;
        private Element tail;


        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>(capacity);
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            return touchKey(key).value;
        }

        private Element touchKey(int key) {
            Element element = map.get(key);
            delete(element);
            addFirst(element);
            return element;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                Element element = touchKey(key);
                element.value = value;
                element.key = key;
                return;
            }
            Element element = new Element();
            element.key = key;
            element.value = value;
            if (map.size() == 0) {
                element.t = element;
                element.f = element;
                front = element;
                tail = element;
            }
            addFirst(element);
            map.put(key, element);
            if (map.size() > capacity) {
                Element ttail = tail;
                delete(tail);
                map.remove(ttail.key);
            }
        }

        private void addFirst(Element element) {
            element.t = front;
            element.f = tail;
            front.f = element;
            tail.t = element;
            front = element;
        }

        private void delete(Element element) {
            element.f.t = element.t;
            element.t.f = element.f;
            if (tail == element) {
                tail = element.f;
            }
            if (front == element) {
                front = element.t;
            }
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new N146().new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        System.out.println(cache.get(3));      // returns 1
        System.out.println(cache.get(2));
        cache.put(4, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
//        cache.put(4, 4);    // evicts key 1
//        System.out.println( cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));       // returns 4
    }
}
