package com.lenve.algorithm.hashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU 缓存机制
 * Created by lenve on 2022/11/23
 */

public class LRUCacheWithLinkedHashMap extends LinkedHashMap<Integer, Integer> {

    // 定义缓存容量
    private int capacity;

    public LRUCacheWithLinkedHashMap(int capacity) {
        // 这里的 accessOrder 置为 true 后，只要 Map 中的节点被访问，就会自动调整该节点的位置
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // 访问数据的 get 方法
    public int get(int key) {
        if (super.get(key) == null) {
            return -1;
        }
        return super.get(key);
    }

    // put 方法
    public void put(int key, int value) {
        super.put(key, value);
    }

    // 重写 超出容量后是删除元素还是扩容的方法  true -> 删除  false -> 不删除，扩容
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; // 当前长度超过容量后，删除元素
    }

    public static void main(String[] args) {
        LRUCacheWithLinkedHashMap lRUCache = new LRUCacheWithLinkedHashMap(2);
        lRUCache.put(1, 1);
        lRUCache.put(2, 2);
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3);
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4);
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }
}
