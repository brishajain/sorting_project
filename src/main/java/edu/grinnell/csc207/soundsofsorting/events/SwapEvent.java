package edu.grinnell.csc207.soundsofsorting.events;

import java.util.List;
//MOVED THE SORTS FILE INTO THE EVENTS DIRECTORY SO WE CANT ACCESS THE SWAP
//FUNCTION 

import edu.grinnell.csc207.soundsofsorting.Sorts;

/**
 * A <code>SwapEvent</code> logs a swap between two indices of the array.
 */
public class SwapEvent<T> implements SortEvent<T>
{
    private int indexA;
    private int indexB;

    public SwapEvent (int a, int b){
        indexA = a;
        indexB = b;
    }

    public void apply(T[] arr)
    {
        Sorts.swap(arr, indexA, indexB); 
    }

    public List<Integer> getAffectedIndices()
    {
        
    }

    public boolean isEmphasized()
    {
        return true;
    }
}