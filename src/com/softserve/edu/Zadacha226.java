package com.softserve.edu;

import java.util.Scanner;

public class Zadacha226 {
	
	public static void main (String[] args) {
		
		System.out.println("Enter two different numbers:");
		  Scanner input = new Scanner(System.in);
		  int n = input.nextInt();
		  int m = input.nextInt();
		  int min;
		  
		  if (n < m)
			  min = n;
		  else
			  min = m;
		  
		  System.out.print("Common divisors: ");
		  for (int i = 1; i < min; i++) {
			  if (n % i == 0 && m % i == 0)
				  System.out.print(i + "  ");
		  }
	}

}
