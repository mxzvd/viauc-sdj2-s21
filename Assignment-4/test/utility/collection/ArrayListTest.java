package utility.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ListADT<String> arrayList;

    @BeforeEach
    void setUp() {
        arrayList = new ArrayList<>();
    }

    @Test
    public void addAtIndexZero() {
        arrayList.add(0, "a");
        assertEquals("a", arrayList.get(0));
    }

    @Test
    public void addAtIndexOne() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        assertEquals("b", arrayList.get(1));
    }

    @Test
    public void addAtIndexMany() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        assertEquals(null, arrayList.get(3));
    }

    @Test
    public void addAtIndexBoundary() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        assertEquals("a", arrayList.get(4));
    }

    @Test
    public void addAtIndexException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.add(3, ""));
    }

    @Test
    public void addZero() {
        arrayList.add( "a");
        assertEquals("a", arrayList.get(0));
    }

    @Test
    public void addOne() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertEquals("b", arrayList.get(1));
    }

    @Test
    public void addMany() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertEquals(null, arrayList.get(3));
    }

    @Test
    public void addBoundary() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertEquals("a", arrayList.get(4));
    }

    @Test
    public void addException() {
        // No cases to test here.
    }

    @Test
    public void containsZero() {
        assertEquals(false, arrayList.contains(""));
        assertEquals(false, arrayList.contains("a"));
        assertEquals(false, arrayList.contains(null));
    }

    @Test
    public void containsOne() {
        arrayList.add("a");
        assertEquals(true, arrayList.contains("a"));

        arrayList = new ArrayList<>();

        arrayList.add("");
        assertEquals(true, arrayList.contains(""));

        arrayList = new ArrayList<>();

        arrayList.add(null);
        assertEquals(true, arrayList.contains(null));
    }

    @Test
    public void containsMany() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");

        assertEquals(true, arrayList.contains("a"));
        assertEquals(true, arrayList.contains(""));
        assertEquals(true, arrayList.contains(null));
    }

    @Test
    public void containsBoundary() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("d");

        assertEquals(true, arrayList.contains("a"));
        assertEquals(true, arrayList.contains(""));
        assertEquals(true, arrayList.contains(null));
    }

    @Test
    public void getZero() {
        // Same first mistake was here. Illegal state exception was being thrown instead of IndexOutOfBoundsException.
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(0));
    }

    @Test
    public void getOne() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        arrayList.add(1, "d");
        assertEquals("d", arrayList.get(1));
    }

    @Test
    public void getMany() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(3, "a");
        assertEquals("a", arrayList.get(3));
    }

    @Test
    public void getBoundary() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        arrayList.add(5, "b");
        assertEquals("a", arrayList.get(4));
    }

    @Test
    public void getException() {
        // First mistake was here. Illegal state exception was being thrown instead of IndexOutOfBoundsException.
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.get(3));
    }

    @Test
    public void indexOfZero() {
        assertEquals(-1, arrayList.indexOf("a"));
    }

    @Test
    public void indexOfOne() {
        arrayList.add("a");
        assertEquals(0, arrayList.indexOf("a"));

        arrayList = new ArrayList<>();

        arrayList.add("");
        assertEquals(0, arrayList.indexOf(""));

        arrayList = new ArrayList<>();

        arrayList.add(null);
        assertEquals(0, arrayList.indexOf(null));
    }

    @Test
    public void indexOfMany() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        arrayList.add(5, "b");
        assertEquals(2, arrayList.indexOf("a"));
        assertEquals(0, arrayList.indexOf(""));
        assertEquals(3, arrayList.indexOf(null));
    }

    @Test
    public void indexOfBoundary() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        arrayList.add(5, "b");
        arrayList.add(0, null);
        assertEquals(3, arrayList.indexOf("a"));
        assertEquals(1, arrayList.indexOf(""));
        // Third mistake indexOf didn't support null elements.
        assertEquals(0, arrayList.indexOf(null));
    }

    @Test
    public void indexOfException() {
        // No cases to test here.
    }

    @Test
    public void isEmptyZero() {
        assertEquals(true, arrayList.isEmpty());
    }

    @Test
    public void isEmptyOne() {
        arrayList.add("a");
        assertEquals(false, arrayList.isEmpty());

        arrayList = new ArrayList<>();

        arrayList.add("");
        assertEquals(false, arrayList.isEmpty());

        arrayList = new ArrayList<>();

        arrayList.add(null);
        assertEquals(false, arrayList.isEmpty());
    }

    @Test
    public void isEmptyMany() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");

        assertEquals(false, arrayList.isEmpty());
    }

    @Test
    public void isEmptyBoundary() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("d");

        assertEquals(false, arrayList.isEmpty());
    }

    @Test
    public void isEmptyException() {
        // No cases to test here.
    }

    @Test
    public void isFullZero() {
        assertEquals(false, arrayList.isFull());
    }

    @Test
    public void isFullOne() {
        arrayList.add("a");
        assertEquals(false, arrayList.isFull());

        arrayList = new ArrayList<>();

        arrayList.add("");
        assertEquals(false, arrayList.isFull());

        arrayList = new ArrayList<>();

        arrayList.add(null);
        assertEquals(false, arrayList.isFull());
    }

    @Test
    public void isFullMany() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertEquals(false, arrayList.isFull());
    }

    @Test
    public void isFullBoundary() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add(null);
        arrayList.add("a");
        arrayList.add("c");
        arrayList.add("d");
        // Second mistake here isFull should always return false, where as before it always returned true.
        assertEquals(false, arrayList.isFull());
    }

    @Test
    public void isFullException() {
        // No cases to test here.
    }

    @Test
    public void removeAtIndexZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(0));
    }

    @Test
    public void removeAtIndexOne() {
        arrayList.add("a");
        assertEquals("a", arrayList.remove(0));
    }

    @Test
    public void removeAtIndexMany() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertEquals(null, arrayList.remove(3));
        assertEquals("a", arrayList.remove(3));
    }

    @Test
    public void removeAtIndexBoundary() {
        arrayList.add("a");
        arrayList.remove(0);
        assertEquals(0, arrayList.size());
    }

    @Test
    public void removeAtIndexException() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(8));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.remove(-2));
    }

    @Test
    public void removeZero() {
        assertThrows(IllegalStateException.class, () -> arrayList.remove("a"));
        assertThrows(IllegalStateException.class, () -> arrayList.remove(""));
        assertThrows(IllegalStateException.class, () -> arrayList.remove(null));
    }

    @Test
    public void removeOne() {
        arrayList.add("a");
        arrayList.remove("a");
        assertEquals(0, arrayList.size());
    }

    @Test
    public void removeMany() {
        arrayList.add("");
        arrayList.add("b");
        arrayList.add("a");
        arrayList.add(null);
        arrayList.add("a");
        assertEquals(null, arrayList.remove(null));
        assertEquals("a", arrayList.remove("a"));
    }

    @Test
    public void removeBoundary() {
        arrayList.add("a");
        arrayList.remove("a");
        assertEquals(0, arrayList.size());
    }

    @Test
    public void removeException() {
        arrayList.add("");
        arrayList.add("b");
        assertThrows(IllegalStateException.class, () -> arrayList.remove("a"));
        assertThrows(IllegalStateException.class, () -> arrayList.remove(null));
    }

    @Test
    public void setZero() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(0, "a"));
    }

    @Test
    public void setOne() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, "a");
        arrayList.set(3, null);
        assertEquals(null, arrayList.get(3));
    }

    @Test
    public void setMany() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        arrayList.set(2, "a");
        arrayList.set(2, "c");
        assertEquals("c", arrayList.get(2));
    }

    @Test
    public void setBoundary() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        arrayList.set(2, "a");
        arrayList.set(2, "c");
        arrayList.set(2, "");
        assertEquals("", arrayList.get(2));
    }

    @Test
    public void setException() {
        assertThrows(IndexOutOfBoundsException.class, () -> arrayList.set(3, null));
    }

    @Test
    public void sizeZero() {
        assertEquals(0, arrayList.size());
    }

    @Test
    public void sizeOne() {
        arrayList.add("a");
        assertEquals(1, arrayList.size());
    }

    @Test
    public void sizeMany() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(3, null);
        arrayList.add(4, "a");
        assertEquals(5, arrayList.size());
    }

    @Test
    public void sizeBoundary() {
        for (int i = 0; i < 101; i++) arrayList.add("a");
        assertEquals(101, arrayList.size());

    }

    @Test
    public void sizeException() {
        // No cases to test here.
    }

    @Test
    public void toStringZero() {
        assertEquals("{}", arrayList.toString());
    }

    @Test
    public void toStringOne() {
        arrayList.add("b");
        assertEquals("{b}", arrayList.toString());

        arrayList = new ArrayList<>();

        arrayList.add("");
        assertEquals("{}", arrayList.toString());

        arrayList = new ArrayList<>();

        arrayList.add(null);
        assertEquals("{null}", arrayList.toString());
    }

    @Test
    public void toStringMany() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        assertEquals("{, b, a}", arrayList.toString());
    }

    @Test
    public void toStringBoundary() {
        arrayList.add(0, "");
        arrayList.add(1, "b");
        arrayList.add(2, "a");
        arrayList.add(2, "a");
        assertEquals("{, b, a, a}", arrayList.toString());
    }

    @Test
    public void toStringException() {
        // No cases to test here.
    }
}
