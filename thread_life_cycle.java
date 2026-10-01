class ThreadLifeCycle extends Thread {

    public void run() {
        System.out.println("Thread is Running");

        try {
            System.out.println("Thread is going to sleep...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
             System.out.println(e);
        }

        System.out.println("Thread has completed execution");
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadLifeCycle t = new ThreadLifeCycle();

        System.out.println("Thread created - New State");
        System.out.println("Before start(): " + t.getState());
        t.start();

        System.out.println("After start(): " + t.getState());
        t.join();
        System.out.println("After completion: " + t.getState());
    }
}
