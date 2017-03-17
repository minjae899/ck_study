package servlet.member.dao;

import java.util.List;
import java.util.Set;

import servlet.member.vo.AttendVO;
import servlet.member.vo.MemberVO;

public interface MemberDAO {
	boolean selectId(String id);
	AttendVO selectAttend(String id);
	void insertAttend(String id);
	MemberVO selectLogin(MemberVO pvo);
	int selectMemberCnt();
	List<MemberVO> selectAttendCheckAllMember(Set<String> gijunDate);
}
