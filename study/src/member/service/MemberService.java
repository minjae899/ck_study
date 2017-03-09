package member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import member.vo.MemberVO;

public interface MemberService {
	String login(HttpServletRequest request, MemberVO pvo) throws Exception;
	boolean checkAttend(String id) throws Exception;
	List<HashMap<String, ArrayList<MemberVO>>> selectAllMember() throws Exception;
}
