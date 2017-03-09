package module.custom;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CustomResponse extends HttpServletResponseWrapper{

	public CustomResponse(HttpServletResponse response) {
		super(response);
	}

}
