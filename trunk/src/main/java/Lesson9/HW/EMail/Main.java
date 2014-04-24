package Lesson9.HW.EMail;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/25/14
 * Time: 1:17 AM
 */

public class Main {
	public static void main(String[] args) {
		isEmail("andrew.rogovets@gmail.com");



	}

	public static boolean isEmail(String email){
		String emailPattern = "[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})";
		boolean result = Pattern.compile(emailPattern).matcher(email).matches();
		System.out.println(email + " --- " + result);
		return result;
	}
}
