package com.course.course.lectures;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class Chunk {
	    public static List<List<Integer>> chunkArray(int[] array, int chunkSize) {
	        List<List<Integer>> result = new ArrayList<>();
	        if (array == null || chunkSize <= 0) return result;

	        for (int i = 0; i < array.length; i += chunkSize) {
	            int end = Math.min(i + chunkSize, array.length); //0 ,5 =,
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
	


 public class ChunkArray{
public static List<List<Integer>> chunkArray(int[] array, int chunkSize) {
    if (array == null || chunkSize <= 0) return Collections.emptyList();

    int length = array.length;
    return IntStream.range(0, (length + chunkSize - 1)/ chunkSize) // 5+ 2-1 =6/2=6
        .mapToObj(i -> {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, length);
            return Arrays.stream(array, start, end).boxed().collect(Collectors.toList());
        })
        .collect(Collectors.toList());
}

 public static void main(String[] args) {
    int[] array = {1, 2, 3, 4, 5};
    int chunkSize = 2;

    List<List<Integer>> chunks = chunkArray(array, chunkSize);
    chunks.forEach(System.out::println);
}}}

/*


| Category       | Purpose                                          |
| -------------- | ------------------------------------------------ |
| **Creational** | Deal with **object creation**                    |
| **Structural** | Deal with **object composition**                 |
| **Behavioral** | Deal with **object interaction & communication** |

1. Creational Patterns
| Pattern              | Description                                               |
| -------------------- | --------------------------------------------------------- |
| **Singleton**        | Ensures only one instance of a class exists.              |
| **Factory Method**   | Delegates instantiation to subclasses.                    |
| **Abstract Factory** | Creates related objects without specifying their classes. |
| **Builder**          | Constructs complex objects step-by-step.                  |
| **Prototype**        | Clones existing objects instead of creating new ones.     |

Example Singleton Pattern
public class Singleton {
private static Singleton instance;

private Singleton() {} // private constructor

public static Singleton getInstance() {
    if (instance == null) {
        instance = new Singleton();
    }
    return instance;
}
}
2. Structural Patterns

| Pattern       | Description                                             |
| ------------- | ------------------------------------------------------- |
| **Adapter**   | Converts one interface to another.                      |
| **Bridge**    | Separates abstraction from implementation.              |
| **Composite** | Treats individual objects and compositions uniformly.   |
| **Decorator** | Adds responsibilities dynamically.                      |
| **Facade**    | Provides a simplified interface to a complex subsystem. |
| **Proxy**     | Represents another object.                              |
🔧 Example: Adapter Pattern
interface MediaPlayer {
void play(String fileName);
}

class AudioPlayer implements MediaPlayer {
public void play(String fileName) {
    System.out.println("Playing audio: " + fileName);
}
}

class MediaAdapter implements MediaPlayer {
private AudioPlayer player = new AudioPlayer();
public void play(String fileName) {
    player.play(fileName);
}
}
3. Behavioral Patterns
| Pattern                     | Description                                                    |
| --------------------------- | -------------------------------------------------------------- |
| **Observer**                | Notifies all dependents of state changes.                      |
| **Strategy**                | Allows selecting algorithm at runtime.                         |
| **Command**                 | Encapsulates a request as an object.                           |
| **Iterator**                | Provides a way to access elements sequentially.                |
| **Template Method**         | Defines the skeleton of an algorithm.                          |
| **State**                   | Allows an object to alter its behavior when its state changes. |
| **Chain of Responsibility** | Passes request along a chain of handlers.                      |
 Example: Strategy Pattern

interface PaymentStrategy {
void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
public void pay(int amount) {
    System.out.println("Paid with credit card: " + amount);
}
}

class PaypalPayment implements PaymentStrategy {
public void pay(int amount) {
    System.out.println("Paid with PayPal: " + amount);
}
}

class ShoppingCart {
public void checkout(PaymentStrategy strategy, int amount) {
    strategy.pay(amount);
}
}
*/
