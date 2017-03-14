package test;

public class JavaTestApp {
	public static void main(String[] args) {
		Custom tb = new Custom();
		
		tb.service();
	}
}


class GenericServlet{
	
	void service(){
		System.out.println("하하");
	}
	
}

class HttpServlet extends GenericServlet{
	
	@Override
	void service(){
		doGet();
		doPost();
	}
	
	void doGet(){
		
	}
	
	void doPost(){
		
	}
	
}

class Custom extends HttpServlet{
	
	@Override
	void service() {
		super.service();
		System.out.println("호호");
	}
	
}
