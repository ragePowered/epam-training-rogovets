package Lesson6.HW;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/11/14
 * Time: 12:41 AM
 */

public interface Figure {

}

class Ellipse implements Figure{
	private ArrayList<Point> points = new ArrayList<>();
	private double semiMajorAxis;
	private double semiMinorAxis;
	private Point center;

	Ellipse(double semiMajorAxis, double semiMinorAxis, Point center) {
		this.semiMajorAxis = semiMajorAxis;
		this.semiMinorAxis = semiMinorAxis;
		this.center = center;
		for (double i = 0; i < 2 * Math.PI; i += 0.1){
			points.add(new Point( (center.getX() + semiMajorAxis * Math.cos(i)), (center.getY() + semiMinorAxis * Math.sin(i)) ));
		}
	}

	@Override
	public String toString() {
		return "Ellipse {\n" +
				"\tpoints=" + Arrays.toString(points.toArray()) +
				",\n\tsemiMajorAxis=" + semiMajorAxis +
				", semiMinorAxis=" + semiMinorAxis +
				", center=" + center +
				"\n}\n";
	}
}

class Triangle implements Figure{
	private Line A;
	private Line B;
	private Line C;

	Triangle(Point a, Point b, Point c) {
		A = new Line(a, b);
		B = new Line(b, c);
		C = new Line(c, a);
	}

	@Override
	public String toString() {
		return "Triangle {" +
				"\n\tA=" + A +
				"\n\tB=" + B +
				"\n\tC=" + C +
				"\n}\n";
	}
}