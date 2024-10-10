import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;

class DoublyLinkedListTest {
    private AbstractList<Integer> list;
    @BeforeEach
    public void setUp() {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void constructorTest() {
        Assertions.assertTrue(list.isEmpty());
        Assertions.assertEquals(list.size(), 0);
        Assertions.assertNotEquals(list, null);
    }

    @Test
    public void addTest() {
        list.add(1);
        Assertions.assertEquals(1, list.size());
        Assertions.assertNotEquals(list.size(), 0);
        list.add(2);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertThrows(NullPointerException.class, () -> {list.add(null);});
    }

    @Test
    public void addAtIndexTest() {
        list.add(0);
        list.add(1,10);
        Assertions.assertEquals(10, list.get(1));
        list.add(0,5);
        Assertions.assertEquals(5, list.get(0));
        list.add(2,15);
        Assertions.assertEquals(15, list.get(2));
        Assertions.assertEquals(4, list.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.add(10,100);});
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.add(-1,1);});
        Assertions.assertThrows(NullPointerException.class, () -> {list.add(1,null);});
    }

    @Test
    public void clearTest() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Assertions.assertEquals(10, list.size());
        list.clear();
        Assertions.assertEquals(0, list.size());
        Assertions.assertNotEquals(null, list);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void containsTest() {
        Assertions.assertFalse(list.contains(1));
        list.add(1);
        list.add(2);
        Assertions.assertTrue(list.contains(1));
        Assertions.assertTrue(list.contains(2));
        Assertions.assertFalse(list.contains(3));
    }

    @Test
    public void getTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.get(2));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.get(-1);});
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.get(3);});
    }

    @Test
    public void removeTest() {
        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals(2, list.remove(1));
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(3, list.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(3);});
        Assertions.assertEquals(1, list.remove(0));
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(3, list.get(0));
        Assertions.assertEquals(3, list.remove(0));
        Assertions.assertEquals(0, list.size());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.remove(0);});
    }

    @Test
    public void setTest() {
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.get(1));
        list.set(1,10);
        Assertions.assertEquals(10, list.get(1));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {list.set(10,0);});
        Assertions.assertThrows(NullPointerException.class, () -> {list.set(0,null);});
    }

    @Test
    public void toStringTest() {
        Assertions.assertEquals("[(head) -> (tail)]", list.toString());
        list.add(1);
        Assertions.assertEquals("[(head) -> 1 -> (tail)]", list.toString());
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Assertions.assertEquals("[(head) -> 1 -> 2 -> 3 -> 4 -> 5 -> (tail)]", list.toString());
    }


}
