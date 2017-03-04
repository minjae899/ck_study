package basic.step1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 서블릿을 만드려면 servlet 클래스를 상속 받아야한다.
 * javax.servlet.GenericServlet 은 추상 클래스로 
 * service() 메서드를 반드시 구현해야한다. 
 * 
 * 
 * 1.요청이 was(Web Application Server, servlet container)로 오게 되면 
 * HttpRequest를 Container에게 보낸다.
 * 2.Servlet Container는 HttpServletReqeust, HttpServletResponse
 * 두객체를 생성한다.
 * 3.요청한 URL을 분석하여 web.xml(DD파일, 배포서술자, Deplyment Descriptor)
 * 를 참조해서 서블릿을 찾는다.
 * 4.Service() 메서드를 호출한다. (생성한, req,res를 참조)
 * 5.doGet() 또는 doPost() 메서드를 호출. (생성한, req,res를 참조)
 * 6.동적인 페이지를 생성후 HttpServletResponse 객체에 응답을 보낸다.
 * 7.응답이 완료되면 HttpServletReqeust, HttpServletResponse 객체를 소멸
 * 시킨다.
 * 
 *  GenericServlet
 *  service()
 *  
 *  HttpServlet
 *  doGet(), doPost()
 */
public class GenericTest1 extends GenericServlet{

	@Override
	public void service
	( ServletRequest req
	, ServletResponse res
	) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		out.println("<html><body><h2>hi ohs</h2></body></html>");
		out.flush();
		out.close();
	}
	
}
