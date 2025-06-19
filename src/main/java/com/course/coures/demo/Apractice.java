package com.course.coures.demo;

import java.util.Arrays;

public class Apractice {

	public static void splitArrayIntoChunk(int arr[], int chunkSize) {
		
		
		if(chunkSize <=0) {
			System.out.println("invalid chunk size must be greater then zero");
			return;
		}
		if(arr.length == 0 || arr == null) {
			
			System.out.println("Arrays is empty");
			return;
		}
	
		
		for( int i=0; i<arr.length; i +=chunkSize) {
			
			int end=Math.min(arr.length, i+chunkSize);
			int chunk []=Arrays.copyOfRange(arr, i, end); // orignal, from , to 
			System.out.println(Arrays.toString(chunk));
		}
		
	}
	public static void main(String[] args) {
		int arr []= {1,2,3,4,5};
		int chunk =2 ;
		
		splitArrayIntoChunk(arr,chunk);
	}
}
