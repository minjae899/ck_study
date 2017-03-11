package web03_HttpHeader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrintHeaderTest extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//한글처리
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		//화면 출력
		PrintWriter out = resp.getWriter();
		
		Enumeration<String> en = req.getHeaderNames();
		while(en.hasMoreElements()){
			String key = en.nextElement();
			String value = req.getHeader(key);
			System.out.println("key : " + key + ", value : " 
					+ value );
		}
		
		
		
	}
	
}
