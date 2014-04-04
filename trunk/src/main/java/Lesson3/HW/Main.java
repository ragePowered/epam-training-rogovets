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
		House myHouse = new House();
		myHouse.addDoor();
		myHouse.addDoor();
		myHouse.addWindow();
		myHouse.addWindow();
		myHouse.addWindow();
		myHouse.lock();
		System.out.println("There is " + Window.getWindowsNumer() + " windows");
		System.out.println("There is " + Door.getDoorsNumber() + " doors");
	}
}

class House{
	private ArrayList<Window> windows = new ArrayList<Window>();
	private ArrayList<Door> doors = new ArrayList<Door>();

	public void lock(){
		System.out.println("House is locked!");
	}

	public void addWindow(){
		windows.add(new Window());
		System.out.println("Window added!");
	}

	public void addDoor(){
		doors.add(new Door());
		System.out.println("Door added");
	}

}

class Window{
	private static int windowsCounter;

	Window() {
		windowsCounter++;
	}

	public static int getWindowsNumer(){
		return windowsCounter;
	}
}

class Door{
	private static int doorsCounter;

	Door() {
		doorsCounter++;
	}

	public static int getDoorsNumber(){
		return doorsCounter;
	}
}