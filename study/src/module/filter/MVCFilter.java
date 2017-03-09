package module.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import module.custom.CustomRequest;
import module.custom.CustomResponse;

public class MVCFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		CustomRequest creq = new CustomRequest((HttpServletRequest) request);	
		CustomResponse cres = new CustomResponse((HttpServletResponse) response);	
		
		creq.setCharacterEncoding("utf-8");
		cres.setContentType("text/html;charset=utf-8");
				
		chain.doFilter(creq, cres);		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
