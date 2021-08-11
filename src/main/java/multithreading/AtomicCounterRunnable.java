package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterRunnable implements Runnable {
    private final AtomicInteger counter = new AtomicInteger(0);
    private static final Logger logger = LoggerFactory.getLogger(CounterRunnable.class);

    public AtomicInteger getCounter() {
        return counter;
    }

    public void atomicIncAndGet(){
        this.counter.compareAndSet(counter.getAndIncrement(), counter.incrementAndGet());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            this.atomicIncAndGet();
        }
        logger.info(Thread.currentThread().getName() + ": " + getCounter());
    }
}
