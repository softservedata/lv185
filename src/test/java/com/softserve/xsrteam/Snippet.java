package com.softserve.xsrteam;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Snippet {

	public static void main(String[] args) {
		Snippet snippet = new Snippet();
		String xml = snippet.xml2String();
		Blog blog = snippet.String2xml(xml);
		System.out.println("Autor name = " + blog.getWriter().getName());
		for (Entry entry : blog.getContent()) {
			System.out.println("Entry Title = " + entry.getTitle());
			System.out.println("Entry Description = " + entry.getDescription());
		}
	}

	public String xml2String() {
		Blog teamBlog = new Blog(new Author("Guilherme Silveira"));
		teamBlog.add(new Entry("first", "My first blog entry."));
		teamBlog.add(new Entry("tutorial", "Today we have developed a nice alias tutorial. Tell your friends! NOW!"));
		//
		XStream xstream = new XStream();
		xstream.alias("blog", Blog.class);
		xstream.alias("entry", Entry.class);
		xstream.useAttributeFor(Blog.class, "writer");
		xstream.aliasField("author", Blog.class, "writer");
		xstream.addImplicitCollection(Blog.class, "entries");
		xstream.registerConverter(new AuthorConverter());
		//
		String result = xstream.toXML(teamBlog);
		System.out.println(result);
		return result;
	}

	public Blog String2xml(String xml) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("blog", Blog.class);
		xstream.alias("entry", Entry.class);
		xstream.useAttributeFor(Blog.class, "writer");
		xstream.aliasField("author", Blog.class, "writer");
		xstream.addImplicitCollection(Blog.class, "entries");
		xstream.registerConverter(new AuthorConverter());
		//
		Blog blog = (Blog) xstream.fromXML(xml);
		return blog;
	}

}
