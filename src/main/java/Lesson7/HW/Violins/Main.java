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
 * Запрограммировать задачу о скрипачах. Есть m скрипачей, n скрипок и k
 * смычков. Скрипачи играют по очереди, для того, чтобы скрипачу сыграть на
 * скрипке ему нужна скрипка и смычок. Есть три очереди: очередь скрипачей
 * скрипок и смычков.
 */

public class Main {
	private static volatile Queue<Violin> violins = new LinkedList<>();
	private static volatile Queue<Bow> bows = new LinkedList<>();


	static {
		for (int i = 0; i < 1; i++){
			violins.add(new Violin(i+1));
		}
		for (int i = 0; i < 1; i++){
			bows.add(new Bow(i+1));
		}
	}

	public static void main(String[] args) {
		for (int i = 1; i <= 100; i++){
			new Violinist("#" + i).start();
		}
	}

	private static class Violinist extends Thread{

		Violinist(String name){
			setName(name);
		}

		@Override
		public void run() {
			Violin violin;
			Bow bow;

			violin = violins.poll();
			if (violin != null){
				System.out.println(getName() + ": got violin. Trying to take bow");
				bow = bows.poll();
				if (bow != null){

					System.out.println(getName() + ": got violin and bow! Playing Beethoven");

					violins.add(violin);
					synchronized (violins){ violins.notifyAll(); }
					bows.add(bow);
					synchronized (bows){ bows.notifyAll();}

				} else {

					violins.add(violin);
					synchronized (violins){ violins.notifyAll(); }

					System.out.println(getName() + ": no bows! Returning captured violin");
					synchronized (bows){
						try { bows.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
					}
				}

			} else {
				System.out.println(getName() + ": no violins!");
				synchronized (violins) {
					try { violins.wait(); } catch (InterruptedException e) { e.printStackTrace(); }
				}
			}
		}
	}
}

class Violin{
	private Integer id;

	Violin(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Bow #" + id;
	}
}