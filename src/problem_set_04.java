/*  Author: Braulio Tonaco
*   Date:   07/18/2018
*
*   PROBLEM SET 04
*
*   An anagram is a word obtained by reordering the letters of another word.
*
*   For example, "once" and "cone" are anagrams.
*
*   NOTE: Each letter must be reused exactly once, so anagrams must have the same numbers of letters.
*
*   Which word in the English language has the most anagrams?
*   Let us take "words in the English language" to mean words in the file "problem_set_04_data.txt".
*
*   Write an algorithm to find the word with the most anagrams. Your program should take no input
*   and produce a single integer, the maximum numbers of anagrams as output.
*
*   CORRECT OUTPUT: 11
* */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class problem_set_04 {
    public static void main(String[] args) {


        array_approach();

        System.out.println();

        hash_map_approach();

    }

    private static void hash_map_approach() {
        HashMap<String, Integer> tracker = new HashMap<String, Integer>();

        // STEP 2: The name of the file to open
        String file_name = "problem_set_04_data.txt";

        // STEP 3: Initialize line variable and set it to null
        String unsorted = null;

        long start = System.currentTimeMillis();

        // STEP 4: Try to open txt file
        try {
            // STEP 4.1: FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file_name);

            // STEP 4.2: Wraps FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // STEP 4.3: Read all words in txt file
            int max = 0;
            while( (unsorted = bufferedReader.readLine()) != null ) {

                // STEP 4.3.1: Sort each word
                char[] c = unsorted.toCharArray();
                Arrays.sort(c);
                String sorted = new String(c);

                // STEP 4.3.2: Map its sorted version count
                if( tracker.containsKey(sorted) )
                    tracker.put(sorted, tracker.get(sorted) + 1);

                else
                    tracker.put(sorted, 1);
            }

            // STEP 4.4: Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file_name + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + file_name + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        long read_file = System.currentTimeMillis();

        // STEP 5: Traverse hashmap and check for max
        int max_anagram = 0;
        for( int value : tracker.values() ){

            if( value > max_anagram )
                max_anagram = value;
        }

        long found_max = System.currentTimeMillis();

        System.out.println("Max number of anagrams: " + max_anagram);
        System.out.println("Time to read file: " + ((read_file - start) / 1000.0) + " seconds");
        System.out.println("Time to calculate max: " + ((found_max - read_file) / 1000.0) + " seconds");

    }


    public static void array_approach(){
        // STEP 1: Initialize array of arrays
        ArrayList< ArrayList<String> > lists = new ArrayList();

        // NOTE: For each main_array index [ i ], there will be an array list containing words of length [ i ]
        //       Example: main_array.get(5) will return an ArrayList containing words of length 5
        lists.add( new ArrayList<>() );

        // STEP 2: The name of the file to open
        String file_name = "problem_set_04_data.txt";

        // STEP 3: Initialize line variable and set it to null
        String word = null;

        long start = System.currentTimeMillis();

        // STEP 4: Try to open txt file
        try {
            // STEP 4.1: FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(file_name);

            // STEP 4.2: Wraps FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // STEP 4.3: Read all words in txt file
            int max = 0;
            while( (word = bufferedReader.readLine()) != null ) {
                int len = word.length();

                // STEP 4.3.1: Adjust/expand main_array to be able to contain words of greater length
                if( len > max ){
                    max = len;
                    for (int i = lists.size(); i <= max; i++ )
                        lists.add(new ArrayList<String>());
                }

                // STEP 4.3.2: Convert word (String) to a Array of characters
                char[] chr_array = word.toCharArray();

                // STEP 4.3.3: Sort characters array using Arrays.sort()
                Arrays.sort(chr_array);

                // STEP 4.3.4: Convert characters array back to a sorted string
                String sorted_word = new String(chr_array);

                // STEP 4.3.4: Add SORTED character array to main_array at index equal to char array size
                lists.get( len ).add( sorted_word );
            }

            // STEP 4.4: Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file_name + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + file_name + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }

        long read_file = System.currentTimeMillis();

        // STEP 5: Find max number of anagrams
        int max_anagram = 0;
        for( ArrayList<String> list : lists ){

            for( int i = 0; i < list.size() && !list.get(i).equals(""); i++ ){
                String w1 = list.get(i);
                int temp_counter = 0;

                for( int j = 0; j < list.size() && !list.get(j).equals("") && i != j; j++ ){
                    String w2 = list.get(j);

                    if( w1.equals(w2) ){  // Found an anagram
                        temp_counter++;
                    }
                }

                if( temp_counter > max_anagram ) {
                    max_anagram = temp_counter + 1;
                }
            }
        }

        long found_max = System.currentTimeMillis();

        System.out.println("Max number of anagrams: " + max_anagram);
        System.out.println("Time to read file: " + ((read_file - start) / 1000.0) + " seconds");
        System.out.println("Time to calculate max: " + ((found_max - read_file) / 1000.0) + " seconds");
    }
}

