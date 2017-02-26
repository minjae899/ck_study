package main.service.impl;

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
	
	public static void main(String[] args) {
		MainService service = new MainServiceImpl();
		
		service.doCheck("chunkind");
	}

	@Override
	public List<MemberVO> selectAllAttendList() {
		//jdbcDao.
		return null;
	}

}
