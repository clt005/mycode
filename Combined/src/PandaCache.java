/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

import java.util.*;

/**
 * Panda Cache Implementation
 *
 * @author Clayton Hammen Tan
 * @since 06-08-2024
 */

public class PandaCache {
    private class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private HashMap<Integer, Node> hashMap;
    private Node head, tail;
    private int capacity;

    /**
     * Constructs a new PandaCache with a specified capacity.
     * @param capacity The maximum number of items that the cache can hold.
     */
    public PandaCache(int capacity) {
        this.capacity = capacity;
        this.hashMap = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * Retrieves the zone number for a given panda ID from the cache.
     * @param pandaID The ID of the panda.
     * @return The zone number of the panda if present, or -1 if not found.
     */
    public int get(int pandaID) {
        Node node = hashMap.get(pandaID);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    /**
     * Updates the zone number for a panda or adds a new panda to the cache.
     * If the cache is at capacity, the least recently used panda is removed.
     * @param pandaID The ID of the panda.
     * @param zoneNumber The new zone number for the panda.
     */
    public void set(int pandaID, int zoneNumber) {
        Node node = hashMap.get(pandaID);
        if (node == null) {
            Node newNode = new Node(pandaID, zoneNumber);
            if (hashMap.size() == capacity) {
                Node tail = removeTail();
                hashMap.remove(tail.key);
            }
            hashMap.put(pandaID, newNode);
            addNode(newNode);
        } else {
            node.value = zoneNumber;
            moveToHead(node);
        }
    }

    /**
     * Moves a node to the head of the doubly linked list to mark it as the most recently used.
     *
     * @param node The node to move to the head.
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    /**
     * Adds a node right after the head.
     *
     * @param node The node to add.
     */
    private void addNode(Node node) {
        node.next = head.next;
        node.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    /**
     * Removes a node from the linked list.
     *
     * @param node The node to remove.
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * Pops the least recently used node from the end of the linked list.
     *
     * @return The node that was removed.
     */
    private Node removeTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }
}
