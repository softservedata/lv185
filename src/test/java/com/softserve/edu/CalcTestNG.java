package com.softserve.edu;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CalcTestNG {
    
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("CalcTestNG @BeforeMethod beforeMethod()");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("CalcTestNG @AfterMethod afterMethod()");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("CalcTestNG @BeforeClass beforeClass()");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("CalcTestNG @AfterClass afterClass()");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("CalcTestNG @BeforeTest beforeTest()");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("CalcTestNG @AfterTest afterTest()");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("CalcTestNG @BeforeSuite beforeSuite()");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("CalcTestNG @AfterSuite afterSuite()");
    }

    @Test
    public void add() {
        System.out.println("add()");
        //throw new RuntimeException("Test not implemented");
    }

    @Test
    public void div() {
        System.out.println("div()");
        //throw new RuntimeException("Test not implemented");
    }
}
