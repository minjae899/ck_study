package member.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cmmn.util.Util;
import member.service.MemberService;
import member.service.impl.MemberServiceImpl;
import member.vo.MemberVO;
import module.ModelAndView;
import module.interfaces.Controller;

public class MemberController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String resultPg = request.getParameter("resultPg");
		
		String id = null;
		String pw = null;
		
		MemberService service = new MemberServiceImpl();
		
		MemberVO pvo = new MemberVO();
		pvo.setId((String) Util.nvl(request.getParameter("id")));
		pvo.setPw((String) Util.nvl(request.getParameter("pw")));
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println("입력한 값 확인 id : " + pvo.getId() +", pw : " + pvo.getPw());
		
		if(resultPg.equals("login")){
			String result = service.login(request, pvo);
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
				service.checkAttend(id);
			}
			
			mv.setRedirect(false);
			mv.setPath("WEB-INF/jsp/member/loginConfirm.jsp");
			
			
		}else if(resultPg.equals("listAjax")){
			System.out.println("list 호출..");
			
			String sessionId = "";
			if(null != request.getSession().getAttribute("loginVO")){
				sessionId = ((MemberVO)request.getSession().getAttribute("loginVO")).getId();
			}
			
			List<HashMap<String, ArrayList<MemberVO>>> list = service.selectAllMember();
			
			String myPenalty = "";
			if(null!=sessionId){
				myPenalty = Util.sumMyPenalty(list, sessionId);
			}
			
			request.setAttribute("resultList", list);
			request.setAttribute("myPenalty", myPenalty);
			
			mv.setRedirect(false);
			mv.setPath("WEB-INF/jsp/member/attendList.jsp");
		}

		return mv;
	}

}
