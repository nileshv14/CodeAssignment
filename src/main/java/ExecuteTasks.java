import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

public class ExecuteTasks implements Runnable {

    Logger log = Logger.getLogger(ExecuteTasks.class.getName());

    private String taskNumber;

    private CountDownLatch countDownLatch;

    ExecuteTasks(String taskNumber, CountDownLatch countDownLatch) {

        this.taskNumber = taskNumber;

        this.countDownLatch = countDownLatch;
    }

    public void run() {

        log.info("executing task number  " + taskNumber);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        countDownLatch.countDown();
        log.info("executed tasks  " + taskNumber);
    }
}
