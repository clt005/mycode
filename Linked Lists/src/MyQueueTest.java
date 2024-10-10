import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {
    private MyQueue<Integer> queue;

    @BeforeEach
    public void setUp() {
        queue = new MyQueue<>();
    }

    @Test
    public void constructorTest() {
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(queue.size(), 0);
        Assertions.assertNotEquals(queue, null);
    }

    @Test
    public void enqueueTest() {
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(queue.size(), 0);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertEquals(queue.size(), 10);
        queue.enqueue(10);
        Assertions.assertFalse(queue.isEmpty());
        Assertions.assertEquals(queue.size(), 11);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {queue.enqueue(null);});
    }

    @Test
    public void dequeueTest() {
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(queue.size(), 0);
        queue.enqueue(1);
        queue.dequeue();
        Assertions.assertNotEquals(queue, null);
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals(queue.size(), 0);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        Assertions.assertEquals(queue.size(), 10);
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals(i, queue.dequeue());
        }
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertDoesNotThrow(() -> {queue.dequeue();});
        Assertions.assertEquals(null, queue.dequeue());
    }

    @Test
    public void peekTest() {
        Assertions.assertTrue(queue.isEmpty());
        queue.enqueue(0);
        Assertions.assertEquals(queue.peek(), 0);
        Assertions.assertFalse(queue.isEmpty());
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i+1);
        }
        Assertions.assertEquals(queue.peek(), 0);
        for (int i = 0; i < 11; i++) {
            Assertions.assertEquals(i, queue.dequeue());
        }
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertDoesNotThrow(() -> {queue.dequeue();});
        Assertions.assertEquals(queue.peek(), null);
    }

    @Test
    public void toStringTest() {
        Assertions.assertTrue(queue.isEmpty());
        Assertions.assertEquals("[]", queue.toString());
        queue.enqueue(0);
        Assertions.assertEquals("[0]", queue.toString());
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i+1);
        }
        Assertions.assertEquals("[0 -> 1 -> 2 -> 3 -> 4 -> 5]", queue.toString());
        for (int i = 0; i < 6; i++) {
            queue.dequeue();
        }
        Assertions.assertEquals("[]", queue.toString());
    }
}
