package com.softserve.edu;

import java.util.ArrayList;

public class Problem3 {

	public static void main(String[] args) {
		
		System.out.println("2 digits Armstrong numbers");
		for(int i = 10; i<=99; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int n = i;
			while (n >= 10) {
				int k = n % 10;
				list.add(k);
				n = (n - n%10)/10;
			}
			list.add(n);
			if (Math.pow(list.get(0), 2) + Math.pow(list.get(1), 2) == i) System.out.println(i);
			list.clear();
		}
		
		System.out.println("3 digits Armstrong numbers");
		for(int i = 100; i<=999; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int n = i;
			while (n >= 10) {
				int k = n % 10;
				list.add(k);
				n = (n - n%10)/10;
			}
			list.add(n);
			if (Math.pow(list.get(0), 3) + Math.pow(list.get(1), 3) + Math.pow(list.get(2), 3) == i) System.out.println(i);
			list.clear();
		}
		
		System.out.println("3 digits Armstrong numbers");
		for(int i = 1000; i<=9999; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int n = i;
			while (n >= 10) {
				int k = n % 10;
				list.add(k);
				n = (n - n%10)/10;
			}
			list.add(n);
			if (Math.pow(list.get(0), 4) + Math.pow(list.get(1), 4) + Math.pow(list.get(2), 4) + Math.pow(list.get(3), 4)== i) System.out.println(i);
			list.clear();
		}
	}

}
