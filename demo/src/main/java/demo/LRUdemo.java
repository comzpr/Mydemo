package demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author DELL
 * @Date 2021/2/25 14:51
 * @Version 1.0
 */

public class LRUdemo<k,v> extends LinkedHashMap<k,v> {

    private int capacity;

    public LRUdemo(int capacity) {
        super(capacity,0.75f,false);
        this.capacity = capacity;
    }

    /**重写父类的方法*/
    @Override
    protected boolean removeEldestEntry(Map.Entry<k,v> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUdemo<Object, Object> lrUdemo = new LRUdemo<>(3);

        lrUdemo.put(1,"a");
        lrUdemo.put(2,"b");
        lrUdemo.put(3,"c");



        System.out.println(lrUdemo.keySet());
        lrUdemo.put(4,"d");
        System.out.println(lrUdemo.keySet());
        lrUdemo.put(3,"p");
        System.out.println(lrUdemo.keySet());
    }
}
