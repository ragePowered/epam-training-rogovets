package Lesson3.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/3/14
 * Time: 12:48 AM
 */

import java.util.ArrayList;

/**
 * Создать приложение, удовлетворяющее требованиям, приведенным в задании.
 * Аргументировать принадлежность классу каждого создаваемого метода и корректно
 * переопределить для каждого класса методы equals(), hashCode(), toString().
 *
 * Создать объект класса Дом, используя классы Окно, Дверь.
 * Методы: закрыть на ключ, вывести на консоль количество окон, дверей.
 */

public class Main {
	public static void main(String[] args) {
		Door mainDoor = new Door("Main door");
		Door kitchenDoor = new Door("Kitchen door");

		Window smallWindow = new Window("Small");
		Window largeWindow = new Window("Large");

		House myHouse = new House("my house");
		myHouse.addDoor(mainDoor);
		myHouse.addDoor(kitchenDoor);
		myHouse.addWindow(largeWindow);
		myHouse.addWindow(largeWindow);
		myHouse.addWindow(smallWindow);

		House mySecondHouse = new House("my second house");
		mySecondHouse.addDoor(mainDoor);
		mySecondHouse.addDoor(kitchenDoor);
		mySecondHouse.addWindow(largeWindow);
		mySecondHouse.addWindow(largeWindow);
		mySecondHouse.addWindow(smallWindow);

		myHouse.getWindowsNumber();
		mySecondHouse.getWindowsNumber();
		myHouse.getDoorsCounter();
		mySecondHouse.getDoorsCounter();

		System.out.println("My house is equals to my second house? - " + myHouse.equals(mySecondHouse));

		myHouse.lock();
	}
}

class House{
	private ArrayList<Window> windows = new ArrayList<Window>();
	private ArrayList<Door> doors = new ArrayList<Door>();
	private String name;

	House(String name) {
		this.name = name;
	}

	public void lock(){
		System.out.println("House is locked!");
	}

	public void addWindow(Window window){
		windows.add(window);
		System.out.println("Window added!");
	}

	public void addDoor(Door door){
		doors.add(door);
		System.out.println("Door added");
	}

	public void getWindowsNumber(){
		System.out.println("There is " + this.windows.size() + " windows in " + this.name);
	}

	public void getDoorsCounter(){
		System.out.println("There is " + this.doors.size() + " doors in " + this.name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof House)) return false;

		House house = (House) o;

		if (!doors.equals(house.doors)) return false;
		if (!windows.equals(house.windows)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = windows.hashCode();
		result = 31 * result + doors.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "House{" +
				"windows=" + windows +
				", doors=" + doors +
				", name='" + name + '\'' +
				'}';
	}
}

class Window{
	private String size;

	Window(String size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Window)) return false;

		Window window = (Window) o;

		return size.equals(window.size);
	}

	@Override
	public int hashCode() {
		return size.hashCode();
	}

	@Override
	public String toString() {
		return "Window{" +
				"size='" + size + '\'' +
				'}';
	}
}

class Door{
	private String location;

	Door(String location) {
		this.location = location;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Door)) return false;

		Door door = (Door) o;

		return location.equals(door.location);
	}

	@Override
	public int hashCode() {
		return location.hashCode();
	}

	@Override
	public String toString() {
		return "Door{" +
				"location='" + location + '\'' +
				'}';
	}
}