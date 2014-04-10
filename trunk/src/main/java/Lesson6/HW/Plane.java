package Lesson6.HW;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/11/14
 * Time: 2:04 AM
 */

public class Plane {
	private static Plane instance = new Plane();
	private LinkedList<Figure> list = new LinkedList<>();
	private AbstractFigureFactory factory = new FigureFactory();

	private Plane() {
	}

	public static Plane getInstance(){
		return instance;
	}

	public LinkedList getAsList(){
		list.add(factory.createFuselage());
		list.add(factory.createLeftWing());
		list.add(factory.createRightWing());
		list.add(factory.createTail());
		for (int i = 1; i < 6; i++){
			list.add(factory.createPorthole(i));
		}
		return list;
	}
}
