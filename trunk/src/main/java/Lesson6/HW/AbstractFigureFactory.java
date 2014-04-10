package Lesson6.HW;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/11/14
 * Time: 12:44 AM
 */

public interface AbstractFigureFactory {
	Figure createFuselage();
	Figure createPorthole(int number);
	Figure createLeftWing();
	Figure createRightWing();
	Figure createTail();

}

class FigureFactory implements AbstractFigureFactory{

	@Override
	public Figure createFuselage() {
		return new Ellipse(8., 2., new Point(9., 8.));
	}

	@Override
	public Figure createPorthole(int number) {
		return new Ellipse(0.5, 1., new Point(4 + (number - 1) * 1.5, 8.));
	}

	@Override
	public Figure createLeftWing() {
		return new Triangle(new Point(9., 6.), new Point(12., 6.), new Point(12., 1.));
	}

	@Override
	public Figure createRightWing() {
		return new Triangle(new Point(9., 10.), new Point(12., 10.), new Point(12., 15.));
	}

	@Override
	public Figure createTail() {
		return new Triangle(new Point(16., 8.), new Point(19., 8.), new Point(19., 13.));
	}
}