package com.softserve.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem1 {

	public static void main(String[] args) throws IOException {
		System.out.println("Please enter 2 numbers");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String a = reader.readLine();
		String b = reader.readLine();
		int n = Integer.parseInt(a);
		int m = Integer.parseInt(b);
		int count = 1;
		int k = 0;
		int z = n;

		if (n > 0 & m > 0) {
			while (n >= 10) {
				n = (n - n % 10) / 10;
				count++;
			}
			// System.out.println("Digits amount " + count);
		} else
			System.out.println("Entered negative number");

		if (m < count) {
			while (z > 10 & m > 0) {
				k = k + z % 10;
				z = (z - z % 10) / 10;
				m--;
			}
			System.out.println("Digits sum " + k);
		} else if (m == count) {
			while (z > 10 & m > 1) {
				k = k + z % 10;
				z = (z - z % 10) / 10;
				m--;
			}
			k = k + z;
			System.out.println("Digits sum " + k);
		}

		else
			System.out.println("m is so big");

	}

}
