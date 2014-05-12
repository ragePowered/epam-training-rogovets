package Lesson9.HW.PetersonsAlgorithm;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 5/10/14
 * Time: 4:11 PM
 */

/**
 * Реализовать собственный mutex на основе алгоритма Петерсона
 * */

public class Main {
	static volatile int loser;
	static volatile int[] interested  = {0, 0};

	public static void main(String[] args) {
		new Thread(new Patient(0)).start();
		new Thread(new Patient(1)).start();
	}

	public static void mutEx(int me){
		int other = 1 - me;
		interested[me] = 1;
		loser = me;

		while (loser == me && loser == interested[other]);
//	Critical block starts here
		System.out.println("Critical block from thread " + me);
		emulateWork();
//	Critical block ends here
		interested[me] = 0;
	}

	private static void emulateWork(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();  //Exception here
		}
	}

}

class Patient implements Runnable{
	private int id;

	Patient(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		Main.mutEx(id);
	}
}

