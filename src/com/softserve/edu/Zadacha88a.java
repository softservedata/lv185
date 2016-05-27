package com.softserve.edu;

import java.util.Scanner;

public class Zadacha88a {
	public static void main (String[] args) {
		
		System.out.println("Enter the natural number:");
		  Scanner input = new Scanner(System.in);
		  
		  int n = input.nextInt();
		  int n2 = n * n;
		  String num = String.valueOf(n2);
		  boolean isdigit = false;
		  
		  for (int i = 0; i < num.length(); i++){
			  char c = num.charAt(i);        
              if (c == '3') {
            	  isdigit = true;
            	  break;
              }
		  }
        	  System.out.printf("There is digit 3 in the number %d: %b", n2, isdigit);
	}
}
