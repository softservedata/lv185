package com.softserve.edu.xtralife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
330. Натуральное число называется совершенным, если оно
равно сумме всех своих делителей, за исключением себя самого. Число
6 – совершенное, так как 6 = 1+2+3. Число 8 – не совершенное, так как
8 ≠ 1+2+4.Дано натуральное число n. Получить все совершенные числа,
меньшие n.
*/
public class Task330 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter natural number:");
		int number = Integer.parseInt(sc.nextLine());
		sc.close();
		for (int i = 1; i < number; i++) {
			if (isPerfectNumber(i)) {
				System.out.println("Number " + i + " is perfect.");
			}
		}
	}

	private static boolean isPerfectNumber(int number) {
		List<Integer> numberDividers = dividers(number);
		int sumDividers = numberDividers.stream().mapToInt(n -> n).sum();
		return (number == sumDividers);
	}

	private static List<Integer> dividers(int number) {
		List<Integer> dividers = new ArrayList<>();
		for (int i = 1; i < number; i++) {
			if (number % i == 0) {
				dividers.add(i);
			}
		}
		return dividers;
	}
}
