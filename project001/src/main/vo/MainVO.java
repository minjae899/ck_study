package main.vo;

public class MainVO {
	private String user_id;
	private String user_pass;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	
	@Override
	public String toString() {
		return "MainVO [user_id=" + user_id + ", user_pass=" + user_pass + "]";
	}
}
