package com.urise.webapp;

public class DeadLock {

    public static void main(String args[]) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        run(lock1, lock2);
        run(lock2, lock1);
    }

    private static void run(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " Waiting " + lock1.toString());
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() + " Holding " + lock1.toString());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Waiting " + lock2.toString());
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() + " Holding " + lock2.toString());
                }
            }
        }).start();
    }
}