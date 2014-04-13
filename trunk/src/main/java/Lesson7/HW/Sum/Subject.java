package Lesson7.HW.Sum;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/14/14
 * Time: 1:47 AM
 */

public interface Subject {
	public void notify(int sum);
}

class Calculator implements Subject{
	private ArrayList<Worker> listOfObservers = new ArrayList<>();
	private int sum = 0;
	private int invocaions = 0;

	Calculator(int numberOfWorkers) {
		create(numberOfWorkers);
		while (invocaions != numberOfWorkers);
		System.out.println(sum);
	}

	@Override
	synchronized public void notify(int sum) {
		this.sum += sum;
		++this.invocaions;
	}

	private void create(int numberOfWorkers) {
		for (int i = 0; i < numberOfWorkers; i++){
			listOfObservers.add(new Worker(this, i, 1000/numberOfWorkers));
			listOfObservers.get(i).start();
		}
	}
}
