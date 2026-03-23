package edu.grinnell.csc207.soundsofsorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A collection of indices into a Scale object.  These indices are the subject
 * of the various sorting algorithms in the program.
 */
public class NoteIndices {

    private Integer[] indices;
    private boolean[] highlighted;

    /** Constructs a new, empty note indices collection. */
    public NoteIndices() {
        indices = new Integer[0];
        highlighted = new boolean[0];
    }
    
    /**
     * (Re-)initializes this collection of indices to map into a new scale
     * object of the given size. The collection is also shuffled to provide an
     * initial starting point for the sorting process.
     * @param n the size of the scale object that these indices map into
     */
    public void initialize(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        highlighted = new boolean[n];
        for (int i = 0; i < n; i++) {
            list.add(i);
            highlighted[i] = false;
        }
        Collections.shuffle(list);
        indices = list.toArray(new Integer[list.size()]);
    }
    
    /** @return the indices of this NoteIndices object */
    public Integer[] getNotes() { 
        return indices;
    }
    
    /**
     * Highlights the given index of the note array
     * @param index the index to highlight
     */
    public void highlightNote(int index) {
        highlighted[index] = true;
    }
    
    /**
     * @param index the index to check
     * @return true if the given index is highlighted
     */
    public boolean isHighlighted(int index) {
        return highlighted[index];
    }
    
    /** Clears all highlighted indices from this collection */
    public void clearAllHighlighted() {
        for (int i = 0; i < highlighted.length; i++) {
            highlighted[i] = false;
        }
    }
}
