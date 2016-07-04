package com.softserve.edu;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class DoloadServlet
 */
@WebServlet({ "/do", "/do.html" })
public class DoloadServlet implements Servlet {
	ServletConfig config;

	/**
	 * Default constructor.
	 */
	public DoloadServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		return config;
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return "DoloadServlet";
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		String head = "HTTP/1.0 200 OK\n"
				+ "Server: DoloadServlet\n"
				+ "Content-Type: text/html; charset=windows-1251\n"
				+ "Connection: Keep-Alive\n"
				+ "Content-Encoding: multipart/mixed\n"
				+ "Transfer-Encoding: chunked" + "Pragma: no-cache\n\n";
		//
		ServletOutputStream os = response.getOutputStream();
		os.print(head);
		response.flushBuffer();
		for (int i = 0; i < 20; i++) {
			os.print("i= " + i + " <br>\n");
			response.flushBuffer();
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
		os.close();
	}
}
