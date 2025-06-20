package com.course.course.lectures;



public class ReverseAnInteger {
	public static void main(String[] args) {
		int n = 323;
		String s=String.valueOf(n);
	char [] arr=s.toCharArray();
	 String rev = "";
	for(int i=arr.length-1; i>=0; i--) {
		rev= rev + arr[i];
	}
	System.out.println(rev);
	
	int v=Integer.parseInt(rev);
//	if(rev.equals(s)) {
//		System.out.println("its palind");
//	}else {
//		System.out.println("not palindrome");
//	}
	
	if(v == n) {
		System.out.println("its palindorme");
	}else {
		System.out.println("not palindrome");
	}
	
	}
	
	
}
