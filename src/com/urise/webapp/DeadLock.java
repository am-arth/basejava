package com.urise.webapp;

public class DeadLock {
   public static Object Lock1 = new Object();
   public static Object Lock2 = new Object();

   public static void main(String args[]) {
      Thread1 t1 = new Thread1();
      Thread2 t2 = new Thread2();
      t1.start();
      t2.start();
   }

   private static class Thread1 extends Thread {
      public void run() {
         synchronized (Lock1) {
            System.out.println("Holding lock 1");
            try {
               sleep(500);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("Waiting lock 2");
            synchronized (Lock2) {
               System.out.println("Holding lock 2");
            }
         }
      }
   }
   private static class Thread2 extends Thread {
      public void run() {
         synchronized (Lock2) {
            System.out.println("Holding lock 2");
            try {
               sleep(500);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("Waiting lock 1");
            synchronized (Lock1) {
               System.out.println("Holding lock 1");
            }
         }
      }
   } 
}