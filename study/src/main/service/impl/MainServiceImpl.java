package main.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.dao.JDBCMainDAO;
import main.service.MainService;
import main.vo.AttendVO;
import main.vo.MemberVO;

public class MainServiceImpl implements MainService{

	private JDBCMainDAO jdbcDao = new JDBCMainDAO();
	
	@Override
	public boolean doCheck(String id) {
		boolean result = false;
		AttendVO avo = new AttendVO();
		avo = jdbcDao.selectAttend(id);
		if(avo == null){
			jdbcDao.insertCheck(id);
			result = true;
		}
		return result;
	}
	
	@Override
	public HashMap<String, ArrayList<MemberVO>> selectAllMember() {
		
		HashMap<String, ArrayList<MemberVO>> result = new HashMap<String, ArrayList<MemberVO>>();
		
		ArrayList<String> dataList = jdbcDao.selectCurrentDate();
		
		for(int i=0; i<dataList.size(); i++){
			ArrayList<MemberVO> memberList = (ArrayList<MemberVO>) jdbcDao.selectMemberByDate(dataList.get(i));
			if(memberList.size() > 0) result.put(dataList.get(i), memberList);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		MainService service = new MainServiceImpl();
		
		service.doCheck("chunkind");

	}
}
