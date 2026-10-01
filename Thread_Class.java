/*
2. Creating Threads using Thread Class
Write a Java program to create three threads by extending the Thread
class. Each thread should perform a different task, such as printing
numbers, displaying characters, and displaying a message. Execute all
three threads concurrently and observe their execution order.
*/

class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Number: " + i);
        }
    }
}

class CharacterThread extends Thread {

    public void run() {
        for (char c = 'A'; c <= 'E'; c++) {
            System.out.println("Character: " + c);
        }
    }
}

class MessageThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Message: Hello");
        }
    }
}
public class Main {
    public static void main(String[] args) {

        NumberThread t1 = new NumberThread();
        CharacterThread t2 = new CharacterThread();
        MessageThread t3 = new MessageThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
