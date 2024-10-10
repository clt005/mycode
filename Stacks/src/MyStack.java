/*
    Name: Clayton Hammen Tan
    PID:  A17819097
 */

import java.util.EmptyStackException;
import utilities.FullStackException;

/**
 * MyStack Implementation
 * @author Clayton Hammen Tan
 * @since 04-08-24
 */
public class MyStack {
    String[] stackArray;
    int size;
    int capacity;
    private static final int DEFAULT_CAPACITY_TEN = 10;

    /**
     * Constructor that initializes a stack with the given initial capacity.
     * @param capacity , integer, maximum number of elements the stack can contain
     * @throws IllegalArgumentException , if capacity is less than one
     * */
    public MyStack(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        } else {
            this.capacity = capacity;
            this.stackArray = new String[capacity];
            this.size = 0;
        }
    }

    /**
     * Constructor that initializes a stack with capacity 10.
     * */
    public MyStack() {
        this(DEFAULT_CAPACITY_TEN);
    }

    /**
     * Checks if the stack is empty and returns true, false otherwise.
     * @return boolean on if the stack is empty
     * */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears all elements in the stack.
     * */
    public void clear() {
        size = 0;
    }

    /**
     * Returns the number of elements in the stack.
     * @return number of elements in the stack
     * */
    public int size() {
        return size;
    }

    /**
     * Returns the maximum number of elements the stack can store.
     * @return maximum number of elements the stack can store
     * */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the top element of the stack.
     * @return the top element of the stack
     * @throws EmptyStackException , if stack is empty
     * */
    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            return stackArray[size - 1];
        }
    }

    /**
     * Pushes the given element to the stack.
     * @param element , string to be added to the top of the stack array
     * @throws FullStackException , if stack is full
     * @throws IllegalArgumentException , if element is null
     * */
    public void push(String element) {
        if (size() == capacity()) {
            throw new FullStackException("Your stack is full.");
        }
        if (element == null) {
            throw new IllegalArgumentException();
        }
        stackArray[size] = element;
        size ++;
    }

    /**
     * Returns and removes the top element of the stack.
     * @return the top element of the stack then removes it from the stack
     * @throws EmptyStackException , if stack is empty
     * */
    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        } else {
            size--;
            return stackArray[size];
        }
    }

    /**
     * Pushes all the strings in elements to the stack.
     * @param elements , array of strings
     * @throws IllegalArgumentException , if elements is null
     * */
    public void multiPush(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException();
        }
        for (String element : elements) {
            push(element);
        }
    }

    /**
     * Pops the given amount of elements from the stack.
     * @param amount , integer, the amount of elements to be popped
     * @return string array of all popped elements
     * @throws IllegalArgumentException , if amount is not a positive number (zero is not)
     * */
    public String[] multiPop(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        } else {
            int finalAmount = Math.min(amount, size());
            String[] poppedArray = new String[finalAmount];
            for (int i = 0; i < finalAmount; i++) {
                poppedArray[i] = pop();
            }
            return poppedArray;
        }

    }


}
