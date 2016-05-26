package problem1.softserve;

//import java.util.Scanner;

public class Task88b {

	//private static Scanner sc;
	
	public static char[] makeChar(int number) {
		
		int digit;
		String newNumber = "";

		while (number > 0) {
			digit = number % 10;
			newNumber = digit + newNumber;
			number = number / 10;
		}
		char[] result = newNumber.toCharArray();
		
		return result;
		
	}

	public int replaceFirstAndLast(int number) {
		char [] digits = makeChar(number);
		char replaser = digits[0];
		digits[0] = digits[digits.length - 1];
		digits[digits.length - 1] = replaser;

		return Integer.parseInt(new String(digits));
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Task88b ts = new Task88b();
//
//		System.out.print("Type the number: ");
//		sc = new Scanner(System.in);
//		int num = sc.nextInt();
//
//		System.out.println(ts.replaceFirstAndLast(num));
//	}

}
