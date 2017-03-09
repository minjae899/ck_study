package module.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.ModelAndView;

public interface Controller {
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response)throws Exception;
}

