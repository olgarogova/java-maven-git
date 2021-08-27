package multithreading;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Updater extends Thread {

    private final List<String> messageList;

    private static final Logger logger = LoggerFactory.getLogger(Updater.class);

    public Updater(List<String> messageList) {
        this.messageList = messageList;
    }

    @Override
    public void run() {
        int number = 0;

        while(true) {
            if (messageList.size() == 0) {
                logger.info("Nothing update, Message List is empty!");
            } else {
                String message = super.getName() + " updatedMessage " + number++;
                logger.info("Updater updates SMS: " + messageList.get(0) + " -> " + message);
                messageList.set(0, message);
            }

            try {
                Thread.sleep(50000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
