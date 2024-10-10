/*
    Name: Clayton Hammen Tan
    PID:  A17819097
 */

/**
 * Queue Using Doubly Linked List
 *
 * @author Clayton Hammen Tan
 * @since 04-27-24
 */

public class MyQueue<T> {
    private DoublyLinkedList<T> queue;
    /* instance variables, feel free to add if you need */

    /* ===separation=== */

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        this.queue = new DoublyLinkedList<>();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if this queue contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return The size of the queue.
     */
    public int size() {
        return this.queue.size();
    }

    /**
     * Adds the specified element to the back of this queue.
     *
     * @param data The element to add.
     * @throws IllegalArgumentException if the specified element is null.
     */
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        this.queue.add(data);
    }

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    public T dequeue() {
        if (this.isEmpty()) {
            return null;
        }
        return this.queue.remove(0);
    }

    /**
     * Retrieves, but does not remove, the head of this queue,
     * or returns null if this queue is empty.
     *
     * @return The head of this queue, or null if this queue is empty.
     */
    public T peek() {
        if (this.isEmpty()) {
            return null;
        }
        return this.queue.get(0);
    }

    /**
     * Returns a string representation of this queue.
     * The format of the string is "[element1 -> element2 -> ...]",
     * where the elements are in the order from the front to the back of the queue.
     *
     * @return A string representation of this queue.
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (!this.isEmpty()) {
            for (int i = 0; i < this.size() - 1; i++) {
                sb.append(this.queue.get(i).toString()).append(" -> ");
            }
            sb.append(this.queue.get(this.size() - 1).toString());
        }
        sb.append("]");
        return sb.toString();
    }
}
