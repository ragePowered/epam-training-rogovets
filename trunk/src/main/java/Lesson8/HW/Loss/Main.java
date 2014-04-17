package Lesson8.HW.Loss;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/16/14
 * Time: 5:42 PM
 */

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException{

		Map map = new HashMap();

		Key k1 = new Key(1, 2);
		map.put(k1, "k1");

		Key cloneK1 = k1.clone();

		Key k2 = new Key(3, 4);
		map.put(k2, "k2");

		System.out.println("Before " + map);
		k1.setI(5);	k1.setJ(5);
		System.out.println("After  " + map);

		System.out.println("Trying get value with k1 ---- (" + k1 + ") - " + map.get(k1));
		System.out.println("Trying get value with cloneK1 (" + cloneK1 + ") - " + map.get(cloneK1));

	}
}

class Key implements Cloneable{
	int I;
	int J;

	Key(int i, int j) {
		I = i;
		J = j;
	}

	public int getI() {
		return I;
	}

	public void setI(int i) {
		I = i;
	}

	public int getJ() {
		return J;
	}

	public void setJ(int j) {
		J = j;
	}

	@Override
	public String toString() {
		return "[" +
				"I=" + I +
				", J=" + J +
				']';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Key)) return false;

		Key key = (Key) o;

		if (I != key.I) return false;
		if (J != key.J) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = I;
		result = 31 * result + J;
		return result;
	}

	@Override
	protected Key clone() throws CloneNotSupportedException {
		return new Key(this.I, this.J);
	}
}
