package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for {@link IntegerSet}.
 *
 * <p>Every method is tested with both a normal case and at least one edge case
 * as required by the Assignment 6 specification.</p>
 *
 * @author Neayla Jones
 * @version 1.0
 */
public class IntegerSetTest {

    /** Primary test set, reset before each test. Default: {1, 2, 3} */
    private IntegerSet setA;

    /** Secondary test set, reset before each test. Default: {2, 3, 4} */
    private IntegerSet setB;

    /**
     * Rebuilds setA = {1, 2, 3} and setB = {2, 3, 4} before every test.
     */
    @BeforeEach
    void setUp() {
        setA = new IntegerSet();
        setB = new IntegerSet();

        setA.add(1);
        setA.add(2);
        setA.add(3);

        setB.add(2);
        setB.add(3);
        setB.add(4);
    }

    // -----------------------------------------------------------------------
    // clear()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("clear() - normal: removes all elements from a populated set")
    void testClearNormal() {
        setA.clear();
        assertTrue(setA.isEmpty());
        assertEquals(0, setA.length());
    }

    @Test
    @DisplayName("clear() - edge: calling clear() on an already-empty set")
    void testClearEdgeAlreadyEmpty() {
        IntegerSet empty = new IntegerSet();
        empty.clear();
        assertTrue(empty.isEmpty());
    }

    // -----------------------------------------------------------------------
    // length()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("length() - normal: returns correct count for populated set")
    void testLengthNormal() {
        assertEquals(3, setA.length());
    }

    @Test
    @DisplayName("length() - edge: returns 0 for an empty set")
    void testLengthEdgeEmpty() {
        assertEquals(0, new IntegerSet().length());
    }

    // -----------------------------------------------------------------------
    // isEmpty()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("isEmpty() - normal: returns false for a non-empty set")
    void testIsEmptyNormal() {
        assertFalse(setA.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() - edge: returns true for a new empty set")
    void testIsEmptyEdgeEmpty() {
        assertTrue(new IntegerSet().isEmpty());
    }

    // -----------------------------------------------------------------------
    // contains()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("contains() - normal: returns true for a value present in the set")
    void testContainsNormal() {
        assertTrue(setA.contains(2));
    }

    @Test
    @DisplayName("contains() - edge: returns false for a value NOT present in the set")
    void testContainsEdgeNotPresent() {
        assertFalse(setA.contains(99));
    }

    // -----------------------------------------------------------------------
    // add()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("add() - normal: inserts a new element into the set")
    void testAddNormal() {
        setA.add(10);
        assertTrue(setA.contains(10));
        assertEquals(4, setA.length());
    }

    @Test
    @DisplayName("add() - edge: duplicate value is not inserted")
    void testAddEdgeDuplicate() {
        int before = setA.length();
        setA.add(1); // 1 already exists in setA
        assertEquals(before, setA.length());
    }

    // -----------------------------------------------------------------------
    // remove()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("remove() - normal: removes an existing element")
    void testRemoveNormal() {
        setA.remove(2);
        assertFalse(setA.contains(2));
        assertEquals(2, setA.length());
    }

    @Test
    @DisplayName("remove() - edge: removing a value not in the set leaves it unchanged")
    void testRemoveEdgeNotPresent() {
        int before = setA.length();
        setA.remove(99);
        assertEquals(before, setA.length());
    }

    // -----------------------------------------------------------------------
    // largest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("largest() - normal: returns the maximum element")
    void testLargestNormal() {
        assertEquals(3, setA.largest());
    }

    @Test
    @DisplayName("largest() - edge: single-element set returns that element")
    void testLargestEdgeSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(7);
        assertEquals(7, s.largest());
    }

    @Test
    @DisplayName("largest() - edge: throws IntegerSetException on empty set")
    void testLargestEdgeEmpty() {
        assertThrows(IntegerSet.IntegerSetException.class,
                () -> new IntegerSet().largest());
    }

    // -----------------------------------------------------------------------
    // smallest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("smallest() - normal: returns the minimum element")
    void testSmallestNormal() {
        assertEquals(1, setA.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: single-element set returns that element")
    void testSmallestEdgeSingleElement() {
        IntegerSet s = new IntegerSet();
        s.add(-5);
        assertEquals(-5, s.smallest());
    }

    @Test
    @DisplayName("smallest() - edge: throws IntegerSetException on empty set")
    void testSmallestEdgeEmpty() {
        assertThrows(IntegerSet.IntegerSetException.class,
                () -> new IntegerSet().smallest());
    }

    // -----------------------------------------------------------------------
    // equals()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("equals() - normal: returns true for identical sets")
    void testEqualsNormal() {
        IntegerSet other = new IntegerSet();
        other.add(1); other.add(2); other.add(3);
        assertTrue(setA.equals(other));
    }

    @Test
    @DisplayName("equals() - edge: same elements in different order returns true")
    void testEqualsEdgeDifferentOrder() {
        IntegerSet other = new IntegerSet();
        other.add(3); other.add(1); other.add(2);
        assertTrue(setA.equals(other));
    }

    @Test
    @DisplayName("equals() - edge: sets with different elements returns false")
    void testEqualsEdgeMismatch() {
        assertFalse(setA.equals(setB));
    }

    // -----------------------------------------------------------------------
    // union()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("union() - normal: contains all elements from both sets without duplicates")
    void testUnionNormal() {
        IntegerSet result = setA.union(setB);
        // {1,2,3} ∪ {2,3,4} = {1,2,3,4}
        assertEquals(4, result.length());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

    @Test
    @DisplayName("union() - edge: union with an empty set returns copy of this set")
    void testUnionEdgeWithEmpty() {
        IntegerSet result = setA.union(new IntegerSet());
        assertTrue(result.equals(setA));
    }

    // -----------------------------------------------------------------------
    // intersect()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("intersect() - normal: returns only common elements")
    void testIntersectNormal() {
        IntegerSet result = setA.intersect(setB);
        // {1,2,3} ∩ {2,3,4} = {2,3}
        assertEquals(2, result.length());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertFalse(result.contains(1));
        assertFalse(result.contains(4));
    }

    @Test
    @DisplayName("intersect() - edge: disjoint sets return an empty set")
    void testIntersectEdgeNoOverlap() {
        IntegerSet c = new IntegerSet();
        c.add(10); c.add(20);
        assertTrue(setA.intersect(c).isEmpty());
    }

    // -----------------------------------------------------------------------
    // diff()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("diff() - normal: returns elements in this set but not in the other")
    void testDiffNormal() {
        IntegerSet result = setA.diff(setB);
        // {1,2,3} ∖ {2,3,4} = {1}
        assertEquals(1, result.length());
        assertTrue(result.contains(1));
    }

    @Test
    @DisplayName("diff() - edge: diff of identical sets returns empty set")
    void testDiffEdgeIdenticalSets() {
        assertTrue(setA.diff(setA).isEmpty());
    }

    // -----------------------------------------------------------------------
    // complement()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("complement() - normal: returns elements in b but not in this set")
    void testComplementNormal() {
        IntegerSet result = setA.complement(setB);
        // complement of {1,2,3} in {2,3,4} = {4}
        assertEquals(1, result.length());
        assertTrue(result.contains(4));
    }

    @Test
    @DisplayName("complement() - edge: disjoint sets return all of b")
    void testComplementEdgeDisjoint() {
        IntegerSet a = new IntegerSet();
        a.add(1); a.add(2);
        IntegerSet b = new IntegerSet();
        b.add(10); b.add(20);
        // complement of {1,2} in {10,20} = {10,20}
        IntegerSet result = a.complement(b);
        assertTrue(result.equals(b));
    }

    // -----------------------------------------------------------------------
    // toString()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("toString() - normal: returns elements in ascending order")
    void testToStringNormal() {
        IntegerSet s = new IntegerSet();
        s.add(3); s.add(1); s.add(2);
        assertEquals("[1, 2, 3]", s.toString());
    }

    @Test
    @DisplayName("toString() - edge: empty set returns []")
    void testToStringEdgeEmpty() {
        assertEquals("[]", new IntegerSet().toString());
    }

    // -----------------------------------------------------------------------
    // Exception tests (standalone block per rubric)
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Exception: largest() throws on empty set")
    void testExceptionLargest() {
        assertThrows(IntegerSet.IntegerSetException.class,
                () -> new IntegerSet().largest());
    }

    @Test
    @DisplayName("Exception: smallest() throws on empty set")
    void testExceptionSmallest() {
        assertThrows(IntegerSet.IntegerSetException.class,
                () -> new IntegerSet().smallest());
    }
}