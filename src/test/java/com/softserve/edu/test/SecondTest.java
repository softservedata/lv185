package com.softserve.edu.test;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.softserve.edu.head.First;
import com.softserve.edu.head.IFirst;
import com.softserve.edu.head.Second;
import com.softserve.edu.test.stub.FirstStub1;

public class SecondTest {

	//@Test
	public void Check1() {
		IFirst first = new FirstStub1(); // Unit Test
		Second second = new Second(first);
		double expected = 5;
		double actual;
		actual = second.process(2);
		Assert.assertEquals(actual, expected);
	}

	//@Test
	public void Check2() {
		IFirst first =  Mockito.mock(IFirst.class);
		Mockito.when(first.calc(Mockito.anyDouble())).thenReturn(1.0);
		Mockito.when(first.calc(2.0)).thenReturn(4.0);
		//
		Second second = new Second(first);
		double expected = 5;
		double actual;
		actual = second.process(2);
		Assert.assertEquals(actual, expected);
		//
		System.out.println("first.calc(2.0) = " + first.calc(2.0));
		System.out.println("first.calc(3.0) = " + first.calc(3.0));
	}

	@Test
	public void Check3() {
		IFirst firstOrigin = new First();
		IFirst firstSpy =  Mockito.spy(firstOrigin);
		Mockito.when(firstSpy.calc(3.0)).thenReturn(33.0);
		//
		Second second = new Second(firstSpy);
		double expected = 5;
		double actual;
		actual = second.process(2);
		Assert.assertEquals(actual, expected);
		Mockito.verify(firstSpy).calc(2.0);
		//
		System.out.println("first.calc(2.0) = " + firstSpy.calc(2.0));
		System.out.println("first.calc(3.0) = " + firstSpy.calc(3.0));
	}

}
