package module;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.interfaces.Controller;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로직은 여기다 작성...
		String command = request.getParameter("command");
		Controller controller = HandlerMapping.getInstance().createController(command);
		
		String path = "error.jsp";
		ModelAndView mv=  null;
		boolean isRedirect = false;
		
		try{
			mv = controller.execute(request, response);
			path = mv.getPath();
			isRedirect = mv.isRedirect();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(isRedirect){
			response.sendRedirect(path);
		}else{
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
	
}