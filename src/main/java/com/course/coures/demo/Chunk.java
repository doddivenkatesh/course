package com.course.coures.demo;

import java.util.Arrays;

public class Chunk {

	public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};  // You can change this for testing
        int chunkSize = 2;             // Change this to test other cases

        splitArrayIntoChunks(array, chunkSize);
    }

	
	 public static void splitArrayIntoChunks(int[] array, int chunkSize) {
	        if (chunkSize <= 0) {
	            System.out.println("Invalid chunk size. Must be greater than 0.");
	            return;
	        }

	        if (array == null || array.length == 0) {
	            System.out.println("Array is empty.");
	            return;
	        }

	        for (int i = 0; i < array.length; i += chunkSize) {
	            int end = Math.min(array.length, i + chunkSize);
	            int[] chunk = Arrays.copyOfRange(array, i, end); // orignal, form, to
	            System.out.println(Arrays.toString(chunk));
	        }
	    }
}











