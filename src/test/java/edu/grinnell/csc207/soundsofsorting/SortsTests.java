package edu.grinnell.csc207.soundsofsorting;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.soundsofsorting.Sorts;
import edu.grinnell.csc207.soundsofsorting.events.SortEvent;

public class SortsTests {
    /**
     * @param <T> the carrier type of the array
     * @param arr the array to check
     * @return true iff <code>arr</code> is sorted.
     */
    public static <T extends Comparable<? super T>> boolean sorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] makeTestArray() {
        return new Integer[] {
            3, 7, 9, 1, 2,
            18, 16, 15, 19, 8,
            14, 12, 5, 13, 4,
            6, 0, 17, 11, 10
        };
    }

    public void testSort(Consumer<Integer[]> func) {
        Integer[] arr = makeTestArray();
        func.accept(arr);
        assertTrue(sorted(arr));
    }

    public void testEvents(Function<Integer[], List<SortEvent<Integer>>> func) {
        Integer[] arr1 = makeTestArray();
        Integer[] arr2 = makeTestArray();
        List<SortEvent<Integer>> events = func.apply(arr1);
        // Check if the sorting algorithm works!
        assertTrue(sorted(arr1));
        // Check if the sorting algorithm generated the correct events!
        Sorts.eventSort(events, arr2);
        assertTrue(sorted(arr2));
    }

    @Test
    public void testBubbleSort() {
        testSort(Sorts::bubbleSort);
        testEvents(Sorts::bubbleSort);
    }
    
    @Test
    public void testInsertionSort() {
        testSort(Sorts::insertionSort);
    }
    
    @Test
    public void testSelectionSort() {
        testSort(Sorts::selectionSort);
    }

    @Test
    public void testMergeSort() {
        testSort(Sorts::mergeSort);
    }
    
    @Test
    public void testQuickSort() {
        testSort(Sorts::quickSort);
    }
}