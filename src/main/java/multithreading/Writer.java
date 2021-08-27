package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Writer extends Thread {

    private static final int CAPACITY = 25;
    private final List<String> messageList;

    private static final Logger logger = LoggerFactory.getLogger(Writer.class);

    public Writer(List<String> messageList) {
        this.messageList = messageList;
    }

    @Override
    public void run() {
        String message;
        int number = 0;

        while (true) {
            if (messageList.size() == CAPACITY) {
                logger.info("Message List is full!");
            } else {
                message = super.getName() + " message " + number++;
                messageList.add(message);
                logger.info("Writer sends SMS " + message);
            }

            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(this.getRandomSleepDuration()));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private long getRandomSleepDuration(){
        Random r = new Random();
        int low = 20;
        int high = 60;
        return r.nextInt(high - low) + low;
    }
}
