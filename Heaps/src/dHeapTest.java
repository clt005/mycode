import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
class dHeapTest {
    private dHeap<Integer> maxHeap;
    private dHeap<Integer> minHeap;

    @BeforeEach
    public void setUp() {
        maxHeap = new dHeap<>();
        minHeap = new dHeap<>(2, 10, false);
    }
    @Test
    public void addTest() {
        // max-heap
        assertThrows(NullPointerException.class, () -> maxHeap.add(null));
        assertEquals(0, maxHeap.size());
        maxHeap.add(1);
        maxHeap.add(2);
        Assertions.assertEquals(2, maxHeap.size());
        for (int i = 1; i <= 20; i++) {
            maxHeap.add(i);
        }
        Assertions.assertEquals(22, maxHeap.size());
        // min-heap
        assertThrows(NullPointerException.class, () -> minHeap.add(null));
        Assertions.assertEquals(0, minHeap.size());
        minHeap.add(1);
        minHeap.add(2);
        Assertions.assertEquals(2, minHeap.size());
        for (int i = 1; i <= 20; i++) {
            minHeap.add(i);
        }
        Assertions.assertEquals(22, minHeap.size());
    }
    @Test
    public void removeTest() {
        // max-heap
        Assertions.assertThrows(NoSuchElementException.class, () -> {maxHeap.remove();});
        maxHeap.add(1);
        maxHeap.add(2);
        maxHeap.add(3);
        Assertions.assertEquals(3, maxHeap.remove());
        Assertions.assertEquals(2, maxHeap.size());
        // min-heap
        Assertions.assertThrows(NoSuchElementException.class, () -> {minHeap.remove();});
        minHeap.add(1);
        minHeap.add(2);
        minHeap.add(3);
        Assertions.assertEquals(1, minHeap.remove());
        Assertions.assertEquals(2, minHeap.size());
    }
    @Test
    public void elementTest() {
        // max-heap
        Assertions.assertThrows(NoSuchElementException.class, () -> {maxHeap.element();});
        maxHeap.add(1);
        Assertions.assertEquals(1, maxHeap.element());
        maxHeap.add(3);
        Assertions.assertEquals(3, maxHeap.element());
        maxHeap.add(2);
        Assertions.assertEquals(3, maxHeap.element());
        // min-heap
        Assertions.assertThrows(NoSuchElementException.class, () -> {minHeap.element();});
        minHeap.add(1);
        Assertions.assertEquals(1, minHeap.element());
        minHeap.add(2);
        Assertions.assertEquals(1, minHeap.element());
        minHeap.add(0);
        Assertions.assertEquals(0, minHeap.element());
    }
    @Test
    public void clearTest() {
        // max-heap
        for (int i = 0; i < 10; i++) {
            maxHeap.add(i);
        }
        Assertions.assertEquals(10, maxHeap.size());
        maxHeap.clear();
        Assertions.assertEquals(0, maxHeap.size());
        Assertions.assertThrows(NoSuchElementException.class, () -> {maxHeap.remove();});
        Assertions.assertThrows(NoSuchElementException.class, () -> {maxHeap.element();});
        // min-heap
        for (int i = 0; i < 10; i++) {
            minHeap.add(i);
        }
        Assertions.assertEquals(10, minHeap.size());
        minHeap.clear();
        Assertions.assertThrows(NoSuchElementException.class, () -> {minHeap.remove();});
        Assertions.assertThrows(NoSuchElementException.class, () -> {minHeap.element();});
    }

}
