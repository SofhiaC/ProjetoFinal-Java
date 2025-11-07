//implementa ordenação por nome
/*
 * Quicksort (A as array, low as int, high as int)
 *      if (low < high)
 *          pivot_location = Partition(A, low, high)
 *              Quicksort(A, low, pivot_location)
 *              Quicksort(A, pivot_location + 1, high)
 * Partition (A as array, low as int, high as int)
 *      pivot = A[low]
 *      leftwall = low
 *      for i = low + 1 to high
 *          if (A[i] < pivot) then
 *              swap (A[i], A[leftwall])
 *              leftwall = leftwall + 1
 *      swap (pivot, A[leftwall])
 *      return(leftwall)
 * --> 0(n log n) se escolher o pivot certo se não é 0(n^2)
 */

public class QuickSort {
}
