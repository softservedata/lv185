package com.softserve.edu;

public class Zadacha560 {
	public static void main (String[] args) {
		
		for (int i = 200; i <= 300; i++) {
			int sum = 0;
			for (int j = 1; j < i; j++) {
				if (i % j == 0) {
					sum += j;
				}
			}
			int temp = 0;
			for (int k = 1; k< sum; k++) {
				if (sum % k == 0) {
					temp += k;
				}
			}
			if (temp == i && sum != i) {
				System.out.printf("Amicable numbers: %d  %d \n", i, sum);
			}
		}
	}
}
