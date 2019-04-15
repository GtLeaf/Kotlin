package coursePractise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUcache<K, V> {
    //需要一个HashMap, listhead, listTail
    private HashMap<K, Node> map;
    private NodeList listHead;
    private NodeList listTail;

    LRUcache(){
        map = new HashMap<>();
        listHead = null;
        listTail = null;
    }

    private class Node{
        K key;
        V value;
        LRUcache pre;
        LRUcache next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class NodeList{
        Node node;
        NodeList pre;
        NodeList next;

        public NodeList(Node node){
            this.node = node;
        }
    }

    //get
    //边界判空
    //修改nodelist的顺序
    public V get(K key){
        Node node = map.get(key);

        return node.value;
    }


    //set
    //将值设置进去，修改使用顺序链表
    public void set(K key, V value){

    }

    //move
    //set、get之后进行链表调整
    private void move(Node node){

    }

}

class LRU<K,V>{
    private static final float hashLoadFactory = 0.75f;
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LRU(int cacheSize){
        this.cacheSize = cacheSize;
        //向上取整
        int capacity = (int)Math.ceil(cacheSize / hashLoadFactory) + 1;
        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true){
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > LRU.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key){
        return map.get(key);
    }

    public synchronized void put(K key, V value){
        map.put(key, value);
    }

    public synchronized void clear(){
        map.clear();
    }

    public synchronized int usedSize(){
        return map.size();
    }

    public void print(){
        for (Map.Entry<K, V> entry : map.entrySet()){
            System.out.println(entry.getValue() + "--");
        }
    }
}