package com.softserve.edu.head;

public class Second {

	private IFirst first;

	public Second(IFirst first) {
		this.first = first;
	}
	
	public double process(double arg0) {
		return first.calc(arg0)+1;
	}
	
}
