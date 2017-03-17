package spring.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import spring.member.vo.MemberVO;

public interface MemberService {
	
	//오늘 출석 체크 여부
	boolean checkAttend(String id) throws Exception;
	
	//모든 회원 가져오기
	List<HashMap<String, ArrayList<MemberVO>>> selectAllMember() throws Exception;
	
	//로그인
	String login(HttpServletRequest req, MemberVO pvo) throws Exception;
	
}
