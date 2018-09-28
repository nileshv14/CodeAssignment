import java.util.logging.Logger;

class ExecuteTasks implements Runnable {

    Logger log = Logger.getLogger(ExecuteTasks.class.getName());

    String taskNumber;

    ExecuteTasks(String taskNumber) {

        this.taskNumber = taskNumber;
    }

    public void run() {

        log.info("executing task number  " + taskNumber);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("executed tasks  " + taskNumber);
    }
}
