package Lesson7.HW.Violins;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/13/14
 * Time: 6:42 PM
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Есть 2 скрипача, скрипока и смычок. Смоделировать Deadlock и разрешить его
 * стандартными средствами Java 1.4
 */

public class Main {
	private static volatile Queue<Violin> violins = new LinkedList<>();
	private static volatile Queue<Bow> bows = new LinkedList<>();

	static {
		for (int i = 0; i < 3; i++){
			violins.add(new Violin(i+1));
		}
		for (int i = 0; i < 2; i++){
			bows.add(new Bow(i+1));
		}
	}

	public static void main(String[] args) {
		new Violinist("Andrew").start();
		new Violinist("Boriss").start();
		new Violinist("Sergii").start();
		new Violinist("Alexey").start();
	}

	private static class Violinist extends Thread{

		Violinist(String name){
			setName(name);
		}

		@Override
		public void run() {
			Violin violin;
			Bow bow;
			int i = 0;
			while (i < 3){
				synchronized (violins){
					if ((violin = violins.poll()) != null){
						System.out.println(getName() + " got " + violin);
					} else {
						System.out.println(getName() + " waiting for VIOLIN");
						try {
							currentThread().join(600);
						} catch (InterruptedException e) {
							e.printStackTrace();  //Exception here
						}
					}
				}

				sleepTime();

				synchronized (bows){
					if ((bow = bows.poll()) != null){

						System.out.println(getName() + " got " + bow);

						if (violin != null){
							System.out.println(getName() + " playing Beethoven on " + violin + " and " + bow);
						}

					} else {

						System.out.println(getName() + " waiting for BOW");
						try {
							currentThread().join(600);
						} catch (InterruptedException e) {
							e.printStackTrace();  //Exception here
						}
					}
				}

				if (bow != null) {
					bows.offer(bow);
					bow = null;
				}

				if (violin != null) {
					violins.offer(violin);
					violin = null;
				}

				i++;
			}

		}
	}

	private static void sleepTime(){
		try {
			Thread.sleep((int)(Math.random() * 751 + 250));
		} catch (InterruptedException e) {
			e.printStackTrace();  //Exception here
		}
	}
}

class Violin{
	private Integer id;

	Violin(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Violin #" + id;
	}
}

class Bow{
	private Integer id;

	Bow(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Bow #" + id;
	}
}