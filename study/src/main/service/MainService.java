package main.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.vo.MemberVO;

public interface MainService {

	boolean doCheck(String id);

	HashMap<String, ArrayList<MemberVO>> selectAllMember();

}
