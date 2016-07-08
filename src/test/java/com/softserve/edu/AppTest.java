package com.softserve.edu;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppTest {
	
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("+++AppTest @BeforeMethod beforeMethod()");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("AppTest @AfterMethod afterMethod()");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("+++AppTest @BeforeClass beforeClass()");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("+++AppTest @AfterClass afterClass()");
    }

	@Test
	public void testApp() {
		System.out.println("+++AppTest surefire.reports.directory = "
				+ System.getProperty("surefire.reports.directory"));
		Assert.assertTrue(true);
	}

}
