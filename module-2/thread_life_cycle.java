/*
1. Thread Life Cycle
Write a Java program that demonstrates the different stages of a thread’s
life cycle. Create a thread, start it, make it sleep for a specified
period, and allow it to complete. Explain the transition between the New,
Runnable, Running, Waiting/Timed Waiting, and Terminated states.
*/

class MyThread extends Thread {

    public void run() {
        System.out.println("Thread is running");

        try {
            System.out.println("Thread is going to sleep...");
            Thread.sleep(2000);

            System.out.println("Thread woke up");
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Thread has completed");
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();

        System.out.println("Thread created");
        System.out.println("State: " + t.getState());
        t.start();
        System.out.println("Thread started");
        System.out.println("State: " + t.getState());
        t.join();
        System.out.println("Thread has finished");
        System.out.println("State: " + t.getState());
    }
}
