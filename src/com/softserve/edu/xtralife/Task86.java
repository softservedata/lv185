package com.softserve.edu.xtralife;

import java.util.Scanner;

/*
86. Дано натуральное число n.
a) Сколько цифр в числе n.
*/
public class Task86 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter natural number:");
		int number = Integer.parseInt(sc.nextLine());
		sc.close();
		System.out.println("Number " + number + " has " + digitCount(number) + " digits.");
	}

	private static int digitCount(int number) {
		if (number < 1) {
			throw new IllegalArgumentException("Incorrect number");
		}
		int digitCount = 0;
		do {
			digitCount++;
		} while ((number /= 10) > 0);
		return digitCount;
	}
}
