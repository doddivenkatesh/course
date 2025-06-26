package com.course.course.lectures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

	         public class HLengthOfLongestSubString{
	         public static void main(String[] args) {
	             String s = "abcabcbb";
	             System.out.println(lengthOfLongestSubstring1(s));
	         }

	         public static int lengthOfLongestSubstring1(String s) {
	             Map<Character, Integer> map = new HashMap<>();  // ✅ move map outside the loop
	             int start = 0;
	             int maxLength = 0;

	             for (int end = 0; end < s.length(); end++) {
	                 char currentChar = s.charAt(end);

	                 // if character already exists in map and is within the current window
	                 if (map.containsKey(currentChar) && map.get(currentChar) >= start) {
	                     start = map.get(currentChar) + 1;
	                 }

	                 map.put(currentChar, end);  // store/update the latest index of the character
	                 maxLength = Math.max(maxLength, end - start + 1);
	             }

	             return maxLength;
	         }
	     }
	     }

}

