package Lesson7.HW.Sum;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/14/14
 * Time: 1:51 AM
 */

public interface Observer {
}

class Worker extends Thread implements Observer{
	private Subject subject;
	private Integer id;
	private Integer quantum;

	Worker(Subject subject, Integer id, Integer quantum) {
		this.subject = subject;
		this.id = id;
		this.quantum = quantum;
	}

	@Override
	public void run() {
		int result = 0;
		for (int i = id * quantum; i < (id + 1) * quantum; i++){
			result += i;
		}
		subject.notify(result);
	}
}