package com.example.quizgame;

public class QuickSort {

    //int comparisonCount = 0;

    //swaps two given elements in an array
    void swap(String array[], int a, int b) {
        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }//swap

    int partition(String array[], int min, int max) {
        String pivot = array[max];

        int i = (min - 1);

        for (int j = min; j <= max - 1; j++) {
            //comparisonCount++;
            int result = array[j].compareToIgnoreCase(pivot);
            if (result < 0) {
                //comparisonCount++;
                i++;
                swap(array, i, j);
            }//if
        }//for

        //makes use of swap to swap array[i+1] with array[max]
        swap(array, i + 1, max);
        return (i + 1);
    }//jediPartition

    //will sort the given array by making use of partition()
    void quickSort(String array[], int min, int max) {
        if (min < max) {
            //comparisonCount++;
            //the location within the array where the partition is taking place
            int partLocation = partition(array, min, max);

            //sort the elements before and after the partition location
            quickSort(array, min, partLocation - 1);
            quickSort(array, partLocation + 1, max);
        }//if
    }//quickSort
}
