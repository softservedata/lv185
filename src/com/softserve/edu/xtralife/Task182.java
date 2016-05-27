package com.softserve.edu.xtralife;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/*
182. Даны натуральное число n, целые числа a1, ... , an. Найти
количество и сумму тех членов данной последовательности, которые
делятся на 5 и не делятся на 7.
*/
public class Task182 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter length of initial array:");
		int sequenceLength = Integer.parseInt(sc.nextLine());
		sc.close();
		int[] sequence = getRandomIntegerArray(sequenceLength, 100);
		int count = 0;
		int sum = 0;
		for (int number : sequence) {
			if (isDividedInto(number, 5) && !isDividedInto(number, 7)) {
				count++;
				sum += number;
			}
		}
		System.out.println("Initial array:");
		System.out.println(Arrays.toString(sequence));
		System.out.println("Looking for numbers divided into 5 and not divided into 7.");
		System.out.println("Count of numbers: " + count);
		System.out.println("Sum of numbers: " + sum);
	}

	private static int[] getRandomIntegerArray(int length, int maxRandomNumber) {
		int[] ar = new int[length];
		Random rnd = new Random();
		for (int i = 0; i < length; i++) {
			ar[i] = rnd.nextInt(maxRandomNumber);
		}
		return ar;
	}

	private static boolean isDividedInto(int number, int divider) {
		return (number % divider == 0);
	}
}
