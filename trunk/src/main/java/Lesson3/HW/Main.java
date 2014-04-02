package Lesson3.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/3/14
 * Time: 12:48 AM
 */

/**
 * Создать приложение, удовлетворяющее требованиям, приведенным в задании.
 * Аргументировать принадлежность классу каждого создаваемого метода и корректно
 * переопределить для каждого класса методы equals(), hashCode(), toString().
 *
 * Создать объект класса Самолет, используя класс Крыло.
 * Методы: летать, задавать маршрут, вывести на консоль маршрут.
 *
 */

public class Main {
	public static void main(String[] args) {
		Airplane plane = new Airplane(new NormalWing());
		plane.wing.fly();
		plane.specifyRoute();
		plane.showRoute();
	}
}

class Airplane{
	Wing wing;

	Airplane(Wing wing) {
		this.wing = wing;
	}

	public void setWing(Wing wing) {
		this.wing = wing;
	}

	public void specifyRoute(){
		System.out.println("Route confirmed");
	}

	public void showRoute(){
		System.out.println("My route is: Kiev - Istanbul");
	}
}

interface Wing{
	public void fly();
}

class NormalWing implements Wing{
	public void fly(){
		System.out.println("I can fly!");
	}
}

class BrokenWing implements Wing{
	public void fly(){
		System.out.println("I can'T fly!");
	}
}