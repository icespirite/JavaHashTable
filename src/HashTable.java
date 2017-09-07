/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class HashTable<K, V> {

    @SuppressWarnings("rawtypes")
    private Entry[] table;
    private int capacity;
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public HashTable(int capacity){
        this.capacity = capacity;
        table = new Entry[capacity];
       }
    
    private int hash(Object key){
        return key.hashCode() % capacity;
    }
    
    public V get(Object key){
        if (key == null) return getForNullKey();
        int hash = hash(key);
        for (Entry<K,V> e = table[hash(key)]; e!= null; e = e.next){
            Object k;
            if ((k = e.key) == key || key.equals(k))
                return e.value;
            
        }
        return null;
    }
    
    private V getForNullKey() {
        for (Entry<K,V> e = table[0]; e!= null; e = e.next){
            if (e.key == null)
                return e.value;
        }
        return null;
    }
    
    public V put(K key, V value) {
        if (key == null)
            return putForNullKey(value);
        int hash = hash(key);
        for (Entry<K,V> e = table[hash]; e != null; e = e.next) {
            Object k;
            if ((k = e.key) == key || key.equals(k)){
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        
        addEntry(hash, key, value);
        return null;
    }
    
    private V putForNullKey(V value) {
        for (Entry<K,V> e = table[0]; e!= null; e = e.next) {
            if (e.key == null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
    addEntry(0, null, value);
    return null;
    }
    
    void addEntry(int hash, K key, V value) {
        Entry<K,V> e = table[hash];
        table[hash] = new Entry<>(hash, key, value,e);
    }
       
    static class Entry<K, V> implements Map.Entry<K, V>{
        final K key;
        V value;
        Entry<K,V> next;
        final int hash;
        
        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }

        @Override
        public K getKey() {
            // TODO Auto-generated method stub
            return key;
        }

        @Override
        public V getValue() {
            // TODO Auto-generated method stub
            return value;
        }

        @Override
        public V setValue(V newvalue) {
            // TODO Auto-generated method stub
            V oldValue = value;
            value = newvalue;
            return oldValue;
        }
        
    }
       public static void main (String[] args){
           HashTable hashTable = new HashTable(97);
           hashTable.put(2,"comput");
           hashTable.put(5,"tree");
           hashTable.put(6,"window");
           hashTable.put(7,"castle");
           hashTable.put(8,"apple");
           String k = "abc";
           String m = k.toUpperCase();
           System.out.println(m);
           System.out.println(k);
           List a = new ArrayList<String>();
           List c = new ArrayList<String>();
           a.add("first");
           List b = a;
           a.add("second");
           c.add("third");
           a = c;
           System.out.println(a);
           System.out.println(b);
           System.out.println(c);
           
       }
}
