/*
3. Creating Threads using Runnable Interface
Write a Java program to create two or more threads by implementing the
Runnable interface. Each thread should perform a separate task.
Explain why implementing Runnable can be preferable to extending the
Thread class in certain situations.
*/

class NumberTask implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Number: " + i);
        }
    }
}
class MessageTask implements Runnable {

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Message: Hello");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NumberTask task1 = new NumberTask();
        MessageTask task2 = new MessageTask();
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        t2.start();
    }
}
