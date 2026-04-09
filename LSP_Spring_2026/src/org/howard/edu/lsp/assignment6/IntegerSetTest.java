package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IntegerSetTest {

    private IntegerSet makeSet(int... values) {
        IntegerSet set = new IntegerSet();
        for (int value : values) {
            set.add(value);
        }
        return set;
    }

    @Test
    void testClearNormal() {
        IntegerSet set = makeSet(1, 2, 3);
        set.clear();
        assertEquals("[]", set.toString());
    }

    @Test
    void testClearEdgeAlreadyEmpty() {
        IntegerSet set = new IntegerSet();
        set.clear();
        assertTrue(set.isEmpty());
    }

    @Test
    void testLengthNormal() {
        IntegerSet set = makeSet(1, 2, 3);
        assertEquals(3, set.length());
    }

    @Test
    void testLengthEdgeEmpty() {
        IntegerSet set = new IntegerSet();
        assertEquals(0, set.length());
    }

    @Test
    void testEqualsNormalSameElementsDifferentOrder() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(3, 2, 1);
        assertTrue(a.equals(b));
    }

    @Test
    void testEqualsEdgeMismatch() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(1, 2);
        assertFalse(a.equals(b));
    }

    @Test
    void testContainsNormalPresent() {
        IntegerSet set = makeSet(1, 2, 3);
        assertTrue(set.contains(2));
    }

    @Test
    void testContainsEdgeAbsent() {
        IntegerSet set = makeSet(1, 2, 3);
        assertFalse(set.contains(9));
    }

    @Test
    void testLargestNormal() {
        IntegerSet set = makeSet(4, 9, 1, 6);
        assertEquals(9, set.largest());
    }

    @Test
    void testLargestEdgeSingleElement() {
        IntegerSet set = makeSet(5);
        assertEquals(5, set.largest());
    }

    @Test
    void testLargestEdgeEmptyException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::largest);
    }

    @Test
    void testSmallestNormal() {
        IntegerSet set = makeSet(4, 9, 1, 6);
        assertEquals(1, set.smallest());
    }

    @Test
    void testSmallestEdgeSingleElement() {
        IntegerSet set = makeSet(5);
        assertEquals(5, set.smallest());
    }

    @Test
    void testSmallestEdgeEmptyException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, set::smallest);
    }

    @Test
    void testAddNormal() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        assertTrue(set.contains(7));
    }

    @Test
    void testAddEdgeDuplicateValue() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        set.add(7);
        assertEquals(1, set.length());
        assertEquals("[7]", set.toString());
    }

    @Test
    void testRemoveNormal() {
        IntegerSet set = makeSet(1, 2, 3);
        set.remove(2);
        assertEquals("[1, 3]", set.toString());
    }

    @Test
    void testRemoveEdgeValueNotPresent() {
        IntegerSet set = makeSet(1, 2, 3);
        set.remove(9);
        assertEquals("[1, 2, 3]", set.toString());
    }

    @Test
    void testUnionNormal() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(3, 4, 5);
        IntegerSet result = a.union(b);
        assertEquals("[1, 2, 3, 4, 5]", result.toString());
    }

    @Test
    void testUnionEdgeWithEmptySet() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = new IntegerSet();
        IntegerSet result = a.union(b);
        assertEquals("[1, 2, 3]", result.toString());
    }

    @Test
    void testIntersectNormal() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);
        IntegerSet result = a.intersect(b);
        assertEquals("[2, 3]", result.toString());
    }

    @Test
    void testIntersectEdgeNoCommonElements() {
        IntegerSet a = makeSet(1, 2);
        IntegerSet b = makeSet(3, 4);
        IntegerSet result = a.intersect(b);
        assertEquals("[]", result.toString());
    }

    @Test
    void testDiffNormal() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);
        IntegerSet result = a.diff(b);
        assertEquals("[1]", result.toString());
    }

    @Test
    void testDiffEdgeIdenticalSets() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(1, 2, 3);
        IntegerSet result = a.diff(b);
        assertEquals("[]", result.toString());
    }

    @Test
    void testComplementNormal() {
        IntegerSet a = makeSet(1, 2, 3);
        IntegerSet b = makeSet(2, 3, 4);
        IntegerSet result = a.complement(b);
        assertEquals("[4]", result.toString());
    }

    @Test
    void testComplementEdgeDisjointSets() {
        IntegerSet a = makeSet(1, 2);
        IntegerSet b = makeSet(3, 4);
        IntegerSet result = a.complement(b);
        assertEquals("[3, 4]", result.toString());
    }

    @Test
    void testIsEmptyNormalEmptySet() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());
    }

    @Test
    void testIsEmptyEdgeNonEmptySet() {
        IntegerSet set = makeSet(1);
        assertFalse(set.isEmpty());
    }

    @Test
    void testToStringNormal() {
        IntegerSet set = makeSet(3, 1, 2);
        assertEquals("[1, 2, 3]", set.toString());
    }

    @Test
    void testToStringEdgeEmptySet() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString());
    }
}