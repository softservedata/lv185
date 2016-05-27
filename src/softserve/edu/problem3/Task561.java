package softserve.edu.problem3;

import java.util.ArrayList;
//import java.util.Scanner;

public class Task561 {

	//private static Scanner sc;

	public ArrayList<Integer> findAllNumbers(int number) {
		ArrayList<Integer> numbers = new ArrayList<>();
		int i = 0;
		while (i < number) {
			i++;
			numbers.add(i);
		}

		return numbers;
	}

	public ArrayList<Integer> findMatches(int number) {
		ArrayList<Integer> matches = new ArrayList<>();
		ArrayList<Integer> numbers = findAllNumbers(number);
		for (int i : numbers) {

			if (i == i * i % divider(i)) {
				matches.add(i);
			}
		}

		return matches;

	}

	public int divider(int number) {
		int count = 0;
		while (number > 0) {
			number = number / 10;
			count++;
		}
		return (int) Math.pow(10, count);
	}

//	public static void main(String[] args) {
//
//		sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		Task561 ts5 = new Task561();
//		System.out.println(ts5.findMatches(num));
//		System.out.println(ts5.findAllNumbers(num));
//	}

}
