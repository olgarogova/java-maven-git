package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

public class DeadlockExample1 implements Runnable {
    private final Lock lock1;
    private final Lock lock2;

    private static final Logger logger = LoggerFactory.getLogger(DeadlockExample1.class);

    public DeadlockExample1(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        String ThreadName = Thread.currentThread().getName();
        logger.info(ThreadName + " Attempting to lock lock1");
        lock1.lock();
        logger.info(ThreadName + "  locked lock1");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info(ThreadName + " Attempting to lock lock2");
        lock2.lock();
        logger.info(ThreadName + "  locked lock2");

        //do the work

        //unlock
        logger.info(ThreadName + "  unlocking lock1");
        lock1.unlock();

        logger.info(ThreadName + "  unlocking lock2");
        lock2.unlock();
    }
}
