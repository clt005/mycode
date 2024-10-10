
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.FullStackException;
import java.util.EmptyStackException;

class MyStackTest {
    @Test
    public void constructorTest() {
        MyStack stackArray1 = new MyStack(5);
        Assertions.assertEquals(5, stackArray1.capacity());
        MyStack stackArray2 = new MyStack(50);
        Assertions.assertEquals(50, stackArray2.capacity());
        MyStack stackArray3 = new MyStack(500);
        Assertions.assertEquals(500, stackArray3.capacity());
    }
    @Test
    public void defaultConstructorTest() {
        MyStack stackArray = new MyStack();
        Assertions.assertEquals(10, stackArray.capacity());
        Assertions.assertNotEquals(1, stackArray.capacity());
        Assertions.assertNotEquals(100, stackArray.capacity());
    }
    @Test
    public void CapacityImmutableTest() {
        MyStack stackArray = new MyStack();
        Assertions.assertEquals(10, stackArray.capacity());
        stackArray.push("1");
        Assertions.assertEquals(10, stackArray.capacity());
    }
    @Test
    public void IsEmptyTest() {
        MyStack stackArray = new MyStack();
        Assertions.assertTrue(stackArray.isEmpty());
    }
    @Test
    public void ClearTest() {
        MyStack stackArray1 = new MyStack();
        stackArray1.push("1");
        stackArray1.clear();
        Assertions.assertTrue(stackArray1.isEmpty());
        MyStack stackArray2 = new MyStack();
        stackArray2.clear();
        Assertions.assertTrue(stackArray2.isEmpty());
        MyStack stackArray3 = new MyStack();
        stackArray3.push("1");
        stackArray3.push("2");
        stackArray3.push("3");
        stackArray3.clear();
        Assertions.assertTrue(stackArray3.isEmpty());

    }
    @Test
    public void invalidCapacityTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new MyStack(-1));
    }
    @Test
    public void pushTest() {
        MyStack stackArray = new MyStack();
        stackArray.push("1");
        Assertions.assertEquals(1, stackArray.size());
    }
    @Test
    public void pushAddsElementToTopTest() {
        MyStack stackArray = new MyStack();
        stackArray.push("1");
        stackArray.push("2");
        Assertions.assertEquals("2", stackArray.peek());
    }
    @Test
    public void pushOnFullStackTest() {
        MyStack stackArray = new MyStack(1);
        stackArray.push("1");
        Assertions.assertThrows(FullStackException.class, () -> stackArray.push("2"));
    }
    @Test
    public void PopTest() {
        MyStack stackArray = new MyStack();
        stackArray.push("1");
        stackArray.pop();
        Assertions.assertEquals(0, stackArray.size());
    }
    @Test
    public void PopReturnsLastElementTest() {
        MyStack stackArray = new MyStack();
        stackArray.push("1");
        stackArray.push("2");
        Assertions.assertEquals("2", stackArray.pop());
    }
    @Test
    public void testPopFromEmptyStackThrowsException() {
        MyStack stackArray = new MyStack();
        Assertions.assertThrows(EmptyStackException.class, stackArray::pop);
    }
    @Test
    public void multiPushTest() {
        MyStack stackArray1 = new MyStack();
        String[] elements1 = {"1", "2", "3"};
        stackArray1.multiPush(elements1);
        Assertions.assertEquals(3, stackArray1.size());
        Assertions.assertEquals("3", stackArray1.peek());
        MyStack stackArray2 = new MyStack();
        String[] elements2 = {"one", "two", "three"};
        stackArray2.multiPush(elements2);
        Assertions.assertEquals(3, stackArray2.size());
        Assertions.assertEquals("three", stackArray2.peek());
        MyStack stackArray3 = new MyStack();
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                stackArray3.multiPush(null));
    }
    @Test
    public void multiPopTest() {
        MyStack stackArray1 = new MyStack();
        String[] elements1 = {"1", "2", "3"};
        stackArray1.multiPush(elements1);
        String[] poppedArray1 = stackArray1.multiPop(2);
        Assertions.assertArrayEquals(new String[]{"3", "2"}, poppedArray1);
        Assertions.assertEquals(1, stackArray1.size());
        MyStack stackArray2 = new MyStack();
        String[] elements2 = {"one","two"};
        stackArray2.multiPush(elements2);
        String[] poppedArray2 = stackArray2.multiPop(10);
        Assertions.assertArrayEquals(new String[]{"two","one"}, poppedArray2);
        Assertions.assertEquals(0, stackArray2.size());
        MyStack stackArray3 = new MyStack();
        stackArray3.push("123");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                stackArray3.multiPop(-1));
    }
}




