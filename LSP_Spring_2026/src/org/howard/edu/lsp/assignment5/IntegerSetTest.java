package org.howard.edu.lsp.assignment5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IntegerSetTest {
	/**
	 * Name: Odunayo Oluokun
	
	 */
    private IntegerSet makeSet(int... values) {
        IntegerSet set = new IntegerSet();
        for (int value : values) {
            set.add(value);
        }
        return set;
    }

    @Test
    void testAddPreventsDuplicates() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(5);
        set.add(3);

        assertEquals(2, set.length());
        assertEquals("[3, 5]", set.toString());
    }

    @Test
    void testClearAndIsEmpty() {
        IntegerSet set = makeSet(1, 2, 3);
        set.clear();

        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
        assertEquals("[]", set.toString());
    }

    @Test
    void testContains() {
        IntegerSet set = makeSet(1, 2, 3);

        assertTrue(set.contains(2));
        assertFalse(set.contains(9));
    }

    @Test
    void testLargest() {
        IntegerSet set = makeSet(7, 2, 10, 4);
        assertEquals(10, set.largest());
    }

    @Test
    void testSmallest() {
        IntegerSet set = makeSet(7, 2, 10, 4);
        assertEquals(2, set.smallest());
    }

    @Test
    void testLargestThrowsWhenEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::largest);
    }

    @Test
    void testSmallestThrowsWhenEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::smallest);
    }

    @Test
    void testRemove() {
        IntegerSet set = makeSet(1, 2, 3);
        set.remove(2);

        assertEquals("[1, 3]", set.toString());
        assertEquals(2, set.length());
        assertFalse(set.contains(2));
    }

    @Test
    void testRemoveNonExistingValueDoesNothing() {
        IntegerSet set = makeSet(1, 2, 3);
        set.remove(99);

        assertEquals("[1, 2, 3]", set.toString());
        assertEquals(3, set.length());
    }

    @Test
    void testUnion() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);

        IntegerSet result = a.union(b);

        assertEquals("[1, 2, 3, 4]", result.toString());
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[2, 3, 4]", b.toString());
    }

    @Test
    void testIntersect() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);

        IntegerSet result = a.intersect(b);

        assertEquals("[2, 3]", result.toString());
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[2, 3, 4]", b.toString());
    }

    @Test
    void testDiff() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);

        IntegerSet result = a.diff(b);

        assertEquals("[1]", result.toString());
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[2, 3, 4]", b.toString());
    }

    @Test
    void testComplement() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);

        IntegerSet result = a.complement(b);

        assertEquals("[4]", result.toString());
        assertEquals("[1, 2, 3]", a.toString());
        assertEquals("[2, 3, 4]", b.toString());
    }

    @Test
    void testEqualsWithSameElementsDifferentOrder() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(3, 2, 1);

        assertTrue(a.equals(b));
        assertEquals(a, b);
    }

    @Test
    void testEqualsFalseForDifferentSets() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(1, 2);

        assertFalse(a.equals(b));
    }

    @Test
    void testToStringSortedFormat() {
        IntegerSet set = makeSet(9, 1, 4, 2);
        assertEquals("[1, 2, 4, 9]", set.toString());
    }

    @Test
    void testEmptyToString() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString());
    }

    @Test
    void testLength() {
        IntegerSet set = makeSet(10, 20, 30);
        assertEquals(3, set.length());
    }

    @Test
    void testNullOtherSetBehavior() {
        IntegerSet a = makeSet(1, 2, 3);

        assertFalse(a.equals((IntegerSet) null));
        assertEquals("[1, 2, 3]", a.diff(null).toString());
        assertEquals("[]", a.intersect(null).toString());
        assertEquals("[]", a.complement(null).toString());
        assertEquals("[1, 2, 3]", a.union(null).toString());
    }
}