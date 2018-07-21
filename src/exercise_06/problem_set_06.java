/*  Author:     Braulio Tonaco
 *  Date:       07/21/2018
 *
 *  THE PURPOSE OF THIS PROBLEM IS TO GAIN FAMILIARITY WITH THE DICTIONARY ABSTRACT DATA TYPE (ADT)
 *
 *  A homophone is one of two or more words what are pronounced alike but are different in meaning or spelling.
 *
 *  For example the following words:
 *      - two
 *      - too
 *      - to
 *
 *  The file 'cmudict.07.7a.txt' contains a pronunciation dictionary downloaded from: http://speech.cs.cmu.edu
 *      - It consists of lines in the form: ABUNDANT    AHO B AH1 N D AHO N T
 *      - The first string is the word, which is followed by one or more phonemes (or phones) that describe the
 *        word's pronunciation
 *
 *  Your goal is yo traverse the cmudict.07.07a.txt file and find the n largest number of homophones.
 *  Then, n homophones follow on the next n lines, one word per line.
 * */

package exercise_06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class problem_set_06 {

    public static void main(String[] args) {
        HashMap<String, ArrayList<String>> hash = new HashMap<>();
        ArrayList<String> max_list = new ArrayList<>();
        int max = 0;

        File file = new File("cmudict.0.7a.txt");

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.substring(0, 3).equals(";;;"))
                    continue; // skip comment lines

                Pronunciation p = new Pronunciation(line);
                String key = p.getPhonemes();
                String val = p.getWord();

                if ( hash.containsKey(key) ){
                    ArrayList<String> values = hash.get(key);
                    values.add(val);

                    if ( values.size() > max ){                     // Checks for max number of homophones
                        max = values.size();
                        max_list = values;
                    }

                    hash.put(key, values);
                }
                else{
                    ArrayList<String> values = new ArrayList<>();
                    values.add(val);
                    hash.put(key, values);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Max number of homophones: " + max + "\n");

        for(String word : max_list){
            System.out.println(word);
        }
    }
}
