package edu.grinnell.csc207.soundsofsorting.events;

import java.util.List;

/**
 * A <code>CopyEvent</code> logs a copy of a value into an index of the array.
 */
public class CopyEvent<T> implements SortEvent<T>
{
    private T value;
    private int destination;

    public CopyEvent (T val, int dest){
        value = val;
        destination = dest;
    }

    public void apply(T[] arr)
    {
        arr[destination] = value;
    }

    public List<Integer> getAffectedIndices()
    {
        
    }

    public boolean isEmphasized()
    {
        return true;
    }
}
