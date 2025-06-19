package com.course.coures.demo;

public class SyncTest {
	
	
 public synchronized void m1() throws InterruptedException {
	 
	 System.out.println(Thread.currentThread().getName() + "entered m1()");
	 Thread.sleep(5000);
	 System.out.println(Thread.currentThread().getName() + "exiting m1()");
 }
 
 
 public static void main(String[] args) {
	 SyncTest o1 = new SyncTest();
	 SyncTest o2 = new SyncTest();
	 
	 Thread t1 = new Thread(() -> {
         try {
             o1.m1();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }, "Thread-1");
	 
	 
	 Thread t2 = new Thread( ()->{
		 
		 try {
			 o2.m1();
		 }catch(InterruptedException e) {
			 e.printStackTrace();
		 }
	 }, "Thread-2");
	 t1.start();
	 t2.start();
}
}
