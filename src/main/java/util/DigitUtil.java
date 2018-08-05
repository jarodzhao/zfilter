package util;

import java.util.Random;

import thread.DigitThread;

public class DigitUtil
{
	/**
	 * 生成随机数
	 * 
	 * @param len
	 * @return
	 */
	public static int[] createDigit(int len)
	{
		int[] ref = new int[len];
		Random random = new Random();

		for (int i = 0; i < len; i++)
			ref[i] = random.nextInt();

		return ref;
	}
	
	/**
	 * 单线程方法，约 100ms 1000 万数字
	 * @param ref 数组
	 */
	public static void SingleThread(int[] ref)
	{
		 DigitThread dt = new DigitThread(ref);
		 Thread t = new Thread(dt);
		 t.start();
	}

	/**
	 * 双线程寻找最大数方法，约 50ms 1000 万数字
	 * @param ref 数组
	 */
	public static void MultipleThread(int[] ref)
	{
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
	}
}
