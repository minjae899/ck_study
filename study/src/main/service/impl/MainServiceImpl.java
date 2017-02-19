package main.service.impl;

import main.dao.MainDAO;
import main.service.MainService;

public class MainServiceImpl implements MainService{

	private MainDAO dao = new MainDAO();
	
	@Override
	public boolean doCheck() {
		
		
		return false;
	}

}
