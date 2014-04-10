package Lesson6.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/9/14
 * Time: 9:10 PM
 */

interface Primitive {}

class Point implements Primitive{
	private double x;
	private double y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return ("{" + x + ", " + y + "}");
	}
}

class Line implements Primitive{
	private Point start;
	private Point end;

	Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public double getLength(){
		return (Math.sqrt((Math.pow(start.getX() - end.getX(), 2)) + (Math.pow(start.getY() - end.getY(), 2))));
	}

	@Override
	public String toString() {
		return ("[" + start + ", " + end + "]");
	}
}
