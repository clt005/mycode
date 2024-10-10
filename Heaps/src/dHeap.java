/*
 * Name: Clayton Hammen Tan
 * PID:  A17819097
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Clayton Hammen Tan
 * @since 05-20-2024
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min
    private final int binary = 2;
    private final int defaultSize = 10;

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this.d = binary;
        this.heap = (T[]) new Comparable[defaultSize];
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this.d = binary;
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = true;
    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d < 1 ) {
            throw new IllegalArgumentException();
        }
        this.d = d;
        this.heap = (T[]) new Comparable[heapSize];
        this.nelems = 0;
        this.isMaxHeap = isMaxHeap;
    }
    /**
     * Returns the number of elements currently stored in the heap.
     *
     * @return the number of elements in the heap.
     */
    @Override
    public int size() {
        return nelems;
    }
    /**
     * Removes and returns the root element from the heap.
     * Replaces the root with the last element and
     * restructures the heap to maintain the heap property by trickling down.
     *
     * @return the root element of the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T out = heap[0];
        heap[0] = heap[nelems-1];
        nelems--;
        if (nelems > 0) {
            trickleDown(0);
        }
        return out;
    }
    /**
     * Adds a new element to the heap.
     * Places the new element at the end of the heap array and
     * restructures the heap by bubbling up the element to maintain the heap property.
     *
     * @param item the element to add to the heap.
     * @throws NullPointerException if the added item is null.
     */
    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        if (nelems == heap.length) {
            resize();
        }
        heap[nelems] = item;
        bubbleUp(nelems);
        nelems++;
    }
    /**
     * Clears all elements from the heap, setting each element in the heap array to null and
     * resetting
     * the number of elements to zero.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        for (int i = 0; i < nelems; i++) {
            heap[i] = null;
        }
        nelems = 0;
    }
    /**
     * Retrieves, but does not remove, the root element of the heap.
     *
     * @return the root element of the heap.
     * @throws NoSuchElementException if the heap is empty.
     */
    @Override
    public T element() throws NoSuchElementException {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }
    /**
     * Calculates and returns the index of the parent node for a given node index.
     *
     * @param index the index of the node.
     * @return the index of the parent node.
     */
    private int parent(int index) {
        return (index - 1) / this.d;
    }
    /**
     * Moves an element up in the heap until the heap property is restored
     *
     * @param index the starting index of the element to bubble up.
     */
    private void bubbleUp(int index) {
        while (index > 0 && compare(heap[index], heap[parent(index)]) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }
    /**
     * Moves an element down in the heap until the heap property is restored
     *
     * @param index the starting index of the element to trickle down.
     */
    private void trickleDown(int index) {
        int childIndex = bestChild(index);
        while (childIndex != -1 &&
                compare(heap[childIndex], heap[index]) > 0) {
            swap(index, childIndex);
            index = childIndex;
            childIndex = bestChild(index);
        }
    }
    /**
     * Compares two elements of the heap, depending on the type of heap
     *
     * @param x the first element to compare.
     * @param y the second element to compare.
     * @return a negative integer, zero, or a positive integer as x is less than, equal to,
     * or greater than y.
     */
    private int compare(T x, T y) {
        if (isMaxHeap) {
            return x.compareTo(y);
        } else {
            return y.compareTo(x);
        }
    }
    /**
     * Swaps two elements in the heap array.
     *
     * @param i the index of the first element.
     * @param j the index of the second element.
     */
    private void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    /**
     * Finds the best child according to the heap property (max or min) of a node at a given index.
     *
     * @param index the index of the parent node.
     * @return the index of the best child node or -1 if no children exist.
     */
    private int bestChild(int index) {
        int bestChildIndex = -1;
        int firstChild = index * d + 1;
        for (int i = 0; i < d && firstChild + i < nelems; i++) {
            if ((bestChildIndex == -1 ||
                    compare(heap[firstChild + i], heap[bestChildIndex]) > 0)) {
                bestChildIndex = firstChild + i;
            }
        }
        return bestChildIndex;
    }

    /**
     * Doubles the size of the heap array if it is full
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newHeap = (T[]) new Comparable[heap.length*2];
        int index = 0;
        while (index < heap.length) {
            newHeap[index] = heap[index];
            index++;
        }
        heap = newHeap;
    }
}
