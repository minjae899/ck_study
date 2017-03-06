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

import cmmn.util.Util;
import main.service.MainService;
import main.service.impl.MainServiceImpl;
import main.vo.MemberVO;

/**
 * @quickCode ##
* @project  study
* @path main.web.MainController.java
* @auth CK
* @date 2017. 2. 27. 오후 4:49:33
* @other 
* TODO CK
 */
public class MainController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:30
	* @other 
	* @param req
	* @param resp
	* @throws ServletException
	* @throws IOException
	* TODO CK
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:26
	* @other 
	* @param req
	* @param resp
	* @throws ServletException
	* @throws IOException
	* TODO CK
	 */
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
			System.out.println(req.getRemoteAddr() + " 메인 호출.");
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/main/main.jsp");
			view.forward(req, resp);
		}
		
		else if(command.equals("check")){
			System.out.println("입력한 값 확인 id : " + id +", pw : " + pw);
			/*
			 * 이슈.
			 * 전달한 rvo는 doLogin메서드에서 새로운 값으로 바뀐다.
			 * 그런데 같은 변수인 rvo가 리턴후엔 원래 값으로 돌아온다...
			 * 내가 알기론 이러면 안되는데 말야...
			 */
//			MemberVO rvo = new MemberVO();
//			rvo.setId("test");
//			String result = service.doLogin(id, pw, rvo);
			
			String result = service.doLogin(id, pw, req);
			
			if(result.equals("NotFoundId")){
				req.setAttribute("message", "아이디를 확인해 주세요.");
				System.out.println(id + "님 아이디를 확인해 주세요.");
			}else if(result.equals("NotFoundPw")){
				req.setAttribute("message", "비밀번호를 확인해 주세요.");
				System.out.println(id + "님 비밀번호를 확인해 주세요.");
			}else if(result.equals("NotAllowedIp")){
				req.setAttribute("message", "허용된 아이피가 아닙니다.");
				System.out.println(id + "님 허용된 아이피가 아닙니다.");
			}else{
				req.setAttribute("message", "출석체크 완료!");
				System.out.println(id + "님 출석체크 완료!");
				/*req.getSession().setAttribute("loginVO", rvo);
				String sessionId = ((MemberVO)req.getSession().getAttribute("loginVO")).getId();
				System.out.println(sessionId);*/
				service.doCheck(id);
			}
			
			
			//서블릿 버전
			//viewCheck(req, resp);
			//jsp 버전
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/main/viewCheck.jsp");
			view.forward(req, resp);
		}
		
		else if(command.equals("listAjax")){
			System.out.println("list 호출..");
			
			String sessionId = "";
			if(null != req.getSession().getAttribute("loginVO")){
				sessionId = ((MemberVO)req.getSession().getAttribute("loginVO")).getId();
			}
			
			List<HashMap<String, ArrayList<MemberVO>>> list = service.selectAllMember();
			
			String myPenalty = "";
			if(null!=sessionId){
				myPenalty = Util.sumMyPenalty(list, sessionId);
			}
			
			req.setAttribute("resultList", list);
			req.setAttribute("myPenalty", myPenalty);
			
			RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/main/listFragment.jsp");
			view.forward(req, resp);
		}
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:49:18
	* @other 
	* @param req
	* @param resp
	* @throws IOException
	* TODO CK
	 */
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




















