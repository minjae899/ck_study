package main.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.util.JDBCUtil;
import main.vo.MainVO;


/**
 * 서블릿을 사용하는 방법
 * 
 * 1.HttpServlet을 상속 받는다.
 * 2.doGet, doPost를 오버라이딩 한다.
 * 
 */
public class MainController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req
					, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//1. 화면에서입력한 아이디와 비밀번호르 받아온다.
		String userId = req.getParameter("user_id");
		String userPw = req.getParameter("user_pass");
		
		//2.화면에서 입력한 아이디를 디비에서 셀렉트해서 디비에 일치하는 아이디와 비밀번호를 가져온다.
		//1) 일치하지않는 아이디가 없으면 아이디가 없습니다.
		JDBCUtil util = new JDBCUtil();
		try {
			
			
			MainVO rvo = util.login(userId);
			
			//한글설정
			resp.setContentType("text/html");
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
			
			PrintWriter out = resp.getWriter();
			
			//3.가져온 비밀번호와 입력한 비밀번호를 비교한다.
			//1) 입력한 비밀번호와 디비에 있는 비밀번호가 다르면 비밀번호가 다릅니다.
			if(null == rvo.getUser_id()){
				System.out.println("아이디가 없습니다.");
				out.println("<h1>아이디가 없습니다.</h1>");
			}else if(!userPw.equals(rvo.getUser_pass())){
				System.out.println("패스워드를 확인해주세요.");
				out.println("<h1>패스워드를 확인해주세요.</h1>");
			}else{
				System.out.println("환영합니다~~~~");
				out.println("<h1>환영합니다~~~~</h1>");
			}
			
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}


		