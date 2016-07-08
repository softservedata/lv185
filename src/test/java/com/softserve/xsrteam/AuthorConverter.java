package com.softserve.xsrteam;

import com.thoughtworks.xstream.converters.SingleValueConverter;

class AuthorConverter implements SingleValueConverter {
	
	public String toString(Object obj) {
		return ((Author) obj).getName();
	}

	public Object fromString(String name) {
		return new Author(name);
	}

	@SuppressWarnings("rawtypes")
	//@SuppressWarnings({"unchecked", "deprecation"})
	public boolean canConvert(Class type) {
		return type.equals(Author.class);
	}
	
}
