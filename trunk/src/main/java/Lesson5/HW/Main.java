package Lesson5.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/8/14
 * Time: 4:56 PM
 */

/**
 * Построить иерархию объектов. Последовательно отобразить объекты,
 * сдвинуть, изменить размеры, спрятать.
 *
 * Координаты - точка - окружность - сектор
 */

public class Main {
	public static void main(String[] args) {
		Point point1 = new Point(2, 2);
		Circle circle = new Circle(point1, 2);
		Circle.Sector sector = circle.new Sector(new Point(3, 0), new Point(0, 1), circle);
	}
}

abstract class Figure{
	abstract public String toString();
}

class Point extends Figure implements Seeable, Moveable{
	private double x;
	private double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return ("x = " + x + "; y = " + y);
	}

	@Override
	public void moveTo(Point newCoordinates) {
		System.out.println(this.toString() + " moved to " + newCoordinates.toString());
		this.x = newCoordinates.getX();
		this.y = newCoordinates.getY();
	}
}

class Circle extends Figure implements Seeable, Moveable, Mutable{
	private Point center;
	private double radius;

	Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
	}

	@Override
	public String toString() {
		return ("Circle { " + this.center + ", radius = " + this.radius + " }");
	}

	@Override
	public void moveTo(Point newCoordinates) {
		System.out.println(this.toString() + " moved to " + newCoordinates);
		this.center = newCoordinates;
	}

	@Override
	public void mutate(double ratio) {
		System.out.println("Radius changed from " + this.radius + " to " + (this.radius *= ratio));
	}

	class Sector extends Figure implements Mutable, Moveable, Seeable{
		private Point p1;
		private Point p2;
		private Circle outer;
		private double angle;

		Sector(Point p1, Point p2, Circle outer) {
			this.p1 = p1;
			this.p2 = p2;
			this.outer = outer;
			double a1 = (p1.getY() - center.getY()) / (p1.getX() - center.getX());
			double a2 = (p2.getY() - center.getY()) / (p2.getX() - center.getX());
			angle = Math.atan((a2 - a1) / (1 + a1 * a2));
		}

		@Override
		public String toString() {
			return ("Sector { center =" + center + ", radius = " + radius + ", angle = " + angle);
		}

		@Override
		public void mutate(double ratio) {
			System.out.print("Sector: ");
			outer.mutate(ratio);
		}

		@Override
		public void moveTo(Point newCoordinates) {
			System.out.print("Sector: ");
			outer.moveTo(newCoordinates);
		}
	}
}