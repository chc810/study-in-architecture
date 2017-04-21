package threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EventHandler {

	final static Logger logger = LoggerFactory.getLogger(EventHandler.class);

	private static int CORE_THREADS = 30;//核心执行线程数
	private static int MAX_THREADS = 50;//最大执行线程数
	private static int MAX_BOLOCK_QUEUE = 1000;//最大等待队列
	public static ExecutorService executorService = new ThreadPoolExecutor(CORE_THREADS,MAX_THREADS, 1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(MAX_BOLOCK_QUEUE, true));
	//public static ExecutorService executorService = Executors.newCachedThreadPool();
	static {
		((ThreadPoolExecutor) executorService).allowCoreThreadTimeOut(false);
	}

	public static void main(String[] args) throws InterruptedException {
		for (int i=0; i<10000;i++) {
			executorService.execute(new TaskHanlder());
			Thread.sleep(400);
		}
	}

}

class TaskHanlder implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(TaskHanlder.class);


	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " 进入TaskHanlder.............");
	}
}
