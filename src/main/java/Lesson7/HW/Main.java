package Lesson7.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/13/14
 * Time: 6:42 PM
 */

/**
 * Есть 2 скрипача, скрипока и смычок. Смоделировать Deadlock и разрешить его
 * стандартными средствами Java 1.4
 */

public class Main {
	public static final Violin violin = new Violin();
	public static final Bow bow = new Bow();

	public static void main(String[] args) {
		new Violinist().start();
		new LeftHandViolinist().start();
	}

	private static class Violinist extends Thread{
		@Override
		public void run() {
			synchronized (violin){
				System.out.println("Vilinist holding " + violin);
				sleepTime();
				System.out.println("Vilinist waiting to take " + bow);
				synchronized (bow){
					System.out.println("Vilinist playing Beethoven");
				}
			}
		}
	}

	private static class LeftHandViolinist extends Thread{
		@Override
		public void run() {
			synchronized (violin){
				System.out.println("Left handed vilinist holding " + violin);
				sleepTime();
				System.out.println("Left handed vilinist waiting to take " + bow);
				synchronized (bow){
					System.out.println("Left handed vilinist playing Beethoven");
				}
			}
		}
	}

	private static void sleepTime(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();  //Exception here
		}
	}
}

class Violin{
	@Override
	public String toString() {
		return "violin";
	}
}

class Bow{
	@Override
	public String toString() {
		return "bow";
	}
}