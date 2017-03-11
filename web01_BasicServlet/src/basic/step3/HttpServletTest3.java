package basic.step3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpServletTest3 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req
	, HttpServletResponse res) throws ServletException, IOException {
		//코딩...
		res.setContentType("text/html");
		res.setCharacterEncoding("utf-8");
		
		PrintWriter out = res.getWriter();
		
		out.println("<body bgcolor='yellow'><h1>안녕하세요.</h1></body>");
		
		out.flush();
		out.close();
	}
	
}
