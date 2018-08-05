package thread;

import mvc.controller.MvcController;

/**
 * 找出最大数
 * 
 * @author ht_z
 *
 */
public class DigitThread implements Runnable
{
	private int[] ds;
	private int max;

	public DigitThread(int[] ds)
	{
		this.ds = ds;
	}

	@Override
	public void run()
	{
		long d1 = System.currentTimeMillis();
		
		for (int d : ds)
			max = Math.max(d, max);

		long d2 = System.currentTimeMillis();

		System.out.println("find_time = " + (d2 - d1));
		
		MvcController.callbackReceive(max);
	}

}
