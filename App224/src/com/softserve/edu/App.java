package com.softserve.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter please natural number: ");
		
		if (sc.hasNextInt()){
			
			int n = sc.nextInt();
			List<Integer> listDivider = new ArrayList<>();
			for (int i = 1; i <=n; i++) {
				if(n%i==0){	
					listDivider.add(i);
				}
			}			
			
			System.out.println("Sequence of Dividers :");
			for(Integer list:listDivider){
				System.out.println(list);
			}
			
		}else{
			System.out.println("Number isn't natural!");
		}
	}

}
