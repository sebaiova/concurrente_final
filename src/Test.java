package src;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Test extends Thread {
    
    private CyclicBarrier barrier;
    private Semaphore sem;
    static private int c = 0;
    private int i;

    public Test(CyclicBarrier barrier, Semaphore sem)
    {
        this.barrier = barrier;
        this.sem = sem;
        i = ++c;
    }

    public void run()
    {

        try {
            sem.acquire();
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
        }
        System.out.printf("Test %d\n", i);
    }

}
