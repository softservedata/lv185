package problem1.softserve;

//import java.util.Scanner;

public class Task88h {
//	private static Scanner sc;

	public int addDigitOne(int number) {
		
		char [] digits = Task88b.makeChar(number);
		char [] newOne = new char[digits.length +2];
		newOne[0] = '1';
		newOne[newOne.length - 1] = '1';
		for(int i = 1; i < newOne.length -1;i++) {
			newOne[i] = digits[i-1];
		}
		return Integer.parseInt(new String(newOne));
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		System.out.print("Type the number for adding 1 at the start and at the end: ");
//		sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		Task88h ts2 = new Task88h();
//		System.out.println(ts2.addDigitOne(num));
//	}

}
