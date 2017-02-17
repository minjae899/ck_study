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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		super.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//필요한 변수 선언.
		MainVO pvo = new MainVO();
		JDBCUtil jdbc = new JDBCUtil();
		MainVO rvo = null;
		
		//html에서 아이디 가져오기
		String pId = request.getParameter("user_id");
		String pPw = request.getParameter("user_pass");
		
		//디비에서 데이터 가져오기
		try {
			rvo = jdbc.getMember(pId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//문서설정
		response.setContentType("text/html;");
		//한글설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//화면에 표출해주기.
		PrintWriter out = response.getWriter();
		
		if(rvo == null){
			//로그인이 안된 상태.
			out.print("로그인 실패!!");
		}else{
			//로그인이 된 상태.
			out.print("로그인 성공!!");
		}
		out.flush();
		out.close();
	}
	
}
