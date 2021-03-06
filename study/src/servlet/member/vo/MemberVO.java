package servlet.member.vo;

import java.util.List;

/**
 * @quickCode ##
* @project  study
* @path member.vo.MemberVO.java
* @auth CK
* @date 2017. 2. 27. 오후 4:51:38
* @other 
* TODO CK
 */
public class MemberVO {
	
	private int seq;
	private String name;
	private String id;
	private String pw;
	private int age;
	private String gender;
	private String tell;
	private String registerDate;
	private String updateDate;
	private String gijunDate;
	private String checkDate;
	private String checkTime;
	private String penalty;
	private List<AttendVO> attendList;
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public List<AttendVO> getAttendList() {
		return attendList;
	}
	public void setAttendList(List<AttendVO> attendList) {
		this.attendList = attendList;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getGijunDate() {
		return gijunDate;
	}
	public void setGijunDate(String gijunDate) {
		this.gijunDate = gijunDate;
	}
	public String getPenalty() {
		return penalty;
	}
	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
	@Override
	public String toString() {
		return "MemberVO [seq=" + seq + ", name=" + name + ", id=" + id + ", pw=" + pw + ", age=" + age + ", gender="
				+ gender + ", tell=" + tell + ", registerDate=" + registerDate + ", updateDate=" + updateDate
				+ ", gijunDate=" + gijunDate + ", checkDate=" + checkDate + ", checkTime=" + checkTime + ", penalty="
				+ penalty + ", attendList=" + attendList + "]";
	}
}
