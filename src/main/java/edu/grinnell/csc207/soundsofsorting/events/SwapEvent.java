package edu.grinnell.csc207.soundsofsorting.events;

import java.util.Arrays;
import java.util.List;

import edu.grinnell.csc207.soundsofsorting.Sorts;

/**
 * A SwapEvent logs a swap between two indices of the array.
 */
public class SwapEvent<T> implements SortEvent<T> {
    private int indexA;
    private int indexB;

    public SwapEvent(int a, int b) {
        indexA = a;
        indexB = b;
    }

    public void apply(T[] arr) {
        Sorts.swap(arr, indexA, indexB);
    }

    public List<Integer> getAffectedIndices() {
        return Arrays.asList(indexA, indexB);
    }

    public boolean isEmphasized() {
        return true;
    }
}