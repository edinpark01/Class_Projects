package exercise_03;

/*  Author: Braulio Tonaco
 *  Date:   07/16/2018
 *
 *  Given an array a of n numbers, consider the problem of computing the average of the first i number,
 *  for i ranging from 0 to n - 1. That is, compute the array b of length n, where:
 *
 *  b[i]    =   a[0] + a[1] + ... + a[i]        0 <= i <= n
 *              ------------------------
 *                        i + 1
 *
 *  Let us assume that all numbers are integer, and so averages are computed with no fractional part;
 *
 *  For example:
 *      ( 2 + 5 + 1 ) / 3 = 2
 *
 *  Your program should use O(1) memory; in particular, it may not use an array of length proportional to n.
 *
 *  For example:
 *
 *  Input:          3 + 1 + 7 + 4 + 6
 *  Correct output: 4
 *
 *  This is because the prefix averages are:
 *  i = 0
 *  ( 3 ) / ( 1 + 0 ) = 3
 *
 *  i = 1
 *  ( 3 + 1 ) / ( 1 + 1 ) = 2
 *
 *  i = 2
 *  ( 3 + 1 + 7 ) / ( 1 + 2 ) = 2
 *
 *  i = 3
 *  ( 3 + 1 + 7 + 4 ) / ( 1 + 3 ) = 3
 *
 *  i = 4
 *  ( 3 + 1 + 7 + 4 + 6 ) / ( 1 + 4 ) = 4
 *
 */

import java.util.Arrays;

public class problem_set_03 {
    public static void main(String[] args) {
        int arrays[][] = { {3, 1, 7, 4, 6}, {2, 5, 1} };

        for( int[] array : arrays ){
            int max = max_avg(array);
            System.out.println(Arrays.toString(array) + " -> Max average: " + max + "\n");
        }
    }

    private static int max_avg(int[] arr){
        int temp_sum = 0;
        int max = 0;

        for( int i = 0; i < arr.length; i++ ){
            temp_sum += arr[i];

            int temp_avg = temp_sum / ( i + 1 );

            if( temp_avg > max )
                max = temp_avg;
        }
        return max;
    }

}


