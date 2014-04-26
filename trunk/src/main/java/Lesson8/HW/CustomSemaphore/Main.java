package Lesson8.HW.CustomSemaphore;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/21/14
 * Time: 10:16 PM
 */

/**
 * Создать собственный класс Semaphore.
 */

public class Main {
	public static void main(String[] args) {

		Runnable limitedCall = new Runnable() {
			final Random rand = new Random();
			final CustomSemaphore available = new CustomSemaphore(2);
			int count = 0;
			public void run()
			{
				int time = rand.nextInt(2) + 1;
				int num = count++;

				try
				{
					available.acquire(this);

					System.out.println("Executing " +
							"long-running action for " +
							time + " seconds... #" + num);

					Thread.sleep(time * 1000);

					System.out.println("Done with #" +
							num + "!");

					available.release();
				}
				catch (InterruptedException intEx)
				{
					intEx.printStackTrace();
				}
			}
		};

		for (int i=0; i<10; i++)
			new Thread(limitedCall).start();
	}
}

class CustomSemaphore {
	private volatile int permits;
	private volatile Queue<Object> queue = new LinkedList<>();

	CustomSemaphore(int permits) {
		if (permits >= 0) this.permits = permits;
		else throw new IllegalArgumentException();
	}

	public void acquire(Object synchronizer){
		queue.add(synchronizer);
		synchronized (synchronizer) {
			--this.permits;
			if (this.permits < 0){
				try {
					synchronizer.wait();
				} catch (InterruptedException e) { e.printStackTrace(); }
			}
		}

	}

	public void release(){
		Object fromQueue;
		++this.permits;
		if ((fromQueue = queue.poll()) != null){
			synchronized (fromQueue){
				fromQueue.notify();
			}
		}
	}

}

