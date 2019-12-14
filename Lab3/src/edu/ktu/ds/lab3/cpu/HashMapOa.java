package edu.ktu.ds.lab3.cpu;

import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.HashType;
import edu.ktu.ds.lab3.utils.Ks;
import edu.ktu.ds.lab3.utils.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashMapOa<K, V> implements Map<K, V> {
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    public static final float DEFAULT_LOAD_FACTOR = 0.9f;
    public static final HashType DEFAULT_HASH_TYPE = HashType.DIVISION;
    protected HashType ht;

    // Maišos lentelė
    protected Node<K, V>[] table;
    protected int size = 0;
    protected float loadFactor;
    protected int rehashesCounter = 0;
    protected int index = 0;

    public HashMapOa()
    {
        ht = DEFAULT_HASH_TYPE;
        this.table = new Node[DEFAULT_INITIAL_CAPACITY];
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    public HashMapOa(int initialCapacity, HashType ht, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        this.table = new Node[initialCapacity];
        this.loadFactor = loadFactor;
        this.ht = ht;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
    public int getTableCapacity() {
        return table.length;
    }
    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        index = 0;
        rehashesCounter = 0;
    }

    @Override
    public String[][] toArray() {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Node<K, V> n : table) {
            String[] list = new String[1];
            list[0] = n.toString();
            result[count] = list;
            count++;
        }
        return result;
    }
    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key or value is null in put(Key key, Value value)");
        }
        index = hash(key, ht);
        int inde = FindPosition(key);
        table[inde] = new Node<>(key, value, table[inde]);
        size++;
        if (size >= table.length * loadFactor) {
            rehash();
        }
        return value;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in get(Key key)");
        }
        index = hash(key, ht);
        int index1 = GetIndex(key);
        if(index1 >= 0)
        {
            return table[index].value;
        }
        return null;
    }
    private int FindPosition(K key)
    {
        index = hash(key, ht);
        int index0 = index;
        for(int i =0; i < table.length; i++)
        {
            if(table[index] == null || table[index].key.equals(key))
            {
                return index;
            }
            index = (index + (i + 1) * (i + 1)) % table.length;
        }
        return -1;
    }
    public int GetIndex(K key)
    {
        index = hash(key, ht);
        int index0 = index;
        for(int i =0; i < table.length; i++)
        {
            if(table[index].key != null) {
                if (table[index].key.equals(key)) {
                    return index;
                }
            }
            index = (index + (i + 1) * (i + 1)) % table.length;
        }
        return -1;
    }
    public int getRehashesCounter() {
        return rehashesCounter;
    }
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null in remove(Key key)");
        }
        int index1 = GetIndex(key);
        if(index1 > -1) {
            V value = table[index1].value;
            table[index1].key = null;
            table[index1].value = null;
            size--;
            return value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }
    private int hash(K key, HashType hashType) {
        int h = key.hashCode();
        switch (hashType) {
            case DIVISION:
                return Math.abs(h) % table.length;
            case MULTIPLICATION:
                double k = (Math.sqrt(5) - 1) / 2;
                return (int) (((k * Math.abs(h)) % 1) * table.length);
            case JCF7:
                h ^= (h >>> 20) ^ (h >>> 12);
                h = h ^ (h >>> 7) ^ (h >>> 4);
                return h & (table.length - 1);
            case JCF8:
                h = h ^ (h >>> 16);
                return h & (table.length - 1);
            default:
                return Math.abs(h) % table.length;
        }
    }
    public void println() {
        if (isEmpty()) {
            Ks.oun("Atvaizdis yra tuščias");
        } else {
            String[][] data = getModelList();
            for (int i = 0; i < data.length; i++) {
                for(int j =0; j < data[i].length; j++) {
                    String format = "%7s";
                    Object value = data[i][j];
                    Ks.ouf(format, (value == null ? "" : value));
                }
                Ks.oufln("");
            }
        }

        Ks.oufln("****** Bendras porų kiekis yra " + size);
    }
    public String[][] getModelList() {
        String[][] result = new String[table.length][];
        int count = 0;
        for (Node<K, V> n : table) {
            List<String> list = new ArrayList<>();
            list.add("[ " + count + " ]");
            if(n != null) {
                list.add("-->");
                list.add(n.key.toString());
            }
            result[count] = list.toArray(new String[0]);
            count++;
        }
        return result;
    }
    private void rehash() {
        HashMapOa<K, V> newMap = new HashMapOa<>(table.length * 2, ht, loadFactor);
        for (int i = 0; i < table.length; i++) {
            if(table[i] != null) {
                newMap.put(table[i].key, table[i].value);
            }
        }
        table = newMap.table;
        rehashesCounter++;
    }
    protected static class Node<K, V> {

        // Raktas
        protected K key;
        // Reikšmė
        protected V value;

        protected Node() {
        }

        protected Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

}
