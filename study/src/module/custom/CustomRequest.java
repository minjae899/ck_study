package module.custom;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CustomRequest extends HttpServletRequestWrapper{

	public CustomRequest(HttpServletRequest request) {
		super(request);
		//url 셋팅
		String url = request.getServerName();
		int port = request.getServerPort();
		String contextPath = request.getServletContext().getContextPath();
		String QueryString = request.getQueryString();
		
	}

}
