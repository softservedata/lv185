package com.softserve.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Hello
 */
//@WebServlet({ "/hello", "/hahaha" })
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//
	private String box = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		if ((request.getParameter("box") != null) 
				&& (!request.getParameter("box").isEmpty())) {
			box = request.getParameter("box");
		} 
		if ((request.getParameter("do") != null) && (!request.getParameter("do").isEmpty())) {
			box = request.getParameter("do");
			RequestDispatcher rd = request.getServletContext().getNamedDispatcher("com.softserve.edu.DoloadServlet");
			System.out.println("rd= " + rd);
			rd.forward(request, response);
		}
		if ((request.getParameter("js") != null)
				&& (!request.getParameter("js").isEmpty())) {
			box = request.getParameter("js");
			RequestDispatcher rd = request.getServletContext().getNamedDispatcher("com.softserve.edu.GetJson");
			System.out.println("JSON rd= " + rd);
			rd.forward(request, response);
		}
		if ((request.getParameter("r") != null)
				&& (!request.getParameter("r").isEmpty())) {
			box = request.getParameter("r");
			RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/work?aa=123&bb=456");
			System.out.println("Redirect r= " + rd);
			rd.forward(request, response);
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		int i = 1;
//		if (2 / i == 2) {
//			throw new RuntimeException("HA-HA-HA");
//		}
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Hello</h1><br><br><br>");
		out.println("<br><font size=\"5\" color='red'> URL = " + request.getRequestURL().toString() + "</font><br>");
		out.println("<br><font size=\"5\" color='green'> URI = " + request.getRequestURI().toString() + "</font><br>");
		if (box.length() > 0) {
			out.println("<br><br>box=" + box + "<br><br><br>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();
		// response.flushBuffer();
		//DataSource d= new DataSource(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
