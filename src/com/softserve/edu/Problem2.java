package com.softserve.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem2 {

	public static void main(String[] args) throws IOException {
		System.out.println("Please enter a number");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String a = reader.readLine();
		int n = Integer.parseInt(a);
		int x = 0;
		int y = 0;

		if (n > 0) {
			int t = (int) Math.sqrt(n);
//			System.out.println(t);
			
			for (int i = 0; i <= t; i++) {
				x = i;
				y = (int) Math.sqrt(n - x * x);
				if ( (x * x + y * y) == n & x >= y)
					System.out.println("x = " + x + " " + "y = " + y);
			}
			System.out.println("Other pairs of numbers does not exist");
		} else
			System.out.println("Entered negative number");

	}

}
