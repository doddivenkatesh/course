package com.course.course.lectures;

public class Basics {
	public static void main(String[] args) {
		int n = 323 ;
		 String s=String.valueOf(n);
		 System.out.println(s);
		 char arr []=s.toCharArray();
		  String rev = "";
		 for(int j=arr.length-1; j>=0; j--) {
			rev = rev + arr[j];
		 }
		 if( rev.equals(s)) {  //compare string values we need to use .equals()
			 System.out.println("palindrome");
		 }else {
			 System.out.println("not palindrome");
		 }
		 System.out.println( "string rev value " + rev);
//		int original =n;
//		StringBuffer sb = new StringBuffer();
//		sb.append(n);
//		StringBuffer r=sb.reverse();
//		int i =Integer.parseInt(r.toString());
//		System.out.println(r);
//		if(original == i) {
//			System.out.println("palindrome");
//		}else {
//			System.out.println("not palindrome");
//		}
	}
}


