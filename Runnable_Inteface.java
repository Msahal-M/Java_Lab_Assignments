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
            System.out.println("Hello from Runnable");
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
