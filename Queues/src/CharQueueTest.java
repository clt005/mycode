import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

class CharQueueTest {

    @Test
    public void charQueueTest() {
        Assertions.assertDoesNotThrow(() -> new CharQueue());
        Assertions.assertDoesNotThrow(() -> new CharQueue(10));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CharQueue(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CharQueue(-1));
    }

    @Test
    public void peekTest() {
        CharQueue queue0 = new CharQueue();
        Assertions.assertThrows(NoSuchElementException.class, queue0::peek);
        queue0.enqueue('a');
        Assertions.assertDoesNotThrow(queue0::peek);
        Assertions.assertEquals('a', queue0.peek());
        queue0.enqueue('b');
        queue0.enqueue('c');
        Assertions.assertEquals(3, queue0.size());
        Assertions.assertEquals('a', queue0.peek());
    }

    @Test
    public void enqueueTest() {
        CharQueue queue1 = new CharQueue(10);
        Assertions.assertTrue(queue1.isEmpty());
        for (int i = 0; i < 10; i++) {
            queue1.enqueue('i');
        }
        Assertions.assertEquals(10, queue1.size());
        Assertions.assertEquals('i', queue1.peek());
        for (int i = 0; i < 5; i++) {
            queue1.enqueue('j');
        }
        Assertions.assertFalse(queue1.isEmpty());
        Assertions.assertEquals(15, queue1.size());
        Assertions.assertEquals('i', queue1.peek());
    }

    @Test
    public void clearTest() {
        CharQueue queue2 = new CharQueue();
        queue2.clear();
        Assertions.assertEquals(0, queue2.size());
        Assertions.assertTrue(queue2.isEmpty());
        queue2.enqueue('i');
        Assertions.assertFalse(queue2.isEmpty());
        Assertions.assertEquals(1, queue2.size());
        queue2.clear();
        Assertions.assertEquals(0, queue2.size());
        Assertions.assertTrue(queue2.isEmpty());
        for (int i = 0; i < 5; i++) {
            queue2.enqueue('i');
        }
        Assertions.assertFalse(queue2.isEmpty());
        queue2.clear();
        Assertions.assertEquals(0, queue2.size());
        Assertions.assertTrue(queue2.isEmpty());
    }

    @Test
    public void dequeueTest() {
        CharQueue queue3 = new CharQueue();
        Assertions.assertThrows(NoSuchElementException.class, queue3::dequeue);
        queue3.enqueue('x');
        Assertions.assertEquals('x', queue3.dequeue());
        queue3.enqueue('a');
        queue3.enqueue('b');
        queue3.enqueue('c');
        Assertions.assertEquals('a', queue3.dequeue());
        Assertions.assertEquals('b', queue3.dequeue());
        Assertions.assertEquals('c', queue3.dequeue());
        Assertions.assertTrue(queue3.isEmpty());
        queue3.enqueue('d');
        queue3.dequeue();
        queue3.enqueue('e');
        queue3.dequeue();
        Assertions.assertTrue(queue3.isEmpty());
        for (int i = 0; i < 10; i++) {
            queue3.enqueue('i');
        }
        Assertions.assertEquals(10, queue3.size());
        for (int i = 0; i < 10; i++) {
            Assertions.assertEquals('i', queue3.dequeue());
        }
        Assertions.assertTrue(queue3.isEmpty());
    }
}
