package com.softserve.xsrteam;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.thoughtworks.xstream.XStream;

public class Writer {
	public static void main(String[] args) {
		Employ em = new Employ();
		//
		em.setName("Jack");
		em.setDesignation("Manager");
		em.setDepartment("Finance");
		//
		XStream xs = new XStream();
		xs.alias("employ", Employ.class);
		// Write to a file in the file system
		try {
			FileOutputStream fs = new FileOutputStream("employeedata.xml");
			xs.toXML(em, fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
