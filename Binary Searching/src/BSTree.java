/*
 * Name: Clayton Hammen Tan
 * PID:  A17819097
 */

import java.util.*;

/**
 * Binary search tree implementation.
 * 
 * @author Clayton Hammen Tan
 * @since  05-10-2024
 */
public class BSTree<T extends Comparable<? super T>> implements Iterable {

    /* * * * * BST Instance Variables * * * * */
    private int nelems; // number of elements stored
    private BSTNode root; // reference to root node

    /* * * * * BST Node Inner Class * * * * */

    /**
     * Binary search tree node implementation
     */
    protected class BSTNode {

        T key;
        LinkedList<T> dataList;
        BSTNode left;
        BSTNode right;

        /**
         * A constructor that initializes the BSTNode instance variables.
         *
         * @param left     Left child
         * @param right    Right child
         * @param dataList Linked list of related info
         * @param key      Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, LinkedList<T> dataList, T key) {
            this.left = left;
            this.right = right;
            this.dataList = dataList;
            this.key = key;
        }

        /**
         * A constructor that initializes BSTNode variables. Note: This constructor is
         * used when you want to add a key with no related information yet. In this
         * case, you must create an empty LinkedList for the node.
         *
         * @param left  Left child
         * @param right Right child
         * @param key   Node's key
         */
        public BSTNode(BSTNode left, BSTNode right, T key) {
            this.left = left;
            this.right = right;
            this.dataList = new LinkedList<>();
            this.key = key;
        }

        /**
         * Return the key
         *
         * @return The key
         */
        public T getKey() {
            return this.key;
        }

        /**
         * Return the left child of the node
         *
         * @return The left child of the node
         */
        public BSTNode getLeft() {
            return this.left;
        }

        /**
         * Return the right child of the node
         *
         * @return The right child of the node
         */
        public BSTNode getRight() {
            return this.right;
        }

        /**
         * Return the linked list of the node
         *
         * @return The linked list of the node
         */
        public LinkedList<T> getDataList() {
            return this.dataList;
        }

        /**
         * Setter for left child of the node
         *
         * @param newleft New left child
         */
        public void setLeft(BSTNode newleft) {
            this.left = newleft;
        }

        /**
         * Setter for right child of the node
         *
         * @param newright New right child
         */
        public void setRight(BSTNode newright) {
            this.right = newright;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setDataList(LinkedList<T> newData) {
            this.dataList = newData;
        }

        /**
         * Append new data to the end of the existing linked list of the node
         *
         * @param data New data to be appended
         */
        public void addNewInfo(T data) {
            dataList.add(data);
        }

        /**
         * Remove 'data' from the linked list of the node and return true. If the linked
         * list does not contain the value 'data', return false.
         *
         * @param data Info to be removed
         * @return True if data was found, false otherwise
         */
        public boolean removeInfo(T data) {
            if (this.dataList.contains(data)) {
                this.dataList.remove(data);
                return true;
            }
            return false;
        }
    }

    /* * * * * BST Methods * * * * */

    /**
     * 0-arg constructor that initializes root to null and nelems to 0
     */
    public BSTree() {
        root = null;
        nelems = 0;
    }

    /**
     * Return the root of BSTree. Returns null if the tree is empty
     *
     * @return The root of BSTree, null if the tree is empty
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Return the BST size
     *
     * @return The BST size
     */
    public int getSize() {
        return nelems;
    }

    /**
     * Insert a key into BST
     * 
     * @param key data to be inserted
     * @return true if insertion is successful and false otherwise
     * @throws NullPointerException if key is null
     */
    public boolean insert(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (root == null) {
            root = new BSTNode(null, null, key);
            nelems++;
            return true;
        }
        return insertHelper(root, key);
    }

    /**
     * Helper recursive function to insert a key into BST
     *
     * @param root root of BST
     * @param key data to be inserted
     * @return boolean whether the key was inserted or not
     */
    private boolean insertHelper(BSTNode root, T key) {
        int compareValue = key.compareTo(root.key);
        if (compareValue == 0) {
            return false;
        } else if (compareValue < 0) {
            if (root.left == null) {
                root.setLeft(new BSTNode(null,null, key));
                nelems++;
            } else {
                insertHelper(root.left, key);
            }
        } else {
            if (root.right == null) {
                root.setRight(new BSTNode(null,null, key));
                nelems++;
            } else {
                insertHelper(root.right, key);
            }
        }
        return true;
    }

    /**
     * Return true if the 'key' is found in the tree, false otherwise
     *
     * @param key To be searched
     * @return True if the 'key' is found, false otherwise
     * @throws NullPointerException If key is null
     */
    public boolean findKey(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return findKeyHelper(root, key);
    }

    /**
     * Helper recursive function to find a key in a BST
     *
     * @param root root of BST
     * @param key data to be found
     * @return boolean on whether key was found
     */
    private boolean findKeyHelper(BSTNode root, T key) {
        if (root == null) {
            return false;
        }
        int compareValue = key.compareTo(root.key);
        if (compareValue == 0) {
            return true;
        } else if (compareValue < 0) {
            return findKeyHelper(root.left, key);
        } else {
            return findKeyHelper(root.right, key);
        }
    }

    /**
     * Insert 'data' into the LinkedList of the node whose key is 'key'
     *
     * @param key  Target key
     * @param data To be added to key's LinkedList
     * @throws NullPointerException     If either key or data is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public void insertData(T key, T data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode insertNode = findNodeHelper(root, key);
        insertNode.addNewInfo(data);
    }

    /**
     * Helper recursive function to return the node whose key is 'key'
     *
     * @param root root of BST
     * @param key data to be found
     * @return the node whose key is 'key'
     */
    private BSTNode findNodeHelper(BSTNode root, T key) {
        if (root == null) {
            return null;
        }
        int compareValue = key.compareTo(root.key);
        if (compareValue == 0) {
            return root;
        } else if (compareValue < 0) {
            return findNodeHelper(root.left, key);
        } else {
            return findNodeHelper(root.right, key);
        }
    }

    /**
     * Return the LinkedList of the node with key value 'key'
     *
     * @param key Target key
     * @return LinkedList of the node whose key value is 'key'
     * @throws NullPointerException     If key is null
     * @throws IllegalArgumentException If key is not found in the BST
     */
    public LinkedList<T> findDataList(T key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!findKey(key)) {
            throw new IllegalArgumentException();
        }
        BSTNode node = findNodeHelper(root, key);
        return node.getDataList();
    }

    /**
     * Return the height of the tree
     *
     * @return The height of the tree, -1 if BST is empty
     */
    public int findHeight() {
        return findHeightHelper(root);
    }

    /**
     * Helper for the findHeight method
     *
     * @param root Root node
     * @return The height of the tree, -1 if BST is empty
     */
    private int findHeightHelper(BSTNode root) {
        if (root == null) {
            return -1;
        } else {
            return 1 + Math.max(findHeightHelper(root.left), findHeightHelper(root.right));
        }
    }

    /* * * * * BST Iterator * * * * */

    /**
     * Binary search tree iterator implementation
     */
    public class BSTree_Iterator implements Iterator<T> {
        /* * * * * BST Iterator Variables * * * * */

        private  Stack<BSTNode> stack;

        /* * * * * BST Iterator Methods * * * * */

        /**
         * Constructor that initializes the iterator
         */
        public BSTree_Iterator() {
            stack = new Stack<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        /**
         * Return a boolean whether the stack has a next element
         * @return a boolean whether the stack has a next element
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next element in the stack
         * @return the next element in the stack
         * @throws NoSuchElementException if stack has no next element
         */
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            BSTNode node = stack.pop();
            BSTNode currentNode = node.right;
            T out = node.key;
            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.left;
            }
            return out;
        }
    }

    /**
     * creates an iterate over the BSTree
     * @return a BSTree iterate
     */
    public Iterator<T> iterator() {
        return new BSTree_Iterator();
    }

    /* * * * * Extra Credit Methods * * * * */
    /*
    public ArrayList<T> intersection(Iterator<T> iter1, Iterator<T> iter2) {

        return null;
    }

    public T levelMax(int level) {

        return null;
    }
    */
}

