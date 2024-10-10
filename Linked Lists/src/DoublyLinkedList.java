/*
 * NAME: Clayton Hammen Tan
 * PID: A17819097
 */

import java.util.AbstractList;

/**
 * Doubly Linked List Implementation
 * @author Clayton Hammen Tan
 * @since 04-26-2024
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         * @param element , element to add, can be null
         */
        private Node(T element) {
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            this.data = element;
            this.next = nextNode;
            this.prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            this.data = element;
        }

        /**
         * Accessor to get the Nodes Element
         *
         * @return the Nodes Element
         */
        public T getElement() {
            return this.data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            if (this.next != null) {
                this.next.prev = this.prev;
            }
            if (this.prev != null) {
                this.prev.next = this.next;
            }
            this.next = null;
            this.prev = null;
        }
    }

    ///


    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        nelems = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node(element,tail,tail.getPrev());
        tail.getPrev().setNext(newNode);
        tail.setPrev(newNode);
        nelems++;
        return true;
    }

    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index The index at which the specified element is to be inserted.
     * @param element The element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size).
     * @throws NullPointerException if the element is null.
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (element == null) {
            throw new NullPointerException();
        }
        if (index < 0 || index > nelems) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = getNth(index);
        Node newNode = new Node(element,currentNode,currentNode.getPrev());
        currentNode.getPrev().setNext(newNode);
        currentNode.setPrev(newNode);
        nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element The element whose presence in this list is to be tested.
     * @return true if this list contains the specified element; false otherwise.
     */
    @Override
    public boolean contains(Object element) {
        T data = (T) element;
        Node currentNode = head.getNext();
        while (currentNode != tail) {
            if (data.equals(currentNode.getElement())) {
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index The index of the element to return.
     * @return The element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size).
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nelems) {
            throw new IndexOutOfBoundsException();
        }
        return getNth(index).getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index The index of the node to retrieve.
     * @return The node at the specified index.
     */
    private Node getNth(int index) {
        Node currentNode = head.getNext();
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    /**
     * Determine if the list empty
     *
     * @return true if this list contains no elements; false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return nelems == 0;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index The index of the element to be removed.
     * @return The element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size).
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= nelems) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = getNth(index);
        T element = currentNode.getElement();
        currentNode.remove();
        nelems--;
        return element;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index The index of the element to replace.
     * @param element The element to be stored at the specified position.
     * @return The element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size).
     * @throws NullPointerException if the element is null.
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if (index < 0 || index > nelems) {
            throw new IndexOutOfBoundsException();
        }
        if (element == null) {
            throw new NullPointerException();
        }
        Node currentNode = getNth(index);
        T oldElement = currentNode.getElement();
        currentNode.setElement(element);
        return oldElement;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return The number of elements in this list.
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Returns a String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"
     *
     * @return A String representation of this list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[(head) -> ");
        Node currentNode = head.getNext();
        while (currentNode != tail) {
            sb.append(currentNode.getElement().toString()).append(" -> ");
            currentNode = currentNode.getNext();
        }
        sb.append("(tail)]");
        return sb.toString();
    }

    /* ==================== EXTRA CREDIT ==================== */

    /*
      Mimic Python list slicing, modify list in place

      TODO: javadoc comments

     public void slice(int start, int end, int step) {
        // TODO: complete implementation
    }


      Recursively swaps halves of list for n recursive layers

      TODO: javadoc comments

    public void swapHalves(int n, int start, int end) {
        // TODO: complete implementation
    }
     */

}
