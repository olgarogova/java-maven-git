package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //performAndFixRaceCondition();
        //performDeadlock();
        //fixDeadlock();
        chatExecute();
    }

    public static void performAndFixRaceCondition() throws InterruptedException {

        CounterRunnable counterRunnable = new CounterRunnable();
        Thread thread1 = new Thread(counterRunnable, "Thread1");
        Thread thread2 = new Thread(counterRunnable, "Thread2");

        logger.info("Race Condition: ");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        AtomicCounterRunnable atomicCounterRunnable = new AtomicCounterRunnable();
        Thread thread3 = new Thread(atomicCounterRunnable, "Thread3");
        Thread thread4 = new Thread(atomicCounterRunnable, "Thread4");

        logger.info("Race Condition fixed, using AtomicInteger counter:");

        thread3.start();
        thread4.start();
    }

    public static void performDeadlock() {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        DeadlockExample1 deadlockExample1 = new DeadlockExample1(lock1, lock2);
        DeadlockExample2 deadlockExample2 = new DeadlockExample2(lock1, lock2);

        Thread thread1 = new Thread(deadlockExample1);
        Thread thread2 = new Thread(deadlockExample2);

        thread1.start();
        thread2.start();

    }

    public static void fixDeadlock() {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        DeadlockFixed1 deadlockFixed1 = new DeadlockFixed1(lock1, lock2);
        DeadlockFixed2 deadlockFixed2 = new DeadlockFixed2(lock1, lock2);

        Thread thread1 = new Thread(deadlockFixed1);
        Thread thread2 = new Thread(deadlockFixed2);

        thread1.start();
        thread2.start();

    }

    public static void chatExecute() {
        int CAPACITY = 25;
        List<String> messageList = Collections.synchronizedList(new ArrayList<>(CAPACITY));

        new Writer(messageList).start();
        new Writer(messageList).start();
        new Writer(messageList).start();
        new Writer(messageList).start();
        new Writer(messageList).start();
        new Reader(messageList).start();
        new Reader(messageList).start();
        new Updater(messageList).start();
    }
}
