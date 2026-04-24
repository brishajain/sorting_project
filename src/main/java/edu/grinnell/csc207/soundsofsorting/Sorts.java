package edu.grinnell.csc207.soundsofsorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.grinnell.csc207.soundsofsorting.events.CompareEvent;
import edu.grinnell.csc207.soundsofsorting.events.CopyEvent;
import edu.grinnell.csc207.soundsofsorting.events.SwapEvent;
import edu.grinnell.csc207.soundsofsorting.events.SortEvent;

/**
 * A collection of sorting algorithms.
 */
public class Sorts {
    public static <T> void swap(T[] arr, int i, int j) {
        T tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static <T extends Comparable<? super T>> List<SortEvent<T>> bubbleSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                events.add(new CompareEvent<T>(j, j + 1));

                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    events.add(new SwapEvent<T>(j, j + 1));
                    swap(arr, j, j + 1);
                }
            }
        }

        return events;
    }

    public static <T extends Comparable<? super T>> List<SortEvent<T>> selectionSort(
            T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();

        for (int j = 0; j < arr.length; j++) {
            int tracker = j;

            for (int i = j + 1; i < arr.length; i++) {
                events.add(new CompareEvent<T>(tracker, i));

                if (arr[tracker].compareTo(arr[i]) > 0) {
                    tracker = i;
                }
            }

            events.add(new SwapEvent<T>(tracker, j));
            swap(arr, tracker, j);
        }

        return events;
    }

    public static <T extends Comparable<? super T>> List<SortEvent<T>> insertionSort(
            T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();

        for (int j = 0; j < arr.length; j++) {
            int i = 0;

            for (; i < j; i++) {
                events.add(new CompareEvent<T>(i, j));

                if (arr[i].compareTo(arr[j]) > 0) {
                    break;
                }
            }

            for (int w = j; w > i; w--) {
                events.add(new SwapEvent<T>(w, w - 1));
                swap(arr, w, w - 1);
            }
        }

        return events;
    }

    public static <T extends Comparable<? super T>> List<SortEvent<T>> mergeSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();
        T[] shadow = Arrays.copyOf(arr, arr.length);

        mergeHelp(arr, shadow, 0, arr.length, events);

        return events;
    }

    private static <T extends Comparable<? super T>> void mergeHelp(
            T[] arr, T[] shadow, int beg, int end, List<SortEvent<T>> events) {
        if (end - beg <= 1) {
            return;
        }

        int mid = (beg + end) / 2;

        mergeHelp(arr, shadow, beg, mid, events);
        mergeHelp(arr, shadow, mid, end, events);

        int leftIn = beg;
        int rightIn = mid;
        int shadowIn = beg;

        while (leftIn < mid && rightIn < end) {
            events.add(new CompareEvent<T>(leftIn, rightIn));

            if (arr[leftIn].compareTo(arr[rightIn]) <= 0) {
                shadow[shadowIn] = arr[leftIn];
                shadowIn++;
                leftIn++;
            } else {
                shadow[shadowIn] = arr[rightIn];
                shadowIn++;
                rightIn++;
            }
        }

        while (leftIn < mid) {
            shadow[shadowIn] = arr[leftIn];
            shadowIn++;
            leftIn++;
        }

        while (rightIn < end) {
            shadow[shadowIn] = arr[rightIn];
            shadowIn++;
            rightIn++;
        }

        for (int i = beg; i < end; i++) {
            events.add(new CopyEvent<T>(shadow[i], i));
            arr[i] = shadow[i];
        }
    }

    public static <T extends Comparable<? super T>> List<SortEvent<T>> quickSort(T[] arr) {
        List<SortEvent<T>> events = new ArrayList<>();

        quickSortHelp(arr, 0, arr.length - 1, events);

        return events;
    }

    private static <T extends Comparable<? super T>> void quickSortHelp(
            T[] arr, int beg, int end, List<SortEvent<T>> events) {
        if (beg >= end) {
            return;
        }

        int mid = (beg + end) / 2;

        events.add(new SwapEvent<T>(mid, end));
        swap(arr, mid, end);

        int leftpoint = beg;
        int rightpoint = end - 1;

        while (leftpoint <= rightpoint) {
            while (leftpoint <= rightpoint) {
                events.add(new CompareEvent<T>(leftpoint, end));

                if (arr[leftpoint].compareTo(arr[end]) < 0) {
                    leftpoint++;
                } else {
                    break;
                }
            }

            while (leftpoint <= rightpoint) {
                events.add(new CompareEvent<T>(rightpoint, end));

                if (arr[rightpoint].compareTo(arr[end]) >= 0) {
                    rightpoint--;
                } else {
                    break;
                }
            }

            if (leftpoint < rightpoint) {
                events.add(new SwapEvent<T>(leftpoint, rightpoint));
                swap(arr, leftpoint, rightpoint);
                leftpoint++;
                rightpoint--;
            }
        }

        events.add(new SwapEvent<T>(leftpoint, end));
        swap(arr, leftpoint, end);

        quickSortHelp(arr, beg, leftpoint - 1, events);
        quickSortHelp(arr, leftpoint + 1, end, events);
    }

    public static <T extends Comparable<? super T>> void eventSort(
            List<SortEvent<T>> events, T[] arr) {
        for (SortEvent<T> event : events) {
            event.apply(arr);
        }
    }
}