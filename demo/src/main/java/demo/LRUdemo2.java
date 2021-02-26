package demo;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author DELL
 * @Date 2021/2/25 18:29
 * @Version 1.0
 */
public class LRUdemo2 {

    class Node<k,v>{
        k key;
        v value;
        Node<k,v> prev;
        Node<k,v> next;

        public Node() {
        }

        public Node(k key, v value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }

    }
    class DoubleLinklist<k,v>{
        Node<k,v> head;
        Node<k,v> tail;

        public DoubleLinklist() {
            this.head = new Node<>();
            this.tail = new Node<>();
            head.next = tail;
            tail.prev = head;

        }
        //向第一添加
        public void addOneNode(Node<k,v> node){
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;

        }
        //删除这个节点
        public void removeNode(Node<k,v> node){
             node.prev.next = node.next;
             node.next.prev = node.prev;
             node.prev = null;
             node.next = null;
        }
        //获取最后一个节点
        public Node<k,v> getLastNode(){
            return tail.prev;
        }
    }

    private int cachesize;
    Map<Integer, Node<Integer,Integer>> map;
    DoubleLinklist<Integer,Integer> doubleLinklist;

    public LRUdemo2(int cachesize) {
        this.cachesize = cachesize;
        map = new HashMap<>();
        doubleLinklist = new DoubleLinklist<>();
    }

    public int get(int key){

        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinklist.removeNode(node);
        doubleLinklist.addOneNode(node);
        return node.value;
    }

    public void put(int key,int value){
        if (map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);//表示更新
            doubleLinklist.removeNode(node);
            doubleLinklist.addOneNode(node);
        }else {
            if (map.size()>cachesize){
                Node<Integer, Integer> lastNode = doubleLinklist.getLastNode();
                map.remove(lastNode.key);
                doubleLinklist.removeNode(lastNode);
            }
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key,newNode);
            doubleLinklist.addOneNode(newNode);
        }
    }
}
