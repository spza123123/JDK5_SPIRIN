import java.util.Random;

public class Philosopher extends Thread {
    private volatile Object leftFork;
    private volatile Object rightFork;
    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action)  {
        System.out.println(Thread.currentThread().getName() + " " + action);
        try {
            Thread.sleep(new Random().nextInt(100,1500));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 3; i++) {
                // размышления
                doAction("Thinking");

                synchronized (leftFork) {
                    synchronized (rightFork) {
                        doAction("Eating");
                    }
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}