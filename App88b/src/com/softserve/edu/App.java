package com.softserve.edu;

import java.util.Scanner;

public class App {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int i;
		int inverse = 0;	
		
			
			System.out.println("Enter please a natural number:");
			
			if (sc.hasNextInt()){				
				i = sc.nextInt();				
				while(i!=0){
					inverse = inverse*10 + (i%10);
					i = i/10;
				}
				System.out.println("Inverse number is "+inverse);				
				
			} else { 			
				System.out.println("It is not a natural number!");
			}
			
			sc.close();				
			
	}
		
		
}
