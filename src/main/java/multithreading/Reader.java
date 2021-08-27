package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Reader extends Thread {

    private final List<String> messageList;

    private static final Logger logger = LoggerFactory.getLogger(Reader.class);

    public Reader(List<String> messageList) {
        this.messageList = messageList;
    }

    @Override
    public void run() {
        while (true) {
            if (messageList.size() == 0) {
                logger.info("Message List is empty!");
            } else {
                String message = messageList.remove(0);
                logger.info("Reader reads SMS " + message);
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
        int low = 30;
        int high = 50;
        return r.nextInt(high - low) + low;
    }
}
