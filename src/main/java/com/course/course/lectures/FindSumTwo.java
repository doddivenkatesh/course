package com.course.course.lectures;

import java.util.HashMap;
import java.util.Map;

public class FindSumTwo {
	
	    
	    public static int[] findTwoSum(int[] arr, int target) {
	        Map<Integer, Integer> map = new HashMap<>(); // ✅ Move map outside loop

	        for (int i = 0; i < arr.length; i++) {
	            int extra = target - arr[i];
	            if (map.containsKey(extra)) {
	                return new int[] { map.get(extra), i }; // ✅ return indices
	            }
	            map.put(arr[i], i);
	        }
	        return null; // No pair found
	    }
	    
	    public static void main(String[] args) {
	        int[] arr = {2, 3, 5, 3, 4};
	        int target = 9;
	        int[] result = findTwoSum(arr, target);

	        if (result != null) {
	            System.out.println("Indices: " + result[0] + ", " + result[1]);
	            System.out.println("Values: " + arr[result[0]] + ", " + arr[result[1]]);
	        } else {
	            System.out.println("No two numbers sum up to " + target);
	        }
	    }
	

}
