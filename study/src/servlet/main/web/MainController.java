package servlet.main.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.module.ModelAndView;
import servlet.module.interfaces.Controller;

/**
 * @quickCode ##
 * @project  study
 * @path main.web.MainController.java
 * @auth CK
 * @date 2017. 2. 27. 오후 4:49:33
 * @other
 */
public class MainController implements Controller{

	/**
	 * @quickCode ##
	 * @auth CK
	 * @date 2017. 2. 27. 오후 4:49:26
	 * @other 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(request.getRemoteAddr() + " 메인 호출.");
		return new ModelAndView("WEB-INF/jsp/servlet/main/main.jsp");
	}
	
}




















