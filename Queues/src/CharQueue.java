/*
    Name: Clayton Hammen Tan
    PID:  A17819097
 */

import java.util.NoSuchElementException;

/**
 * Resizable Queue Implementation
 * @author Clayton Hammen Tan
 * @since 04-16-2024
 */
public class CharQueue {
    private static final int DEFAULT_CAPACITY = 5;
    private static final int DOUBLE_CAPACITY = 2;
    /* instance variables, feel free to add more if you need */
    private char[] circularArray;
    private int length;
    private int front;
    private int rear;
    private int capacity;

    /**
     * Constructor that initializes a queue with default capacity of 5.
     */
    public CharQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor that initializes a queue with the given capacity.
     * @param capacity , integer, assigned capacity of queue
     * @throws IllegalArgumentException , if capacity is not a positive integer (0 is not)
     */
    public CharQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        } else {
            circularArray = new char[capacity];
            this.length = 0;
            this.front = 0;
            this.rear = 0;
            this.capacity = capacity;
        }
    }

    /**
     * Checks if queue is empty and returns true, false otherwise.
     * @return boolean on if the queue is empty or not
     */
    public boolean isEmpty() {
        return this.length == 0;
    }

    /**
     * Returns the number of elements in the queue.
     * @return integer , representing the number of elements in the queue
     */
    public int size() {
        return this.length;
    }

    /**
     * Clears all elements in the queue.
     */
    public void clear() {
        this.circularArray = new char[this.capacity];
        this.length = 0;
        this.front = 0;
        this.rear = 0;
    }

    /**
     * Adds a new element to the back of the queue.
     * Doubles the queue's capacity if queue is already full.
     * @param elem , char, element to be added to the queue
     */
    public void enqueue(char elem) {
        if (this.length == this.capacity) {
            char[] newArray = new char[this.capacity * DOUBLE_CAPACITY];
            for (int i = 0; i < this.capacity; i++) {
                newArray[i] = this.circularArray[(this.front + i) % this.capacity];
            }
            this.circularArray = newArray;
            this.front = 0;
            this.rear = this.length;
            this.capacity = this.capacity * DOUBLE_CAPACITY;
            }
        this.circularArray[this.rear] = elem;
        this.rear = (this.rear + 1) % this.capacity;
        this.length++;
    }
    /**
     * Returns the element at the front of the queue.
     * @return the element at the front of the queue
     * @throws NoSuchElementException , if queue is empty
     */
    public char peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return this.circularArray[this.front];
    }
    /**
     * Returns and removes the element at the front of the queue.
     * @return the element at the front of the queue
     * @throws NoSuchElementException , if queue is empty
     */
    public char dequeue() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        char elem = this.circularArray[this.front];
        this.circularArray[this.front] = '\0';
        this.front = (front + 1) % this.capacity;
        this.length--;
        if (this.length == 0) {
            this.front = 0;
            this.rear = 0;
        }
        return elem;
    }
}
