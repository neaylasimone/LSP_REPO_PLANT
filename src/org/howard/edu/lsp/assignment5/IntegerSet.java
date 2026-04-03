package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a mathematical set of integers with no duplicate values.
 * Supports standard set operations: union, intersection, difference, and complement.
 *
 * <p>All set operations return a new {@code IntegerSet} and do not modify the
 * original sets. The internal representation uses an {@link ArrayList}.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 *   IntegerSet s1 = new IntegerSet();
 *   s1.add(1); s1.add(2); s1.add(3);
 *   IntegerSet s2 = new IntegerSet();
 *   s2.add(2); s2.add(3); s2.add(4);
 *   System.out.println(s1.union(s2)); // [1, 2, 3, 4]
 * </pre>
 *
 * @author [Neayla Jones]
 * @version 1.0
 */
public class IntegerSet {

    /** Internal storage for set elements. No duplicates are permitted. */
    private ArrayList<Integer> set;

    /**
     * Constructs an empty {@code IntegerSet}.
     */
    public IntegerSet() {
        set = new ArrayList<>();
    }

    // -----------------------------------------------------------------------
    // Basic Operations
    // -----------------------------------------------------------------------

    /**
     * Removes all elements from this set, leaving it empty.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the cardinality of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns {@code true} if this set contains no elements.
     *
     * @return {@code true} if the set is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns {@code true} if this set contains the specified value.
     *
     * @param value the integer to search for
     * @return {@code true} if {@code value} is in the set; {@code false} otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Adds the specified integer to this set if it is not already present.
     * Duplicates are silently ignored.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes the specified integer from this set if it is present.
     * If the value is not in the set, the set is unchanged.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    // -----------------------------------------------------------------------
    // Min / Max
    // -----------------------------------------------------------------------

    /**
     * Returns the largest element in this set.
     *
     * @return the maximum integer value in the set
     * @throws IntegerSetException if the set is empty
     */
    public int largest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Cannot find largest element of an empty set.");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in this set.
     *
     * @return the minimum integer value in the set
     * @throws IntegerSetException if the set is empty
     */
    public int smallest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Cannot find smallest element of an empty set.");
        }
        return Collections.min(set);
    }

    // -----------------------------------------------------------------------
    // Equality
    // -----------------------------------------------------------------------

    /**
     * Returns {@code true} if this set and {@code b} contain exactly the same
     * elements, regardless of order.
     *
     * <p>Example: {@code [1, 2, 3]} equals {@code [3, 2, 1]} → {@code true}</p>
     *
     * @param b the other {@code IntegerSet} to compare against
     * @return {@code true} if both sets have identical elements; {@code false} otherwise
     */
    public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) {
            return false;
        }
        // Every element of this set must appear in b (lengths are equal,
        // so if all elements match the sets are identical).
        for (int element : set) {
            if (!b.contains(element)) {
                return false;
            }
        }
        return true;
    }

    // -----------------------------------------------------------------------
    // Set Operations
    // -----------------------------------------------------------------------

    /**
     * Returns a new {@code IntegerSet} containing all elements that appear in
     * either this set or {@code intSetb} (or both).
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * <p>Example: {@code [1,2,3].union([2,3,4])} → {@code [1,2,3,4]}</p>
     *
     * @param intSetb the other set
     * @return a new set representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);          // copy all elements from this set
        for (int element : intSetb.set) {
            result.add(element);              // add() handles duplicate prevention
        }
        return result;
    }

    /**
     * Returns a new {@code IntegerSet} containing only the elements common to
     * both this set and {@code intSetb}.
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * <p>Example: {@code [1,2,3].intersect([2,3,4])} → {@code [2,3]}</p>
     *
     * @param intSetb the other set
     * @return a new set representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        result.set.retainAll(intSetb.set);    // keep only elements also in intSetb
        return result;
    }

    /**
     * Returns a new {@code IntegerSet} containing elements that are in this set
     * but <em>not</em> in {@code intSetb} (set difference: this ∖ b).
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * <p>Example: {@code [1,2,3].diff([2,3,4])} → {@code [1]}</p>
     *
     * @param intSetb the set to subtract
     * @return a new set representing the difference (this minus intSetb)
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        result.set.removeAll(intSetb.set);    // remove every element that appears in intSetb
        return result;
    }

    /**
     * Returns a new {@code IntegerSet} containing elements that are in
     * {@code intSetb} but <em>not</em> in this set (complement of this in b).
     *
     * <p>Neither the current set nor {@code intSetb} is modified.</p>
     *
     * <p>Example: {@code [1,2,3].complement([2,3,4])} → {@code [4]}</p>
     *
     * @param intSetb the universal set to take the complement against
     * @return a new set representing the complement (intSetb minus this)
     */
    public IntegerSet complement(IntegerSet intSetb) {
        // complement is the diff in the reverse direction: intSetb ∖ this
        return intSetb.diff(this);
    }

    // -----------------------------------------------------------------------
    // toString
    // -----------------------------------------------------------------------

    /**
     * Returns a string representation of this set in ascending sorted order.
     *
     * <p>Format: {@code [v1, v2, v3]} for a non-empty set, or {@code []} for
     * an empty set. Values are separated by a comma and a single space, with
     * no leading or trailing spaces inside the brackets.</p>
     *
     * @return a formatted string of the set's elements in ascending order
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();             // ArrayList.toString() produces "[a, b, c]"
    }

    // -----------------------------------------------------------------------
    // Custom Exception (inner class for convenience)
    // -----------------------------------------------------------------------

    /**
     * Thrown when an operation is attempted on an empty {@code IntegerSet}
     * that requires at least one element (e.g., {@link IntegerSet#largest()}
     * or {@link IntegerSet#smallest()}).
     */
    public static class IntegerSetException extends RuntimeException {
        /**
         * Constructs an {@code IntegerSetException} with the specified detail message.
         *
         * @param message a description of the error
         */
        public IntegerSetException(String message) {
            super(message);
        }
    }
}