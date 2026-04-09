package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Name: Odunayo Oluokun
 * Models a mathematical set of integers.
 *
 * <p>A set contains no duplicate values and supports standard set operations
 * such as union, intersection, difference, and complement.</p>
 */
public class IntegerSet {
    /** Internal storage for set elements. */
    private final ArrayList<Integer> set = new ArrayList<>();

    /**
     * Constructs an empty IntegerSet.
     */
    public IntegerSet() {
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the set size
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set contains exactly the same elements as another set.
     * Element order does not matter.
     *
     * @param b the other set
     * @return true if both sets are equal; false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        if (this.length() != b.length()) {
            return false;
        }
        return getSortedCopy(this.set).equals(getSortedCopy(b.set));
    }

    /**
     * Supports normal Java equality checks in addition to equals(IntegerSet).
     *
     * @param obj the object to compare
     * @return true if equal; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntegerSet)) {
            return false;
        }
        return equals((IntegerSet) obj);
    }

    /**
     * Returns a hash code consistent with equals.
     *
     * @return the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(getSortedCopy(this.set));
    }

    /**
     * Returns true if the set contains the specified value.
     *
     * @param value the value to search for
     * @return true if present; false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     *
     * @return the largest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        checkNotEmpty();
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return the smallest integer in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        checkNotEmpty();
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     *
     * @param item the value to add
     */
    public void add(int item) {
        if (!contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it exists.
     *
     * @param item the value to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set containing all elements that appear in either set.
     * Neither original set is modified.
     *
     * @param intSetb the other set
     * @return a new set representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        for (Integer value : this.set) {
            result.add(value);
        }

        if (intSetb != null) {
            for (Integer value : intSetb.set) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing only the elements common to both sets.
     * Neither original set is modified.
     *
     * @param intSetb the other set
     * @return a new set representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (Integer value : this.set) {
            if (intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in the current set but not in the other set.
     * Neither original set is modified.
     *
     * @param intSetb the other set
     * @return a new set representing the difference
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            for (Integer value : this.set) {
                result.add(value);
            }
            return result;
        }

        for (Integer value : this.set) {
            if (!intSetb.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns a new set containing elements in the other set but not in the current set.
     * Neither original set is modified.
     *
     * @param intSetb the other set
     * @return a new set representing the complement of this set relative to the other set
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();

        if (intSetb == null) {
            return result;
        }

        for (Integer value : intSetb.set) {
            if (!this.contains(value)) {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * Returns true if the set has no elements.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns the set in ascending order using the required format.
     * Example: [1, 2, 3]
     *
     * @return the string representation of the set
     */
    @Override
    public String toString() {
        return getSortedCopy(this.set).toString();
    }

    /**
     * Throws an exception if the set is empty.
     */
    private void checkNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Set is empty.");
        }
    }

    /**
     * Creates a sorted copy of the provided list.
     *
     * @param source the list to copy and sort
     * @return a sorted copy of the input list
     */
    private List<Integer> getSortedCopy(List<Integer> source) {
        ArrayList<Integer> copy = new ArrayList<>(source);
        Collections.sort(copy);
        return copy;
    }
}