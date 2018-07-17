/*  Author: Braulio Tonaco
*   Date:   07/16/2018
*
*   You are given a 5x5 array of cells, some of which contain letters and the others are empty. You need to
*   fill the empty squares with letters so that there is a path moving only horizontally or vertically (not diagonally)
*   at each step that visits all cells with consecutive letters. The letters are lower case 'a' through 'y', and 'a'
*   is present in one of the starting cells.
*
*   For the input:          The output should be:
*               zzzzm                               ijklm
*               zzzzz                               hgpon
*               zfzzz                               efqrs
*               zzzaz                               dcbat
*               zzzzu                               yxwvu
*
*   Write a program to solve this problem. The input consists of five lines, each containing a string of five
*   LOWER case letters from 'a' to 'z' with no spaces.
*
*   An 'a' appears exactly once in the five lines. A 'z' indicates an empty cell. The output if five lines,
*   each containing five characters (without spaces), and the five lines together contain each of the letters 'a'
*   through 'y' according to the rules given above.
*
*   Another way to describe the correct output is that if you place the cursor over the ‘a’,
*   then using only the up, down, left, and right arrows you can move the cursor over the letters ‘a’ through ‘y’
*   in order.
*   You may assume that at least one solution exists for any of the test cases.
*   If more than one solution exists, print out just one.
* */


public class problem_set_02 {
    public static void main(String[] args) {
        char[][] table ={
                {'z','z','z','z','a'},
                {'f','z','z','z','z'},
                {'o','z','z','z','z'},
                {'z','z','r','z','z'},
                {'z','z','z','z','u'}
        };

        // Stating position of character a
        int starting_row = 0;
        int starting_col = 4;

        boolean isSolvable = AtoY(table, starting_row, starting_col, 'a');

        if( isSolvable )
            printTable(table);
        else
            System.out.println("Not solvable!");

    }

    private static boolean AtoY(char[][] t, int row, int col, char c) {
        printTable(t);
        System.out.println();
        // STEP 1: Write Base Case(s)
        if( c == 'y' )
            return true;

        // STEP 2: Define directions: UP, DOWN, RIGHT, LEFT
        int[][] directions = {
                {1, 0},
                {0, 1},
                {-1, 0},
                {0, -1}
        };

        // STEP 3: Send 'cursor' on every direction
        for(int[] direction : directions){
            // STEP 4: Make next cursor direction is within range
            if(inRange(direction, row, col)){
                int next_row = row + direction[0];
                int next_col = col + direction[1];
                char desired_char = (char)(c + 1);

                // STEP 5: Look for follow-up character, if its what we are looking for...
                if( t[next_row][next_row] == desired_char ){
                    boolean got_it = AtoY(t, next_row, next_col, desired_char);

                    if( got_it )
                        return true;
                    else
                        AtoY(t, next_row, next_col, desired_char);
                }
                // STEP 6: If it is default character 'z'
                else if( t[next_row][next_row] == 'z' ){
                    // STEP 7: Replace 'z' character for next desired character
                    t[next_row][next_col] = desired_char;

                    boolean got_it = AtoY(t, next_row, next_col, desired_char);

                    if ( got_it )
                        return true;
                    else
                        t[next_row][next_col] = 'z';
                }
            }
        }
        return false;
    }


    private static boolean inRange(int[] d, int row, int col) {
        return ( row + d[0] > -1 && row + d[0] < 5 ) &&
               ( col + d[1] > -1 && col + d[1] < 5 );
    }


    private static void printTable(char[][] t) {
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j)
                System.out.print(t[i][j]);
            System.out.println();
        }
    }
}
