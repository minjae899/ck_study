package main.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.MainService;
import main.service.impl.MainServiceImpl;
import main.vo.MemberVO;

public class MainController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String command = null;
		String id = null;
		String pw = null;
		
		MainService service = new MainServiceImpl();
		
		command = req.getParameter("command");
		id = req.getParameter("user_id");
		pw = req.getParameter("user_pw");
		
		if(command.equals("main")){
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/main/main.jsp");
			view.forward(req, resp);
		}
		
		else if(command.equals("check")){
			boolean result = service.doCheck(id);
			
			//서블릿 버전
			//viewCheck(req, resp);
			//jsp 버전
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/main/viewCheck.jsp");
			view.forward(req, resp);
		}
		
		/*else if(command.equals("list")){
			List<MemberVO> attendList = service.selectAllAttendList();
		}*/
		
		else if(command.equals("listAjax")){
			HashMap<String, ArrayList<MemberVO>> attendList = service.selectAllMember();
			
			req.setAttribute("list", attendList);
			
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/main/listFragment.jsp");
			view.forward(req, resp);
		}
	}
	
	public void viewCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		
		PrintWriter out = resp.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("	<meta charset=\"utf-8\">");
		out.println("</head>");
		out.println("<body>");
		out.println("	<h1>출석체크 완료!!</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}
	
}
