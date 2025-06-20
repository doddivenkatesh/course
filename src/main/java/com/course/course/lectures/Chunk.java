package com.course.course.lectures;
import java.util.*;
public class Chunk {
	    public static List<List<Integer>> chunkArray(int[] array, int chunkSize) {
	        List<List<Integer>> result = new ArrayList<>();
	        if (array == null || chunkSize <= 0) return result;

	        for (int i = 0; i < array.length; i += chunkSize) {
	            int end = Math.min(i + chunkSize, array.length);
	            List<Integer> chunk = new ArrayList<>();
	            for (int j = i; j < end; j++) {
	                chunk.add(array[j]);
	            }
	            result.add(chunk);
	        }
	        return result;
	    }
  
	    public static void main(String[] args) {
	        int[] array = {1, 2, 3, 4, 5};
	        int chunkSize = 2;

	        List<List<Integer>> chunks = chunkArray(array, chunkSize);
	        for (List<Integer> chunk : chunks) {
	            System.out.println(chunk);
	        }
	    }
	
}


//public static List<List<Integer>> chunkArray(int[] array, int chunkSize) {
//    if (array == null || chunkSize <= 0) return Collections.emptyList();
//
//    int length = array.length;
//    return IntStream.range(0, (length + chunkSize - 1) / chunkSize)
//        .mapToObj(i -> {
//            int start = i * chunkSize;
//            int end = Math.min(start + chunkSize, length);
//            return Arrays.stream(array, start, end).boxed().collect(Collectors.toList());
//        })
//        .collect(Collectors.toList());
//}
//
//public static void main(String[] args) {
//    int[] array = {1, 2, 3, 4, 5};
//    int chunkSize = 2;
//
//    List<List<Integer>> chunks = chunkArray(array, chunkSize);
//    chunks.forEach(System.out::println);
//}