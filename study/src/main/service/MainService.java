package main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import main.vo.MemberVO;

public interface MainService {

	
	String doLogin(String id, String pw, MemberVO rvo);
	
	String doLogin(String id, String pw, HttpServletRequest req);
	
	boolean doCheck(String id);

	List<HashMap<String, ArrayList<MemberVO>>> selectAllMember();

}
