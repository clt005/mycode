import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BSTreeTest {
    private BSTree<Integer> tree;

    @BeforeEach
    public void setUp() {
        tree = new BSTree<>();
        Assertions.assertNotNull(tree);
        Assertions.assertEquals( 0, tree.getSize());
        Assertions.assertNull(tree.getRoot());
    }
    @Test
    public void insertTest() {
        Assertions.assertThrows(NullPointerException.class, () -> tree.insert(null));
        Assertions.assertTrue(tree.insert(1));
        Assertions.assertFalse(tree.insert(1));
        Assertions.assertTrue(tree.insert(2));
        Assertions.assertTrue(tree.insert(3));
        Assertions.assertEquals(3, tree.getSize());
    }

    @Test
    public void findKeyTest() {
        Assertions.assertThrows(NullPointerException.class, () -> tree.findKey(null));
        tree.insert(1);
        tree.insert(2);
        Assertions.assertEquals(1, tree.getRoot().getKey());
        Assertions.assertEquals(2, tree.getSize());
        Assertions.assertTrue(tree.findKey(1));
        Assertions.assertTrue(tree.findKey(2));
        Assertions.assertFalse(tree.findKey(3));
    }

    @Test
    public void insertDataTest() {
        tree.insert(10);
        tree.insertData(10,5);
        tree.insertData(10,15);
        Assertions.assertEquals(10, tree.getRoot().getKey());
        Assertions.assertThrows(NullPointerException.class, () -> tree.insertData(null,1));
        Assertions.assertThrows(NullPointerException.class, () -> tree.insertData(10,null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> tree.insertData(1,50));
    }

    @Test
    public void findDataListTest() {
        Assertions.assertThrows(NullPointerException.class, () -> tree.findDataList(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {tree.findDataList(1);});
        tree.insert(1);
        tree.insertData(1,2);
        tree.insertData(1,3);
        Assertions.assertEquals(1, tree.getRoot().getKey());
        LinkedList<Integer> list = tree.findDataList(1);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void findHeightTest() {
        Assertions.assertEquals(-1, tree.findHeight());
        tree.insert(1);
        Assertions.assertEquals(0, tree.findHeight());
        tree.insert(2);
        tree.insert(3);
        Assertions.assertEquals(2, tree.findHeight());
        tree.insert(0);
        Assertions.assertEquals(2, tree.findHeight());
    }

    @Test
    public void BSTreeIteratorTest() {
        Iterator<Integer> iterator = tree.iterator();
        Assertions.assertFalse(iterator.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);
        BSTree<Integer> tree2 = new BSTree<>();
        tree2.insert(4);
        tree2.insert(2);
        tree2.insert(6);
        tree2.insert(1);
        tree2.insert(3);
        tree2.insert(5);
        tree2.insert(7);
        Iterator<Integer> iterator2 = tree2.iterator();
        Assertions.assertTrue(iterator2.hasNext());
        Assertions.assertDoesNotThrow(iterator2::next); // -> output: 1
        Assertions.assertEquals(2, iterator2.next());
        Assertions.assertEquals(3, iterator2.next());
        Assertions.assertEquals(4, iterator2.next());
        Assertions.assertEquals(5, iterator2.next());
        Assertions.assertNotEquals(7, iterator2.next());
        Assertions.assertNotEquals(6, iterator2.next());
        Assertions.assertThrows(NoSuchElementException.class, iterator2::next);
        BSTree<Integer> tree3 = new BSTree<>();
        for (int i = 0; i < 100; i++) {
            tree3.insert(i);
        }
        Iterator<Integer> iterator3 = tree3.iterator();
        for (int i = 0; i < 100; i++) {
            Assertions.assertEquals(i, iterator3.next());
        }
        Assertions.assertFalse(iterator3.hasNext());
        Assertions.assertThrows(NoSuchElementException.class, iterator3::next);
    }
}
