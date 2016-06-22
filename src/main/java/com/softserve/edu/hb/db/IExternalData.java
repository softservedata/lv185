package com.softserve.edu.hb.db;

import java.util.List;

public interface IExternalData {

	List<List<String>> getAllCells(String absoluteFilePath);

}