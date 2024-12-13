package pro.sky.algorithms.list.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.algorithms.list.service.StringList;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private StringList stringList;

    @BeforeEach
    void setUp() {
        stringList = new StringListImpl(10);
    }

    @Test
    void testGet() {
        stringList.add("Hello");
        assertEquals("Hello", stringList.get(0));
    }

    @Test
    void testSize() {
        stringList.add("Hello");
        stringList.add("World");
        assertEquals(2, stringList.size());
    }

    @Test
    void testIsEmpty() {
        assertTrue(stringList.isEmpty());
        stringList.add("Hello");
        assertFalse(stringList.isEmpty());
    }

    @Test
    void testClear() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void testIndexOf() {
        stringList.add("Hello");
        stringList.add("World");
        assertEquals(0, stringList.indexOf("Hello"));
        assertEquals(1, stringList.indexOf("World"));
        assertEquals(-1, stringList.indexOf("Java"));
    }

    @Test
    void testLastIndexOf() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.add("Hello");
        assertEquals(2, stringList.lastIndexOf("Hello"));
        assertEquals(1, stringList.lastIndexOf("World"));
        assertEquals(-1, stringList.lastIndexOf("Java"));
    }

    @Test
    void testContains() {
        stringList.add("Hello");
        stringList.add("World");
        assertTrue(stringList.contains("Hello"));
        assertTrue(stringList.contains("World"));
        assertFalse(stringList.contains("Java"));
    }

    @Test
    void testAdd() {
        stringList.add("Hello");
        assertEquals("Hello", stringList.get(0));
    }

    @Test
    void testAddAtIndex() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.add(1, "Java");
        assertEquals("Hello", stringList.get(0));
        assertEquals("Java", stringList.get(1));
        assertEquals("World", stringList.get(2));
    }

    @Test
    void testSet() {
        stringList.add("Hello");
        stringList.set(0, "Java");
        assertEquals("Java", stringList.get(0));
    }

    @Test
    void testRemove() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.remove("World");
        assertEquals("Hello", stringList.get(0));
        assertEquals(1, stringList.size());
    }

    @Test
    void testRemoveAtIndex() {
        stringList.add("Hello");
        stringList.add("World");
        stringList.remove(1);
        assertEquals("Hello", stringList.get(0));
        assertEquals(1, stringList.size());
    }

    @Test
    void testEquals() {
        StringList anotherList = new StringListImpl(10);
        anotherList.add("Hello");
        stringList.add("Hello");
        assertTrue(stringList.equals(anotherList));
    }

    @Test
    void testToArray() {
        stringList.add("Hello");
        stringList.add("World");
        assertArrayEquals(new String[]{"Hello", "World"}, stringList.toArray());
    }

    @Test
    void testToString() {
        stringList.add("Hello");
        stringList.add("World");
        assertEquals("(Hello, World)", stringList.toString());
    }
}
