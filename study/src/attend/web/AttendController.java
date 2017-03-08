package attend.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.service.MainService;
import main.service.impl.MainServiceImpl;
import module.ModelAndView;
import module.interfaces.Controller;

public class AttendController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = null;
		String pw = null;
		
		MainService service = new MainServiceImpl();
		
		id = request.getParameter("user_id");
		pw = request.getParameter("user_pw");
		
		System.out.println("입력한 값 확인 id : " + id +", pw : " + pw);
		
		String result = service.doLogin(id, pw, request);
		
		if(result.equals("NotFoundId")){
			request.setAttribute("message", "아이디를 확인해 주세요.");
			System.out.println(id + "님 아이디를 확인해 주세요.");
		}else if(result.equals("NotFoundPw")){
			request.setAttribute("message", "비밀번호를 확인해 주세요.");
			System.out.println(id + "님 비밀번호를 확인해 주세요.");
		}else if(result.equals("NotAllowedIp")){
			request.setAttribute("message", "허용된 아이피가 아닙니다.");
			System.out.println(id + "님 허용된 아이피가 아닙니다.");
		}else{
			request.setAttribute("message", "출석체크 완료!");
			System.out.println(id + "님 출석체크 완료!");
			/*req.getSession().setAttribute("loginVO", rvo);
			String sessionId = ((MemberVO)req.getSession().getAttribute("loginVO")).getId();
			System.out.println(sessionId);*/
			service.doCheck(id);
		}

		return new ModelAndView("WEB-INF/jsp/main/viewCheck.jsp", false);
	}

}
