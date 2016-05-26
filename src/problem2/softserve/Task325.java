package problem2.softserve;

import java.util.ArrayList;
//import java.util.Scanner;

public class Task325 {

//	private static Scanner sc;
	ArrayList<Integer> numbers = new ArrayList<>();

	public boolean isSimple(int number) {
		boolean simple = true;
		for (int i = 2; i < number; i++) {
			if (number % i == 0) {
				simple = false;
				break;
			}
		}
		if (simple) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Integer> getAllNumbers(int number) {

		int count = 1;
		while (count <= number) {
			if (number % count == 0) {
				if (isSimple(count)) {
					numbers.add(count);
				}
				count++;

			} else {
				count++;
			}
		}

		return numbers;
	}

//	public static void main(String[] args) {
//		
//		Task325 ts3 = new Task325();
//
//		System.out.print("Type the number: ");
//		sc = new Scanner(System.in);
//		int num = sc.nextInt();
//		System.out.println(ts3.getAllNumbers(num));
//	}

}
