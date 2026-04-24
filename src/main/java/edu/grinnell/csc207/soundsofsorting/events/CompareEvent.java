package edu.grinnell.csc207.soundsofsorting.events;

import java.util.Arrays;
import java.util.List;

/**
 * A CompareEvent logs a comparison a sort makes between two indices.
 */
public class CompareEvent<T> implements SortEvent<T> {
    private int indexA;
    private int indexB;

    public CompareEvent(int a, int b) {
        indexA = a;
        indexB = b;
    }

    public void apply(T[] arr) {
        // Compare events do not change the array.
    }

    public List<Integer> getAffectedIndices() {
        return Arrays.asList(indexA, indexB);
    }

    public boolean isEmphasized() {
        return false;
    }
}