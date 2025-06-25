package com.course.course.lectures;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
 public static void main(String[] args) {

	         String s = "abcabcbb";
	         System.out.println(lengthOfLongestSubstring(s));  // Output: 3
	     }

	     public static int lengthOfLongestSubstring(String s) {
	         Set<Character> set = new HashSet<>();
	         int maxLength = 0;
	         int left = 0;

	         for (int right = 0; right < s.length(); right++) {
	             while (set.contains(s.charAt(right))) {
	                 set.remove(s.charAt(left));
	                 left++;
	             }
	             set.add(s.charAt(right));
	             maxLength = Math.max(maxLength, right - left + 1);
	         }

	         return maxLength;
	     }

	     public class Main {
	         public static void main(String[] args) {
	             String s = "pwwkewxpw";
	             System.out.println("Longest substring length: " + lengthOfLongestSubstring(s));
	         }

	         public static int lengthOfLongestSubstring(String s) {
	             Set<Character> seen = new HashSet<>();
	             int maxLength = 0;
	             int left = 0;

	             for (int right = 0; right < s.length(); right++) {
	                 char currentChar = s.charAt(right);

	                 while (seen.contains(currentChar)) {
	                     seen.remove(s.charAt(left));
	                     left++;
	                 }

	                 seen.add(currentChar);
	                 maxLength = Math.max(maxLength, right - left + 1);
	             }

	             return maxLength;
	         }
	     }


}

