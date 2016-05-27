package com.softserve.edu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter please natural number: ");
		
		if (sc.hasNextInt()){
			int n = sc.nextInt();
			List<Integer> listMarsen = new ArrayList<>();
			int sequence = 1;
			int degree = 2;
			
			listMarsen.add(sequence);
			
			while (sequence<n){
				if(checkIsSimple(degree)){
					sequence = (int) Math.pow(2, degree++) - 1;
					if(sequence<n){
						listMarsen.add(sequence);						
					}
					continue;
				}
				while(!checkIsSimple(degree)){
					degree++;
				}
			}
			
			System.out.println("Sequence of Marcen :");
			for(Integer list:listMarsen){
				System.out.println(list);
			}
			
		}else{
			System.out.println("Number isn't natural!");
		}
		
		sc.close();
		
	}
	
	
	
	
	private static boolean checkIsSimple(int n){
		if(n==2 || n==3 || n==5 || n==7){
			
			return true;
		}
		for (int i = 2; i < 8; i++) {
			if(n%i==0){				
				return false;
			}
		}
		return true;
	}

}
