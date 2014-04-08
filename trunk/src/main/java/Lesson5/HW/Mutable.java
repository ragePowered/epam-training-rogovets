package Lesson5.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/8/14
 * Time: 11:23 PM
 */

interface Mutable{
	void mutate(double ratio);
}

interface Moveable{
	void moveTo(Point newCoordinates);
}

interface Seeable{
	default void show(){
		System.out.println(this.toString() + " appeared!");
	}
	default void hide(){
		System.out.println(this.toString() + " disappeared...");
	}
}