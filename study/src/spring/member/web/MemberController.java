package spring.member.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.common.module.paging.PaginationInfo;
import spring.common.web.CommonController;
import spring.member.vo.MemberVO;

@Controller
public class MemberController extends CommonController{

	@RequestMapping(value="/main.do")
	public String main(){
		log("welcom to study home.");
		return "main/main";
	}
	
	@RequestMapping(value="/login.do")
	public String login
	( HttpServletRequest req
	, HttpServletResponse res
	, @RequestParam Map<String, Object> params
	, @ModelAttribute("memberVO") MemberVO memberVO
	, ModelMap model) throws Exception{
		
		log("\t id \t: " + memberVO.getId());
		log("\t pw \t: " + memberVO.getPw());
		
		String result = memberService.login(req, memberVO);
		if(result.equals("NotFoundId")){
			model.addAttribute("message", "아이디를 확인해 주세요.");
			log(memberVO.getId() + "님 아이디를 확인해 주세요.");
		}else if(result.equals("NotFoundPw")){
			model.addAttribute("message", "비밀번호를 확인해 주세요.");
			log(memberVO.getId() + "님 비밀번호를 확인해 주세요.");
		}else if(result.equals("NotAllowedIp")){
			model.addAttribute("message", "허용된 아이피가 아닙니다.");
			log(memberVO.getId() + "님 허용된 아이피가 아닙니다.");
		}else{
			model.addAttribute("message", "출석체크 완료!");
			log(memberVO.getId() + "님 출석체크 완료!");
			memberService.checkAttend(memberVO.getId());
		}
		
		return "member/loginConfirm";
	}
	
	
	@RequestMapping(value="/showAttendAllMember.do")
	public String showAttendAllMember
	( HttpServletRequest req
	, HttpServletResponse res
	, @RequestParam Map<String, Object> params
	, @ModelAttribute("memberVO") MemberVO memberVO
	, ModelMap model) throws Exception{
		
		
		log("list 호출..");
		
		String sessionId = "";
		if(null != req.getSession().getAttribute("loginVO")){
			sessionId = ((MemberVO)req.getSession().getAttribute("loginVO")).getId();
		}
		
		List<HashMap<String, ArrayList<MemberVO>>> list = memberService.selectAllMember();
		
		String myPenalty = "";
		if(null!=sessionId){
			myPenalty = util.sumMyPenalty(list, sessionId);
		}
		
		model.addAttribute("resultList", list);
		model.addAttribute("myPenalty", myPenalty);
		
		
		return "member/attendList";
	}
	
	
	@RequestMapping(value="/memberList.do")
	public String memberList
	( HttpServletRequest req
	, HttpServletResponse res
	, @RequestParam Map<String, Object> params
	, @ModelAttribute("memberVO") MemberVO memberVO) throws Exception{
		
		PaginationInfo paginationInfo = new PaginationInfo();
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		//int totalCnt = memberService.selectMemberCnt(); 
		
		//Pagination.setPagination(paginationInfo, params, totalCnt, recordCountPerPage, pageSize);
		
		
		return "";
	};
	
}
