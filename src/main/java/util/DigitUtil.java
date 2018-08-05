package util;

import java.util.Random;

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
}
