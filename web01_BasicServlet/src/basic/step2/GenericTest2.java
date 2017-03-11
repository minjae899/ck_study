package basic.step2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GenericTest2 extends GenericServlet{

	@Override
	public void service(ServletRequest req
	, ServletResponse res) throws ServletException, IOException {
		//코딩...
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		
		PrintWriter out = res.getWriter();
		
		out.println("<body bgcolor='yellow'><h1>안녕하세요.</h1></body>");
		
		out.flush();
		out.close();
		
	}
	
}
