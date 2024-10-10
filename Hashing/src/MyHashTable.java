/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a hash table with separate chaining.
 * 
 * @author Clayton Hammen Tan
 * @since 05-27-2024
 */

public class MyHashTable implements KeyedSet {

    /* instance variables */
    private int size; // number of elements stored
    private int rehashCount;
    private int collisionCount;
    private LinkedList<String>[] table; // data table
    private List<String> statsLog;
    private static final int DEFAULT_CAPACITY = 19;
    private static final int MINIMUM_CAPACITY = 7;
    private static final int DOUBLE = 2;
    private static final int LEFT_BIT = 5;
    private static final int RIGHT_BIT = 27;


    /**
     * Initializes a hash table with default capacity.
     */
    public MyHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initializes a hash table with a given capacity.
     *
     * @param capacity The initial capacity of the hash table.
     * @throws IllegalArgumentException if capacity < 7
     */
    @SuppressWarnings("unchecked")
    public MyHashTable(int capacity) {
        if (capacity < MINIMUM_CAPACITY) {
            throw new IllegalArgumentException();
        }
        table = new LinkedList[capacity];
        size = 0;
        rehashCount = 0;
        collisionCount = 0;
        statsLog = new ArrayList<String>();
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * Inserts a value into the hash table.
     *
     * @param value The value to be inserted.
     * @return true if the value was inserted, false if it already exists.
     * @throws NullPointerException if value is null
     */
    public boolean insert(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (lookup(value)) {
            return false;
        }
        float loadFactor = (float) (size + 1) / table.length;
        if (loadFactor > 1) {
            statsLog.add(String.format("Before rehash # %d: load factor %.2f, %d collision(s).",
                    rehashCount + 1, loadFactor, collisionCount));
            rehash();
        }
        int idx = hashString(value);
        if (!table[idx].isEmpty()) {
            collisionCount++;
        }
        table[idx].add(value);
        size++;
        return true;
    }

    /**
     * Deletes a value from the hash table.
     *
     * @param value The value to be deleted.
     * @return true if the value was deleted, false if it does not exist.
     * @throws NullPointerException if value is null
     */
    public boolean delete(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int idx = hashString(value);
        if (table[idx].remove(value)) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Looks up a value in the hash table.
     *
     * @param value The value to look up.
     * @return true if the value exists, false otherwise.
     * @throws NullPointerException if value is null
     */
    public boolean lookup(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int idx = hashString(value);
        return table[idx].contains(value);
    }

    /**
     * Returns an array of all elements in the hash table.
     *
     * @return An array of all elements in the hash table.
     */
    public String[] returnAll() {
        LinkedList<String> allArray = new LinkedList<>();
        for (LinkedList<String> bucket : table) {
            allArray.addAll(bucket);
        }
        String[] outputArray = new String[allArray.size()];
        int idx = 0;
        for (String str : allArray) {
            outputArray[idx] = str;
            idx++;
        }
        return outputArray;
    }

    /**
     * Returns the number of elements currently stored in the hash table.
     *
     * @return The number of elements in the hash table.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the total capacity of the hash table.
     *
     * @return The total capacity of the hash table.
     */
    public int capacity() {
        return table.length;
    }

    /**
     * Returns the statistics log of the HashTable.
     *
     * @return the statistics log of the HashTable.
     */
    public String getStatsLog() {
        StringBuilder log = new StringBuilder();
        for (String entry : statsLog) {
            log.append(entry).append("\n");
        }
        return log.toString();
    }

    /**
     * Utility function provided to help with debugging
     */
    public void printTable() {
        String s = "";
        for (int i = 0; i < table.length; i++) {
            s = s + i + ":";
            if (!table[i].isEmpty()) {
                for (String t : table[i])
                    s = s + " " + t + ",";
                // remove trailing comma
                s = s.substring(0, s.length() - 1);
            }
            s = s + "\n";
        }
        // remove trailing newline
        s = s.substring(0, s.length() - 1);
        System.out.println(s);
    }

    /**
     * Rehashes the table by doubling its capacity.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        rehashCount++;
        LinkedList<String>[] oldTable = table;
        int newCapacity = oldTable.length * DOUBLE;
        table = new LinkedList[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        collisionCount = 0;
        for (LinkedList<String> bucket : oldTable) {
            for (String value : bucket) {
                insert(value);
            }
        }
    }

    /**
     * Returns the hash value of the given string using the Simplified CRC hash function.
     *
     * @param value The value to hash.
     * @return The hash value of the string.
     */
    private int hashString(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashValue <<LEFT_BIT;
            int rightShiftedValue = hashValue >>RIGHT_BIT;
            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        return Math.abs(hashValue) % capacity();
    }
}
