/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

/**
 * Simple Bloom Filter Implementation
 *
 * @author Clayton Hammen Tan
 * @since 05-29-2024
 */

public class MyBloomFilter implements KeyedSet {

    private static final int DEFAULT_M = (int) 1e7;
    private static final int LEFT_BIT = 5;
    private static final int RIGHT_BIT = 27;
    private static final int ASCII_CUTOFF = 96;
    private static final int HORNERS_INT = 27;
    private static final int PROVIDED_INT = 8;
    boolean[] bits;

    /**
     * Initialize MyBloomFilter with the default number of bits
     */
    public MyBloomFilter() {
        bits = new boolean[DEFAULT_M];
    }

    /**
     * Insert the string key into the bloom filter.
     *
     * @param key key to insert
     * @throws NullPointerException if value is null
     * @return true if the key was inserted, false if the key was already
     *         present
     */
    public boolean insert(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int hashA = hashFuncA(key);
        int hashB = hashFuncB(key);
        int hashC = hashFuncC(key);
        boolean duplicate = lookup(key);
        bits[hashA] = true;
        bits[hashB] = true;
        bits[hashC] = true;
        return !duplicate;
    }

    /**
     * Check if the given key is present in the bloom filter.
     * @param key key to look up
     * @throws NullPointerException if value is null
     * @return true if the key was found, false if the key was not found
     */
    public boolean lookup(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int hashA = hashFuncA(key);
        int hashB = hashFuncB(key);
        int hashC = hashFuncC(key);
        return bits[hashA] && bits[hashB] && bits[hashC];
    }

    /**
     * First hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncA(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int leftShiftedValue = hashValue <<LEFT_BIT;
            int rightShiftedValue = hashValue >>RIGHT_BIT;
            hashValue = (leftShiftedValue | rightShiftedValue) ^ value.charAt(i);
        }
        return Math.abs(hashValue) % bits.length;
    }

    /**
     * Second hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncB(String value) {
        int hashValue = 0;
        for (int i = 0; i < value.length(); i++) {
            int letterValue = value.charAt(i) - ASCII_CUTOFF;
            hashValue = (hashValue * HORNERS_INT + letterValue) % bits.length;
        }
        return hashValue;
    }

    /**
     * Third hash function to be used by MyBloomFilter
     * @param value The input string
     * @return A hashcode for the string
     */
    private int hashFuncC(String value) {
        int hashVal = 0;
        for (int j = 0; j < value.length(); j++) {
            int letter = value.charAt(j);
            hashVal = ((hashVal << PROVIDED_INT) + letter) % bits.length;
        }
        return Math.abs(hashVal % bits.length);
    }
}
