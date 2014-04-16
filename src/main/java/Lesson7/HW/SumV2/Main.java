package Lesson7.HW.SumV2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/14/14
 * Time: 7:43 AM
 */

/**
 * Найти сумму 1+2+3+...+1000. Используя при этом n-потоков.
 * Запрещено использовать метод join.
 */

public class Main {
	public static void main(String[] args) {
		new Calculator(5).calculate();
	}
}

class Calculator{
	private ExecutorService service;
	private List<Worker> tasks = new ArrayList<>();
	private int result = 0;

	Calculator(int numberOfWorkers) {
		service = Executors.newFixedThreadPool(numberOfWorkers);
		for (int i = 0; i < numberOfWorkers; i++){
			tasks.add(new Worker(i, 1000/numberOfWorkers));
		}
	}

	public void calculate(){
		try {
			for (Future<Integer> each : service.invokeAll(tasks)){
					result += each.get();
			}
		} catch (InterruptedException e) {e.printStackTrace();
		} catch (ExecutionException e) {e.printStackTrace();
		}
		service.shutdown();
		System.out.println("Result is " + result);
	}
}

class Worker implements Callable<Integer>{
	private int id;
	private int quantum;

	Worker(int id, int quantum) {
		this.id = id;
		this.quantum = quantum;
	}

	@Override
	public Integer call() throws Exception {
		int result = 0;
		for (int i = id * quantum; i < (id + 1) * quantum; i++){
			result += i;
		}
		System.out.println(Thread.currentThread().getName() + " - " + result);
		return result;
	}
}