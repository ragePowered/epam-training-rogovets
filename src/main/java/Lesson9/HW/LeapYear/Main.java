package Lesson9.HW.LeapYear;

import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: andrew
 * Date: 4/25/14
 * Time: 12:08 AM
 */

public class Main {
	public static void main(String[] args) {
		isLeap("2004");
		isLeap("1800");
		isLeap("2400");
		isLeap("400");
		isLeap("3002");
		isLeap("2001");

	}

	public static boolean isLeap(String year){
		String mod4StrExc = "(04)|(08)";
		String mod4Str = "(12)|(16)|(20)|(24)|(28)|(32)|(36)|(44)|(48)|(52)|(56)|(60)|(64)|(68)|(72)|(76)|(80)|(84)|(88)|(92)|(96)";

		boolean mod4 = Pattern.compile(".*(" + mod4StrExc + "|" + mod4Str + ")").matcher(year).matches();
		boolean mod100 = Pattern.compile(".*00").matcher(year).matches();
		boolean mod400 = Pattern.compile("(400|800)|(.*(" + mod4Str + ")00)").matcher(year).matches();

		if ( (!mod100 && mod4) || mod400){
			System.out.println(year + " Leap");
			return true;
		} else {
			System.out.println(year + " not Leap");
			return false;
		}

	}
}