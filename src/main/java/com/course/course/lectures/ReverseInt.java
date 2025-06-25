package com.course.course.lectures;

import java.util.stream.IntStream;

public class ReverseInt {

	    public static void main(String[] args) {
	        int n = 12345;	       
	        String str = String.valueOf(n);
	        String reversed = IntStream.rangeClosed(1, str.length())
	                                   .mapToObj(i -> str.charAt(str.length() - i))
	                                   .map(String::valueOf)
	                                   .reduce("", String::concat);
	        int reversedInt = Integer.parseInt(reversed);
	        System.out.println("Reversed number: " + reversedInt);
	        String str1 = "ROTATOR";
	        System.out.println(str1.length());
	    }
	}

//| ---------------------------------------- | --------------------------------- |
//| `String.valueOf(n)`                      | Converts the number to string     |
//| `IntStream.rangeClosed(1, str.length())` | Iterates in reverse using math    |
//| `.mapToObj(...)`                         | Gets characters from end to start |
//| `.map(String::valueOf)`                  | Converts `char` to `String`       |
//| `.reduce("", String::concat)`            | Joins into reversed string        |
//| `Integer.parseInt(...)`                  | Converts string to int again      |
