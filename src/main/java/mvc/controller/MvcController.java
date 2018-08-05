package mvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import thread.DigitThread;

import static util.DigitUtil.*;

@Controller
public class MvcController
{
	private static boolean finish = false;
	private static int the_max;

	@ResponseBody
	@RequestMapping("/digit")
	public String digit()
	{
		// 生成100万个随机数字
		long c1 = System.currentTimeMillis();
		int[] ref = createDigit(100000000); // 约 20ms 完成 100 万
		long c2 = System.currentTimeMillis();

		// 输出生成所有时间
		System.out.println("create_time = " + (c2 - c1)); // 生成1000万随机数时间

//		单线程方法，约 100ms
//		 DigitThread dt = new DigitThread(ref);
//		 Thread t = new Thread(dt);
//		 t.start();

//		双线程方法，约 50ms
		int[] ref1 = new int[ref.length / 2];
		int[] ref2 = new int[ref.length / 2];

		for (int i = 0; i < ref.length / 2; i++)
			ref1[i] = ref[i];

		int j = 0;
		for (int i = ref.length / 2; i < ref.length; i++) {
			ref2[j] = ref[i];
			j++;
		}

		new Thread(new DigitThread(ref1)).start();
		new Thread(new DigitThread(ref2)).start();

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

		finish = true;
		the_max = x;
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
