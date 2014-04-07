package Lesson5;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/7/14
 * Time: 4:33 PM
 */

public class Main {
	public static void main(String[] args) {
		B pb = (int i) -> System.out.println("Lambda test " + i);

		Method[] methods = pb.getClass().getDeclaredMethods();
		for (Method each : methods){
			System.out.println(each.getName() + " from " + each.getDeclaringClass().getName());
		}
	}
}

@FunctionalInterface
interface B{
	void g(int i);
}
