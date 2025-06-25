package com.course.course.lectures;

import java.util.HashMap;
import java.util.Map;

public class RearrangingPalindrome {


	    public static void main(String[] args) {
	        String str = "abdybayd";
	        boolean isPalindromePossible = checkPalindromePossibility(str);
	        System.out.println("Is palindrome possible ? : " + isPalindromePossible);
	    }

	    static boolean checkPalindromePossibility(String str) {
	        Map<Character, Integer> freqMap = new HashMap<>();
	        
	        // Count frequency of each character
	        for (char c : str.toCharArray()) {
	            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
	        }

	        int oddCount = 0;

	        for (int freq : freqMap.values()) {
	            if (freq % 2 != 0) {
	                oddCount++;
	                if (oddCount > 1) return false;
	            }
	        }

	        return true;
	    }

}


/*

| Use This On  | Correct Method | What It Does                   |
| ------------ | -------------- | ------------------------------ |
| `Map` object | `get(key)`     | Get value by key               |
| `Map` object | `values()`     | Get all values                 |
| `Map` object | `keySet()`     | Get all keys                   |
| `Map` object | `entrySet()`   | Get all key-value pairs        |
| `Map.Entry`  | `getKey()`     | Get key of the current entry   |
| `Map.Entry`  | `getValue()`   | Get value of the current entry |


| Operation                     | `ArrayList`             | `LinkedList`               |
| ----------------------------- | ----------------------- | -------------------------- |
| **Access (get/set)**          | O(1) ✅                  | O(n) ❌ (sequential search) |
| **Insert at end**             | O(1) (amortized) ✅      | O(1) ✅                     |
| **Insert in middle/start**    | O(n) ❌ (shift elements) | O(1) ✅ (if pointer known)  |
| **Remove at end**             | O(1) ✅                  | O(1) ✅                     |
| **Remove in middle/start**    | O(n) ❌                  | O(1) ✅ (if pointer known)  |
| **Search (contains/indexOf)** | O(n) ❌                  | O(n) ❌                     |
| **Iteration**                 | O(n) ✅                  | O(n) ✅                     |
📘 Why the differences?
1. ArrayList
Internally uses a resizable array.

Elements are stored contiguously in memory.

Pros:

Fast random access via index.

Cons:

Insert/remove in the middle → all following elements must shift.

Can waste memory due to capacity resizing.

2. LinkedList
Implemented as a doubly linked list.

Each node contains a value + pointers to next and previous.

Pros:

Fast insert/remove at start or middle (if pointer/reference known).

Cons:

Slow access by index (must traverse from head/tail).

Higher memory overhead (each node stores 2 pointers).

✅ When to Use What?
Scenario	Best Choice	Why?
Frequent random access	ArrayList ✅	Constant time get(i)
Frequent insert/remove in middle	LinkedList ✅	No shifting required
Iterate through all elements	Both are fine	O(n) for both
Memory-sensitive, large lists	ArrayList ✅	Lower overhead

🔍 Example:
java
Copy
Edit
List<Integer> list = new ArrayList<>();
list.get(1000); // O(1)

List<Integer> list2 = new LinkedList<>();
list2.get(1000); // O(n) traversal from head
Let me know if you'd like a benchmark demo, or how both perform in multithreaded or concurrent
*/