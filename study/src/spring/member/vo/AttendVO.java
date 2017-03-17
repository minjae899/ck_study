package spring.member.vo;

public class AttendVO {
	private String id;
	private String checkDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	@Override
	public String toString() {
		return "AttendVO [id=" + id + ", checkDate=" + checkDate + "]";
	}
}
