import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestTasks {

    private static final int THREADS_COUNT = 10;

    private static final int TASK_COUNT = 1000;

    private static final Logger logger = Logger.getLogger(TestTasks.class.getName());

    public static void main(String args[]) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

        int numberOfTasks = TASK_COUNT;

        final CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks - 2);

        for (int i = 0; i < numberOfTasks-1; i++) {

            if (i == 0) {

                logger.info("Executing first task " + (i + 1));

                logger.info("execution of task 1 finished ");
            } else if (i == numberOfTasks - 1) {

                logger.info("executing last task " + (i + 1));

                TimeUnit.MILLISECONDS.sleep(1000);

                logger.info("executed last task " + (i + 1));
            } else {

                Runnable runTask = new ExecuteTasks(String.valueOf(i + 1),countDownLatch);

                executorService.execute(runTask);


            }
        }

        try{
            countDownLatch.await();
            TimeUnit.MILLISECONDS.sleep(1000);
            Runnable runTask = new ExecuteTasks(String.valueOf(numberOfTasks),countDownLatch);
            executorService.execute(runTask);
        }catch (InterruptedException e){

            e.printStackTrace();
        }
    }
}
