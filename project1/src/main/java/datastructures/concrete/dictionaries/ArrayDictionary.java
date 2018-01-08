package datastructures.concrete.dictionaries;

import datastructures.concrete.dictionaries.ArrayDictionary.Pair;
import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;
import misc.exceptions.NotYetImplementedException;

/**
 * See IDictionary for more details on what this class should do
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // You may not change or rename this field: we will be inspecting
    // it using our private tests.
    private Pair<K, V>[] pairs;

    // You're encouraged to add extra fields (and helper methods) though!
    private int size;
    
    public ArrayDictionary() {
        this.pairs = makeArrayOfPairs(10);
        this.size = 0;
    }
    
    /**
     * This method will return a new, empty array of the given size
     * that can contain Pair<K, V> objects.
     *
     * Note that each element in the array will initially be null.
     */
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);

    }

    //need test
    @Override
    public V get(K key) {
        int index = indexOfKey(key);
        if(index == -1) {
            throw new NoSuchKeyException();
        }
        return pairs[index].value;
    }

    // need test
    @Override
    public void put(K key, V value) {
        int index = indexOfKey(key);
        if(index == -1) {
            // add new key and value
            Pair<K,V> newPair = new Pair<K, V>(key, value);
            pairs[size] = newPair;
            size++;
        } else {
            // replace value for existing key
            pairs[index].value = value; 
        }
    }

    // need test
    @Override
    public V remove(K key) {
        int index = indexOfKey(key);
        if(index == -1) {
            throw new NoSuchKeyException();
        }
        for (int i = index; i < size - 1; i++) {
            pairs[i] = pairs[i + 1];
         }
         size--;
    }
    
    //need test
    @Override
    public boolean containsKey(K key) {
        return indexOfKey(key) != -1;
    }
    
    // Returns the index of the pair of given key in the pairs array
    private int indexOfKey(K key) {
        int index = 0;
        for(int i = 0; i < size; i++) {
            if (pairs[i].key.equals(key)) {
                return index;
            }
            index++;
        }
        return -1;
    }

  //need test
    @Override
    public int size() {
        return size;
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        // You may add constructors and methods to this class as necessary.
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
