import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TestTasks {

    private static final Logger logger = Logger.getLogger(TestTasks.class.getName());

    public static void RunTasks(int numberOfThreads, int numberOfTasks) {

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        final CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks - 2);

        for (int i = 0; i < numberOfTasks - 1; i++) {

            if (i == 0) {

                logger.info("Executing first task " + (i + 1));

                logger.info("execution of task 1 finished ");
            } else {

                Runnable runTask = new ExecuteTasks(String.valueOf(i + 1), countDownLatch);

                executorService.execute(runTask);
            }
        }

        try {
            countDownLatch.await();
            TimeUnit.MILLISECONDS.sleep(1000);
            Runnable runTask = new ExecuteTasks(String.valueOf(numberOfTasks), countDownLatch);
            executorService.execute(runTask);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        logger.info("Please enter number of tasks to be executed");

        int numberOfTasks = scanner.nextInt();

        logger.info("Please enter number of threads");

        int numberOfThreads = scanner.nextInt();

        RunTasks(numberOfThreads, numberOfTasks);
    }
}


 /*ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        final CountDownLatch countDownLatch = new CountDownLatch(numberOfTasks - 2);

        for (int i = 0; i < numberOfTasks - 1; i++) {

            if (i == 0) {

                logger.info("Executing first task " + (i + 1));

                logger.info("execution of task 1 finished ");
            } else {

                Runnable runTask = new ExecuteTasks(String.valueOf(i + 1), countDownLatch);

                executorService.execute(runTask);
            }
        }

        try {
            countDownLatch.await();
            TimeUnit.MILLISECONDS.sleep(1000);
            Runnable runTask = new ExecuteTasks(String.valueOf(numberOfTasks), countDownLatch);
            executorService.execute(runTask);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }*/