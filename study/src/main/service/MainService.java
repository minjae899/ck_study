package main.service;

import java.util.List;

import main.vo.MemberVO;

public interface MainService {

	boolean doCheck(String id);

	List<MemberVO> selectAllAttendList();

}
