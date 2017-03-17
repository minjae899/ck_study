package servlet.query.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet
	( HttpServletRequest req
	, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req, resp);
	}

	@Override
	protected void doPost
	( HttpServletRequest req
	, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher view = req.getRequestDispatcher("WEB-INF/jsp/servlet/query/queryModal.jsp");
		view.forward(req, resp);
		
		System.out.println("call in QueryController");
		
	}
	
	
}
