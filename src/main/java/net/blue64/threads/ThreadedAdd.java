package net.blue64.threads;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * Created by IntelliJ IDEA.
 * User: selevine
 * Date: Jun 21, 2010
 * Time: 8:31:45 PM
 */

public class ThreadedAdd {

    public static final int NUM_THREADS = 10;

    private static final Executor exe = Executors.newFixedThreadPool(NUM_THREADS);

    private static final AtomicInteger total = new AtomicInteger(0);

    private CountDownLatch countDown;

    public int add(List<Integer> intList) {
        if (intList == null)
            return 0;

        //TODO: Remove below here before giving to candidate
        countDown = new CountDownLatch(intList.size());

        for (final int i : intList) {
            Runnable task = new Runnable() {
                public void run() {
                    total.addAndGet(i);
                    countDown.countDown();
                }
            };
            exe.execute(task);
        }
        try {
			countDown.await();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
        return total.get();
    }
}
