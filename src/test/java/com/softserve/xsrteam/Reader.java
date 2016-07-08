package com.softserve.xsrteam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Reader {
	public static void main(String[] args) {
		XStream xs = new XStream(new DomDriver());
		xs.alias("employ", Employ.class);
		//
		Employ em = new Employ();
		try {
			FileInputStream fis = new FileInputStream("employeedata.xml");
			xs.fromXML(fis, em);
			//
			System.out.println(em.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
