package exercise_01;

/*  Author: Braulio Tonaco
*   Date:   07/16/2018
*
*   Exercise 1 - STEP 1: Write a recursive method that take a string as argument and returns the reverse of the string
*                        Given the argument 'Horse' your method should return the string 'esroH'
*                STEP 2: Use the recursive method from STEP 1 to test if an input string is a palindrome.
*                STEP 3: The output is True if the string is a palindrome and False if it is not.
*
*                NOTE: Palindrome is a word, phrase, or sequence that reads the same backward as forward
*
*   Exercise 2 - STEP 1: Write a recursive method to determine if an array of integers contains an integer that is
*                        the sum of two integers appearing earlier in the array; that is:
*                               - For some i, A[i] = A[j] + A[k] for some j, k < i.
*                STEP 2: The output is True if one of the integers is the sum of two previous ones, and
*                        False otherwise.
* */
import java.util.Arrays;

public class problem_set_01 {

    public static void main(String[] args) {
    
        exercise_01();
        System.out.println();
        exercise_02();
        
    }

    private static void exercise_01(){
        System.out.println("EXERCISE 01");

        String[] str_list = {"Horse", "civic", "kayak", "sagas", "not a palindrome"};

        for (String str : str_list){
            String reversed_str = recursive_reverse(str, str.length() - 1 );

            String result = ( str.equals(reversed_str) ) ? "True" : "False";

            System.out.println("Is '" + str + "' a palindrome? " + result);
        }       
    }

    private static void exercise_02() {
        System.out.println("EXERCISE 02");
        int[] good_int_lst = {5, 2, 4, 1, 6, 8, 1};
        int[] bad_int_lst = {7, 6, 5, 4};

        System.out.println(Arrays.toString(good_int_lst));
        System.out.println(SumOfPrevious(good_int_lst, 0, 1, 2));

        System.out.println();

        System.out.println(Arrays.toString(bad_int_lst));
        System.out.println(SumOfPrevious(bad_int_lst, 0, 1, 2));


    }

    private static boolean SumOfPrevious(int[] A, int k, int j, int i) {
        if ( A[i] == A[j] + A[k]) {
            return true;
        }

        if ( j + 1 < i ) return SumOfPrevious(A, k, j + 1, i);
        if ( k + 1 < j ) return SumOfPrevious(A, k + 1, j, i);
        if ( i + 1 < A.length ) return SumOfPrevious(A, k, j, i + 1);
        return false;
    }


    private static String recursive_reverse(String str, int n) {
        if ( n == 0 )
            return String.valueOf(str.charAt(n));
        else
            return String.valueOf(str.charAt(n)) + recursive_reverse(str, n - 1);
    }
}
