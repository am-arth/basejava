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
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " Waiting " + lock1);
            synchronized (lock1) {
                System.out.println(threadName + " Holding "+ lock1);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println(threadName + " Holding " + lock2);
                }
            }
        }).start();
    }
}