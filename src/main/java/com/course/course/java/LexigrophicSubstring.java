package com.course.course.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LexigrophicSubstring {

	
	    public static void main(String[] args) {
	        String s = "banana";
	        int k = 3;

	        List<String> substrings = new ArrayList<>();

	        // Extract all substrings of length k
	        for (int i = 0; i <= s.length() - k; i++) {
	            substrings.add(s.substring(i, i + k));
	        }

	        // Sort them lexicographically
	        Collections.sort(substrings);

	        // Print them
	        System.out.println("Lexicographically ordered substrings of length " + k + ":");
	        for (String sub : substrings) {
	            System.out.println(sub);
	        }
	    }
	

}
