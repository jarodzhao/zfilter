package thread;

import java.util.concurrent.Callable;

/**
 * 实现 callable 线程类
 * @author ht_z
 * 返回值为 <Integer>
 */
public class FindMaxTask implements Callable<Integer>
{
/**
 * Callable 可以有返回值，Runnable 不能有返回值
 */
	private int[] data;

	public FindMaxTask(int[] data)
	{
		this.data = data;
	}

	@Override
	public Integer call()
	{
		int max = Integer.MIN_VALUE;
		
		long cs1 = System.currentTimeMillis();
		
		for(int d : data)
			max = Math.max(d, max);

		long cs2 = System.currentTimeMillis();
		
		System.out.println(cs2 - cs1);

		return max;
	}

}
