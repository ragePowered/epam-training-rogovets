package Lesson8.HW.Triangles;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/22/14
 * Time: 1:24 AM
 */

import java.io.IOException;
import java.util.Formatter;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Изобразить в приложении правильные треугольники, вращающиеся в
 * плоскости экрана вокруг своего центра. Каждому объекту соответствует
 * поток с заданным приоритетом. Через каждые 5 сек. производить
 * синхронизацию.
 */

public class Main {
	public static CopyOnWriteArrayList<EquilateralTriangle> arrayList = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws IOException{

		for (int i = 0; i < 5; i++){
			Point center = new Point(Math.random()*5 + 1, Math.random()*5 + 1);
			EquilateralTriangle triangle = EquilateralTriangle.build(center, Math.random() * 4 + 1);
			arrayList.add(triangle);
			Rotator rotator = new Rotator(i, Math.random());
			new Thread(rotator).start();
		}

	}
	public static void synchronize(int index, EquilateralTriangle triangle){
		arrayList.set(index, triangle);
	}
}

class Point{
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
		return new Formatter().format("{%4.3f, %4.3f}", x, y).toString();
	}
}

class Triangle{
	private Point a;
	private Point b;
	private Point c;

	Triangle(Point a, Point b, Point c) {
		if (isValid(a, b, c)){
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}

	private boolean isValid(Point a, Point b, Point c){
		double ab = Math.sqrt( Math.pow( a.getX() - b.getX(), 2 ) + Math.pow( a.getY() - b.getY(), 2 ) );
		double bc = Math.sqrt( Math.pow( b.getX() - c.getX(), 2 ) + Math.pow( b.getY() - c.getY(), 2 ) );
		double ac = Math.sqrt( Math.pow( a.getX() - c.getX(), 2 ) + Math.pow( a.getY() - c.getY(), 2 ) );
		return ( (ab + bc) - ac >= Double.MIN_VALUE);
	}

	public Point getA() {
		return a;
	}
	public void setA(Point a) {
		this.a = a;
	}
	public Point getB() {
		return b;
	}
	public void setB(Point b) {
		this.b = b;
	}
	public Point getC() {
		return c;
	}
	public void setC(Point c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "[ A = " + a + ", B = " + b + ", C = " + c + ']';
	}
}

class EquilateralTriangle extends Triangle{
	private Point center;
	private double radius;

	EquilateralTriangle(Point center, double radius, Point a, Point b, Point c){
		super(a, b, c);
		this.center = center;
		this.radius = radius;
		if (isEquilateral(a, b, c))
			System.out.println("Triangle is equilateral");
		else System.out.println("Triangle is equilateral");
	}

	private boolean isEquilateral(Point a, Point b, Point c){
		double ab = Math.sqrt( Math.pow( a.getX() - b.getX(), 2 ) + Math.pow( a.getY() - b.getY(), 2 ) );
		double bc = Math.sqrt( Math.pow( b.getX() - c.getX(), 2 ) + Math.pow( b.getY() - c.getY(), 2 ) );
		double ac = Math.sqrt( Math.pow( a.getX() - c.getX(), 2 ) + Math.pow( a.getY() - c.getY(), 2 ) );

		System.out.printf("Side length is %4.3f; ", ab);
		return ( Math.abs(ab - bc) <= Double.MIN_VALUE && Math.abs(bc - ac) <= Double.MIN_VALUE &&  Math.abs(ac - ab) <= Double.MIN_VALUE);
	}

	public static EquilateralTriangle build(Point center, double radius){
		Point a = new Point(center.getX(), center.getY() + radius);
		Point b = new Point(center.getX() + (Math.sqrt(3) * radius / 2), center.getY() - (radius / 2));
		Point c = new Point(center.getX() - (Math.sqrt(3) * radius / 2), center.getY() - (radius / 2));
		return new EquilateralTriangle(center, radius, a, b, c);
	}

	public void rotate(double speed){
		double angle = Math.atan( (this.getA().getY() - center.getY()) / (this.getA().getX() - center.getX()) );
		Point newA = new Point( center.getX() + radius * Math.cos( angle + speed ), center.getY() + radius * Math.sin( angle + speed ));
		this.setA(newA);
		angle = Math.atan( (this.getB().getY() - center.getY()) / (this.getB().getX() - center.getX()) );
		newA = new Point( center.getX() + radius * Math.cos( angle + speed ), center.getY() + radius * Math.sin( angle + speed ));
		this.setB(newA);
		angle = Math.atan( (this.getC().getY() - center.getY()) / (this.getC().getX() - center.getX()) );
		newA = new Point( center.getX() + radius * Math.cos( angle + speed ), center.getY() + radius * Math.sin( angle + speed ));
		this.setC(newA);
	}
}

class Rotator implements Runnable{
	private EquilateralTriangle triangle;
	private double speed;
	private int index;

	Rotator(int index, double speed) {
		this.index = index;
		this.triangle = Main.arrayList.get(index);
		this.speed = speed;
	}

	@Override
	public void run() {
		long start;
		start = System.currentTimeMillis();
		while (!Thread.currentThread().isInterrupted()){
			triangle.rotate(speed);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) { e.printStackTrace(); }
			if (System.currentTimeMillis() - start >= 5000){
				start = System.currentTimeMillis();
				Main.synchronize(index, triangle);
				System.out.println("Synchronized #" + index + " " + triangle);
			}
		}
	}
}