package mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import thread.FindMaxTask;

import static util.DigitUtil.*;

@Controller
public class MvcController
{
	/**
	 * callable 方式线程的调用
	 * 
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@ResponseBody
	@RequestMapping("/callable")
	public String str() throws InterruptedException, ExecutionException
	{
		int[] ref = createDigit(100000000);

		int[] ref1 = new int[ref.length / 2];
		int[] ref2 = new int[ref.length / 2];

		for (int i = 0; i < ref.length / 2; i++)
			ref1[i] = ref[i];

		int j = 0;
		for (int i = ref.length / 2; i < ref.length; i++) {
			ref2[j] = ref[i];
			j++;
		}

		FindMaxTask task1 = new FindMaxTask(ref1);
		FindMaxTask task2 = new FindMaxTask(ref2);
		
		// 线程管理器，创建两个线程（自动复用线程）
		ExecutorService es = Executors.newFixedThreadPool(2);
		
		/**
		 * FixedThreadPool: 预分配线程池
		 * CachedThreadPool: 缓存线程池（优选）
		 * SingleThreadPool: 单任务（队列）线程
		 */
		
		
		// callable 线程任务会阻塞， runnable 不会
		Future<Integer> future1 = es.submit(task1);		//get() 方法阻塞，直到出现结果, 50ms 约 1 亿个数字
		Future<Integer> future2 = es.submit(task2);		//get() 方法阻塞，直到出现结果, 50ms 约 1 亿个数字
		
		// 检查线程是否完成
		future1.isDone();
		
		return future1.get() + " / " + future2.get();
	}

	/**
	 * thread 方式线程的调用
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/thread")
	public String digit()
	{
		// 生成100万个随机数字
		long c1 = System.currentTimeMillis();
		int[] ref = createDigit(100000000); // 约 20ms 完成 100 万
		long c2 = System.currentTimeMillis();

		// 输出生成所有时间
		System.out.println("create_time = " + (c2 - c1)); // 生成1000万随机数时间

		// 单线程方法
		SingleThread(ref);

		// 多（双）线程方法
		MultipleThread(ref);

		return "0";
	}

	/**
	 * 线程回调的静态方法
	 * 
	 * @param x
	 *            找到的最大数
	 */
	public static void callbackReceive(int x)
	{
		System.out.println(" * " + x);
	}

	/**
	 * 测试控制器
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/hi")
	public String hi()
	{
		Random random = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ref = "hi, " + random.nextFloat() + " at " + sdf.format(new Date());
		System.out.println(ref);
		return ref;
	}
}
