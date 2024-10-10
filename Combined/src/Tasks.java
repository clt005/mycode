/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

import java.util.*;

/**
 * DSA Review
 *
 * @author Clayton Hammen Tan
 * @since 06-08-2024
 */

public class Tasks {

    /**
     * TODO: Question A
     * Checks if there are any duplicate entries in the array of customer IDs.
     * @param entries The array of customer IDs to check for duplicates.
     * @return true if there are duplicates, false otherwise.
     */
    public static boolean raffleChecker(int[] entries) {
        HashSet<Integer> idTable = new HashSet<>();
        for (int i : entries) {
            if (idTable.contains(i)) {
                return true;
            }
        }
        return false;
    }

    // List Node class provided for reference
    public static class Node {
        Node next;
        String name;
        public Node(String name) {
            this.name = name;
        }
    }

    /**
     * TODO: Question B
     * Returns the middle node data from a singly linked list.
     * @param head The head of the singly linked list.
     * @return The data of the middle node.
     */
    public static String middleNode(Node head) {
        Node out = head;
        Node helper = head;
        while (helper != null && helper.next != null) {
            out = out.next;
            helper = helper.next.next;
        }
        return out.name;
    }

    /**
     * TODO: Question C
     * Determines if a new message can be constructed using only the characters
     * from an old message.
     * @param newMessage The message that needs to be constructed.
     * @param oldMessage The available characters in the old message.
     * @return true if the new message can be fully constructed from the old message,
     * false otherwise.
     */
    public static boolean canConstruct(String newMessage, String oldMessage) {
        HashMap<Character, Integer> oldCount = new HashMap<>();
        for (char c : oldMessage.toCharArray()) {
            oldCount.put(c, oldCount.getOrDefault(c, 0) + 1);
        }
        for (char c : newMessage.toCharArray()) {
            int count = oldCount.getOrDefault(c, 0);
            if (count == 0) {
                return false;
            }
            oldCount.put(c, count - 1);
        }
        return true;
    }

    /**
     * TODO: Question D
     * Returns the names of the k most frequent customers from the array of buyers.
     * @param buyers Array of buyer names, possibly containing duplicates.
     * @param k The number of top frequent customers to return.
     * @return An array of size k containing the names of the most frequent customers.
     */
    public static String[] frequentCustomers(String[] buyers, int k) {
        Hashtable<String, Integer> buyerTable = new Hashtable<>();
        for (String buyer : buyers) {
            buyerTable.put(buyer, buyerTable.getOrDefault(buyer, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> maxHeap = new PriorityQueue<>();
        maxHeap.addAll(buyerTable.entrySet());
        String[] out = new String[k];
        for (int i = 0; i < k; i++) {
            out[i] = maxHeap.poll().getKey();
        }
        return out;
    }


}
