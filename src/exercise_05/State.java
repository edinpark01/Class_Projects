package exercise_05;

public class State {
    static int[] capacities;
    int[] contents;
    State pred; // Singly Linked list where each node knows its predecessor

    /** Sets global jugs capacities **/
    static void set_capacities(int[] c){
        capacities = new int[c.length];
        System.arraycopy(c, 0, capacities, 0, c.length);
    }

    /** Sets up contents for first instance of State (Singly Linked List HEAD) **/
    State(int[] c){
        contents = new int[c.length];
        System.arraycopy(c, 0, contents, 0, c.length);

        // Sets current state predecessor to null, since its the first instance of State
        pred = null;
    }

    /** Sets successor jugs capacities **/
    State(State s){
        contents = new int[s.contents.length];
        System.arraycopy(s.contents, 0, contents, 0, s.contents.length);
    }

    /** Move contents from source jug to destination jug **/
    State move(int src, int dest){
        State newState = new State(this);

        int srcAmount = newState.contents[src];  // Source jug current instance contents (how much water in it)
        int desAmountToFillUp = capacities[dest] - newState.contents[dest]; // Destination Jug Capacity minus its contents

        int transferAmount = Math.min(srcAmount, desAmountToFillUp);

        newState.contents[src] -= transferAmount;
        newState.contents[dest] += transferAmount;

        newState.pred = this;  // Sets newState's predecessor (next State Node) equals to current state instance (this)

        return newState;
    }

    /** Checks if current state has reached its goal, which is d **/
    boolean reached(int goal){
        return contents[0] == goal || contents[1] == goal || contents[2] == goal;
    }

    /** Displays jugs contents of current state **/
    void display(){
        System.out.println("[ " + contents[0] + ", " + contents[1] + ", " + contents[2] + " ]");
    }

}
