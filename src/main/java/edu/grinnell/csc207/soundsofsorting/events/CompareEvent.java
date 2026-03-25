package edu.grinnell.csc207.soundsofsorting.events;

import java.util.List;

/**
 * A <code>CompareEvent</code> logs a comparison a sort makes between two
 * indices in the array.
 */
public class CompareEvent<T> implements SortEvent<T> 
{
    private int indexA;
    private int indexB;

    //constructor
    public CompareEvent (int a, int b){
        indexA = a;
        indexB = b;
    }

    public <T extends Comparable<? super T>> void apply(T[] arr)
    {
        arr[indexA].compareTo(arr[indexB]);
        
    }

    //How is this supposed to work/factor into CompareEvents implementation?
    public List<Integer> getAffectedIndices()
    {
        List<Integer> affectedIndices = new List<Integer>; 

        //somewhere in here add new elements indexA and indexB into this list

        return affectedIndices;
    }

    public boolean isEmphasized()
    {
        return false;
    }
}
    
