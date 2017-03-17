package servlet.module;

import servlet.main.web.MainController;
import servlet.member.web.MemberController;
import servlet.module.interfaces.Controller;

//Controller를 생성하는 공장.
public class HandlerMapping {
	
	private static HandlerMapping handler = new HandlerMapping();
	
	private HandlerMapping(){}
	public static HandlerMapping getInstance(){
		return handler;
	}
	
	public Controller createController(String command){
		Controller controller = null;
		if(command.equals("main")){
			controller = new MainController();
		}else if(command.equals("member")){
			controller = new MemberController();
		}
		return controller;
	}
}