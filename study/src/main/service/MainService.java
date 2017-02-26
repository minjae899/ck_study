package main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import main.vo.MemberVO;

public interface MainService {

	boolean doCheck(String id);

	List<HashMap<String, ArrayList<MemberVO>>> selectAllMember();

}
