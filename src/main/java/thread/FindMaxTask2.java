package thread;

import java.util.concurrent.Callable;

public class FindMaxTask2 implements Callable<Integer>
{
	private int[] data;
	private int start;
	private int end;

	public FindMaxTask2(int[] data, int start, int end)
	{
		this.data = data;
		this.start = start;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception
	{
		int x = Integer.MIN_VALUE;
		
		long cs1 = System.currentTimeMillis();

		for (int i = start; i < end; i++)
			x = data[i] < x ? x : data[i];

		long cs2 = System.currentTimeMillis();
		
		System.out.println(cs2 - cs1);
		
		return x;
	}

}
