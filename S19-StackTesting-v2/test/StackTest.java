import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.collection.ArrayStack;
import utility.collection.StackADT;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private StackADT<String> stack;

    @BeforeEach
    void setUp() {
        stack = new ArrayStack<>();
    }

    @Test
    public void popZero() {
        assertThrows(IllegalStateException.class,() -> stack.pop());
    }

    @Test
    public void popOne() {
        stack.push("C");
        assertEquals("C", stack.pop());
    }

    @Test
    public void popMany() {
        stack.push("");
        stack.push("C");
        stack.push("A");
        assertEquals("A", stack.pop());
        assertEquals("C", stack.pop());
        assertEquals("", stack.pop());
    }

    @Test
    public void popBoundary() {
        // Boundary zero already tested.
        for (int i = 0; i < 100; i++) stack.push("" + i);
        for (int i = 99; i >= 0; i--) assertEquals("" + i, stack.pop());
    }

    @Test
    public void pop101() {
        // Boundary zero already tested.
        for (int i = 0; i < 101; i++) stack.push("" + i);
        for (int i = 100; i >= 0; i--) assertEquals("" + i, stack.pop());
    }

    @Test
    public void sizeZero() {
        assertEquals(0, stack.size());
    }

    @Test
    public void sizeOne() {
        stack.push("C");
        assertEquals(1, stack.size());
    }

    @Test
    public void sizeMany() {
        stack.push("");
        stack.push("C");
        stack.push("A");
        assertEquals(3, stack.size());
    }

    @Test
    public void sizeBoundary() {
        // Boundary zero already tested.
        for (int i = 0; i < 100; i++) stack.push("" + i);
        assertEquals(100, stack.size());
    }

    @Test
    public void isEmptyZero() {
        assertEquals(true, stack.isEmpty());
    }

    @Test
    public void isEmptyOne() {
        stack.push("C");
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void isEmptyMany() {
        stack.push("");
        stack.push("C");
        stack.push("A");
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void isEmptyBoundary() {
        // Boundary zero already tested.
        for (int i = 0; i < 100; i++) stack.push("" + i);
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void isEmpty101() {
        // Boundary zero already tested.
        for (int i = 0; i < 101; i++) stack.push("" + i);
        assertEquals(false, stack.isEmpty());
    }

    @Test
    public void indexOfZeroRegular() {
        assertEquals(-1, stack.indexOf("A"));
    }

    @Test
    public void indexOfZeroEmpty() {
        assertEquals(-1, stack.indexOf(""));
    }

    @Test
    public void indexOfZeroNull() {
        assertEquals(-1, stack.indexOf(null));
    }

    @Test
    public void indexOfOneRegular() {
        stack.push("C");
        assertEquals(0, stack.indexOf("C"));
    }

    @Test
    public void indexOfOneEmpty() {
        stack.push("");
        assertEquals(0, stack.indexOf(""));
    }

    @Test
    public void indexOfOneNull() {
        stack.push(null);
        assertEquals(0, stack.indexOf(null));
    }

    @Test
    public void indexOfManyRegular() {
        stack.push("C");
        stack.push(null);
        stack.push("A");
        stack.push(null);
        stack.push("B");
        stack.push("B");
        stack.push("");
        stack.push("");
        stack.push("G");
        assertEquals(8, stack.indexOf("C"));
    }

    @Test
    public void indexOfManyEmpty() {
        stack.push("C");
        stack.push(null);
        stack.push("A");
        stack.push(null);
        stack.push("B");
        stack.push("B");
        stack.push("");
        stack.push("");
        stack.push("G");
        assertEquals(1, stack.indexOf(""));
    }

    @Test
    public void indexOfManyNull() {
        stack.push("C");
        stack.push(null);
        stack.push("A");
        stack.push(null);
        stack.push("B");
        stack.push("B");
        stack.push("");
        stack.push("");
        stack.push("G");
        assertEquals(5, stack.indexOf(null));
    }

    @Test
    public void indexOfBoundary() {
        // Boundary zero already tested.
        for (int i = 0; i < 100; i++) stack.push("" + i);
        assertEquals(99, stack.indexOf("0"));
    }

    @Test
    public void indexOf101() {
        // Boundary zero already tested.
        for (int i = 0; i < 101; i++) stack.push("" + i);
        assertEquals(100, stack.indexOf("0"));
    }

    @Test
    public void indexOfExceptionNotFoundRegular() {
        for (int i = 0; i < 100; i++) stack.push("" + i);
        assertEquals(-1, stack.indexOf("A"));
    }

    @Test
    public void indexOfExceptionNotFoundNull() {
        for (int i = 0; i < 100; i++) stack.push("" + i);
        assertEquals(-1, stack.indexOf(null));
    }

    @Test
    public void peekZero() {
        assertThrows(IllegalStateException.class,() -> stack.peek());
    }

    @Test
    public void peekOne() {
        stack.push("C");
        assertEquals("C", stack.peek());
    }

    @Test
    public void peekMany() {
        stack.push("");
        stack.push("C");
        stack.push("A");
        assertEquals("A", stack.peek());
        assertEquals("A", stack.peek());
        assertEquals("A", stack.peek());
    }

    @Test
    public void peekBoundary() {
        // Boundary zero already tested.
        for (int i = 0; i < 100; i++) stack.push("" + i);
        for (int i = 99; i >= 0; i--) assertEquals("99", stack.peek());
    }

    @Test
    public void peek101() {
        // Boundary zero already tested.
        for (int i = 0; i < 101; i++) stack.push("" + i);
        for (int i = 100; i >= 0; i--) assertEquals("100", stack.peek());
    }

    @Test
    public void isFullZero() {
        assertEquals(false, stack.isFull());
    }

    @Test
    public void isFullOne() {
        stack.push("C");
        assertEquals(false, stack.isFull());
    }

    @Test
    public void isFullMany() {
        stack.push("");
        stack.push("C");
        stack.push("A");
        assertEquals(false, stack.isFull());
    }

    @Test
    public void isFullBoundary() {
        // Boundary zero already tested.
        for (int i = 0; i < 100; i++) stack.push("" + i);
        assertEquals(false, stack.isFull());
    }

    @Test
    public void isFull101() {
        // Boundary zero already tested.
        for (int i = 0; i < 101; i++) stack.push("" + i);
        assertEquals(false, stack.isFull());
    }
}
