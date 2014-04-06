package Lesson4.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/6/14
 * Time: 7:57 PM
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Построить модель программной системы.
 *
 * Система Жилищно-коммунальные услуги. Квартиросъемщик отправляет Заявку,
 * в которой указывает род работ, масштаб и желаемое время выполнения.
 * Диспетчер формирует соответствующую Бригаду и регистрирует её в Плане работ.
 * Диспетчер может отклонить Заявку в случае занятости всех Бригад.
 */

public class Main {
	public static void main(String[] args) {
		HousingUtilities utilities = new HousingUtilities();
		utilities.createBrigades();

		Lodger lodger = new Lodger("Andrew Rogovets");
		Task task = new Task(Task.Maintenance.EXAMINATION, Task.AmountOfWork.SMALL, 30);
		Request request = new Request(lodger, task);

		utilities.addRequestToWorkPlan(request);
		utilities.addRequestToWorkPlan(request);
		utilities.addRequestToWorkPlan(request);

		utilities.finishWorkPlan();

	}
}

class HousingUtilities{
	private ArrayList<Request> workplan = new ArrayList<Request>();
	private HashMap<Brigade, Request> brigadesMap = new HashMap<Brigade, Request>();

	public void addRequestToWorkPlan(Request request){
		workplan.add(request);
	}

	public void createBrigades(){
		for (int i = 0; i < 2; i++){
			brigadesMap.put(new Brigade("Brigade #" + (i+1)), null);
		}
	}

	public void finishWorkPlan(){
		while (workplan.size() > 0){
			if (assignRequestToBrigade(workplan.get(workplan.size() - 1))){
				System.out.println("Brigade on the way");
				workplan.remove(workplan.size() - 1);
				System.out.println("Workplan shrinked!");
			} else {
				System.out.println("All brigades is busy right now...");
				return;
			}
		}
		System.out.println("Workplan finished! I can go home now");
	}

	private boolean assignRequestToBrigade(Request request){
		for (Map.Entry each : brigadesMap.entrySet()){
			if (each.getValue() == null){
				each.setValue(request);
				return true;
			}
		}
		return false;
	}

}

class Request{
	private Lodger requester;
	private Task task;

	Request(Lodger requester, Task task) {
		this.requester = requester;
		this.task = task;
	}

	public Lodger getRequester() {
		return requester;
	}
	public void setRequester(Lodger requester) {
		this.requester = requester;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Request{" +
				"requester=" + requester +
				", task=" + task +
				'}';
	}
}

class Lodger{
	private String name;

	Lodger(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Lodger{" +
				"name='" + name + '\'' +
				'}';
	}
}

class Task{
	public enum Maintenance {
		REPAIR, EXAMINATION, CONSULTATION
	}
	public enum AmountOfWork{
		SMALL, AVERAGE, HUGE
	}

	private Maintenance maintenance;
	private AmountOfWork amount;
	private int duration;

	Task(Maintenance maintenance, AmountOfWork amount, int duration) {
		this.maintenance = maintenance;
		this.amount = amount;
		this.duration = duration;
	}

	public Maintenance getMaintenance() {

		return maintenance;
	}
	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}
	public AmountOfWork getAmount() {
		return amount;
	}
	public void setAmount(AmountOfWork amount) {
		this.amount = amount;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "Task{" +
				"maintenance=" + maintenance.toString() +
				", amount=" + amount.toString() +
				", duration=" + duration +
				'}';
	}
}

class Brigade{
	private String name;

	Brigade(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Brigade)) return false;

		Brigade brigade = (Brigade) o;

		if (!name.equals(brigade.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "Brigade{" +
				"name='" + name + '\'' +
				'}';
	}
}