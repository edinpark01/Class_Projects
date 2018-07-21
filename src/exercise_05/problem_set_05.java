/*  Author:     Braulio Tonaco
 *  Date:       07/19/2018
 *
 *  THE PURPOSE OF THIS PROBLEM IS TO GAIN FAMILIARITY WITH STACK AND QUEUES
 *
 *  You have three jugs that can hold c1, c2, and c3 liters of water, respectively.
 *  Initially:
 *      JUG 1 -> FULL       JUG 2 -> EMPTY
 *                          JUG 3 -> EMPTY
 *  You can repeat the following procedure any number of times:
 *      1.  Choose two of the just
 *      2.  Pour the contents of one into the other
 *      3.  Until either:
 *          a. The first is empty
 *          b.  The second is full
 *
 *  GOAL: End up with exactly d liters in one of the jugs.
 *
 *  EXAMPLE:
 *  Jug (x) |   Capacity    |   Initial Condition
 *  JUG 1   |   20          |   Full
 *  JUG 2   |   5           |   Empty
 *  JUG 3   |   3           |   Empty
 *
 *  GOAL: 4 gallons of water in any one of the jugs
 *
 *  [ 20, 0, 0 ]
 *  [ 15, 5, 0 ]
 *  [ 15, 2, 3 ]
 *  [ 18, 2, 0 ]
 *  [ 18, 0, 2 ]
 *  [ 13, 5, 2 ]
 *  [ 13, 4, 3 ] <-- Reached goal, which is 4 gallons in one of the jugs
 * */

package exercise_05;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class problem_set_05 {

    private static ArrayList<State> visited = new ArrayList<>();   // Keeps track of new jugs movements permutations

    public static void main(String[] args) {
        Queue<State> queue = new LinkedList<>();

        int goal = 4;

        int[] capacities = {20, 5, 3};
        int[] initial_contents = {20, 0, 0};

        State.set_capacities(capacities);               // Sets jugs capacities
        State initial = new State(initial_contents);    // Initializes first jugs

        queue.add(initial);                             // Queue first state

        while( !queue.isEmpty() ){
            State current = queue.poll();               // Dequeue - ( FIFO )

            if( current.reached(goal) ){                // Checks if current state has reached goal
                Stack<State> stack = new Stack<>();

                while ( current.pred != null ) {        // Stacks up to the Singly Linked List head
                    stack.add(current);
                    current = current.pred;
                }
                stack.add(current);                     // Stack up head

                while( !stack.empty() ) {               // Prints out contents of stack from last to first ( LIFO )
                    State s = stack.pop();
                    s.display();
                }
                break;
            }

            for(int i = 0; i < 3; i++){

                for(int j = 0; j < 3; j++){

                    if( j != i ){
                        State next = current.move(i, j);

                        if( !seen(next) ){
                            queue.add(next);            // Queue next State
                            visited.add(next);          // Tag it as visited
                        }
                    }
                }
            }
        }
    }

    /** Checks weather next State node has be visited **/
    private static boolean seen(State n){
        for( State s : visited )
            if( s.contents[0] == n.contents[0] && s.contents[1] == n.contents[1] && s.contents[2] == n.contents[2] )
                return true;
        return false;
    }
}
