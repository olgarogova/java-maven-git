package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CounterRunnable implements Runnable {

    private int counter = 0;
    private static final Logger logger = LoggerFactory.getLogger(CounterRunnable.class);

    public long getCounter() {
        return counter;
    }

    public void setCounter(int counter){
        this.counter = counter;
    }

    public void incAndGet() {
            this.counter++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            this.incAndGet();
        }
        logger.info(Thread.currentThread().getName() + ": " + getCounter());
    }
}
