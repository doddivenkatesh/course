package com.course.course.lectures;

import java.util.HashMap;
import java.util.Map;

public class LongestMap {

	    public static void main(String[] args) {
	        String s = "abcabcbb";
	        System.out.println(lengthOfLongestSubstring(s));
	    }

	    public static int lengthOfLongestSubstring(String s) {
	        Map<Character, Integer> map = new HashMap<>();  // ✅ move map outside the loop
	        int start = 0;
	        int maxLength = 0;

	        for (int end = 0; end < s.length(); end++) {
	            char currentChar = s.charAt(end);

	            // if character already exists in map and is within the current window
	            if (map.containsKey(currentChar) ) {
	                start = map.get(currentChar) + 1;
	            }

	            map.put(currentChar, end);  // store/update the latest index of the character
	            maxLength = Math.max(maxLength, end - start + 1);
	        }

	        return maxLength;
	    }
	}
