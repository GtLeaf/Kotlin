package coursePractise;

import java.util.HashMap;

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
