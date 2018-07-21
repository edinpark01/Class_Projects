package exercise_06;

import java.util.ArrayList;

public class Pronunciation implements Comparable<Pronunciation> {
	private String word;
	private String phonemes;
	private ArrayList<String> word_list = new ArrayList<String>(); 

	Pronunciation(String p) {
		int i = p.indexOf(' ');
		word = p.substring(0, i);
		phonemes = p.substring(i+2);
	}

	public String getWord() {
		return word;
	}
	
	public void instertWord(String w) {
		word_list.add(w);
	}

	public String getPhonemes() {
		return phonemes;
	}
	
	public ArrayList getWordList() {
		return word_list;
	}

	public String toString() {
		String s = word + ": " + phonemes;
		return s;
	}

	public int compareTo(Pronunciation p) {
		return this.phonemes.compareTo(p.phonemes);
	}
}
