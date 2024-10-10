/*
 * Name: Clayton Hammen Tan
 * PID:  A17819097
 */

import java.util.HashSet;


/**
 * One-Hot Encoding
 *
 * @author Clayton Hammen Tan
 * @since 04-03-24
 */

public class OneHot {
    /**
     * EC1
     * Unique categories but same length
     */
    public static String[] getUnique(String[] arr) {
        return null;
    }

    /**
     * EC2
     * Number of unique categories
     */
    public static int getNumOfUniqueElements(String[] arr) {
        int count = 0;
        for(String i : arr) {
            if(i != null) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * EC3
     * Feature Matrix
     */
    public static int[][] oneHotEncode(String[] arr) {
        return null;
    }
}
