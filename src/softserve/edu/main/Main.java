package softserve.edu.main;

import java.util.Scanner;

import softserve.edu.problem1.Task88b;
import softserve.edu.problem1.Task88h;
import softserve.edu.problem2.Task325;
import softserve.edu.problem2.Task332;
import softserve.edu.problem3.Task243a;
import softserve.edu.problem3.Task561;

public class Main {
	private static Scanner sc;

	public static void main(String[] args) {
		System.out.print("Please type number: ");
		sc = new Scanner(System.in);
		int num = sc.nextInt();
		//reversing number
		System.out.println("Problem 88b: ");
		Task88b ts = new Task88b();
		System.out.println(ts.replaceFirstAndLast(num));
		//adding one at front and end
		System.out.println("Problem 88h: ");
		Task88h ts2 = new Task88h();
		System.out.println(ts2.addDigitOne(num));
		//all simple dividers
		System.out.println("Problem 325: ");
		Task325 ts3 = new Task325();
		System.out.println(ts3.getAllNumbers(num));
		// Lagrange theorem
		System.out.println("Problem 332: ");
		Task332 ts4 = new Task332();
		ts4.findFourSqrt(num);
		//all dividers with contain same root numbers
		System.out.println("Problem 561: ");
		Task561 ts5 = new Task561();
		System.out.println(ts5.findMatches(num));
		System.out.println(ts5.findAllNumbers(num));
		//theorem Ferma-Ejlera
		System.out.println("Problem 243a: ");
		Task243a ts6 = new Task243a();
		ts6.findTwoSqrt(num);
	}

}
