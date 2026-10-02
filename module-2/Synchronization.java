/*
4. Synchronization
Write a Java program in which multiple threads access and update a shared
bank account balance. Demonstrate the problem that can occur when
multiple threads modify the balance simultaneously. Then use the
synchronized keyword to ensure that the balance is updated correctly.
*/

class BankAccount {
    int balance = 1000;

    public synchronized void withdraw(int amount) {

        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                System.out.println(e);
            }

            balance = balance - amount;

            System.out.println("Remaining balance: " + balance);
        }
        else {
            System.out.println(Thread.currentThread().getName() + " - Insufficient balance");
        }
    }
}
public class Synchronization {
    public static void main(String[] args) throws InterruptedException {

        BankAccount account = new BankAccount();
        Thread t1 = new Thread(() -> account.withdraw(700), "Thread 1");
        Thread t2 = new Thread(() -> account.withdraw(700), "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Final balance: " + account.balance);
    }
}
