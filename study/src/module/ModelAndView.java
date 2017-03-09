package module;

/*
 * 서버에서 이동할 뷰페이지 이름과
 * 이동방식의 정보를 담고 있는 클래스 
 */
public class ModelAndView {
	
	private String path;
	private boolean isRedirect;
	
	public ModelAndView(String path, boolean isRedirect) {		
		this.path = path;
		this.isRedirect = isRedirect;
	}
	
	public ModelAndView(){}
	
	public ModelAndView(String path) {		
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}	
}