package main.service.impl;

import main.dao.MainDAO;
import main.service.MainService;
import main.vo.AttendVO;

public class MainServiceImpl implements MainService{

	private MainDAO dao = new MainDAO();
	
	@Override
	public boolean doCheck(String id) {
		boolean result = false;
		AttendVO avo = new AttendVO();
		avo = dao.selectAttend(id);
		if(avo == null){
			dao.insertCheck("chunkind");
			result = true;
		}
		return result;
	}
	
	public static void main(String[] args) {
		MainService service = new MainServiceImpl();
		
		service.doCheck("chunkind");
	}

}
