/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

import java.util.*;

/**
 * Donation Stack Implementation
 *
 * @author Clayton Hammen Tan
 * @since 06-08-2024
 */

public class DonationStack {
    Stack<Integer> normalStack;
    Stack<Integer> maxStack;

    /**
     * Constructor for DonationStack.
     */
    public DonationStack() {
        this.normalStack = new Stack<>();
        this.maxStack = new Stack<>();
    }
    /**
     * Pushes an amount onto the stack and updates the max stack if necessary.
     * @param val The amount to push onto the stack.
     */
    public void push(int val) {
        if (maxStack.isEmpty() || val > maxStack.peek()) {
            maxStack.push(val);
        }
        normalStack.push(val);
    }
    /**
     * Returns the most recently added element without removing it.
     * @return The top element of the stack.
     */
    public int peek() {
        return normalStack.peek();
    }
    /**
     * Removes and returns the most recently added element.
     * @return The popped element from the stack.
     */
    public int pop() {
        int val = normalStack.pop();
        if (val == maxStack.peek()) {
            maxStack.pop();
        }
        return val;
    }
    /**
     * Retrieves the maximum element in the stack.
     * @return The maximum element.
     */
    public int max() {
        return maxStack.peek();
    }
}
