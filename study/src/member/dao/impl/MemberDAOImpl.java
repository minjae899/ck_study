package member.dao.impl;

import java.util.List;
import java.util.Set;

import member.dao.MemberDAO;
import member.vo.AttendVO;
import member.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO{

	@Override
	public boolean selectId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AttendVO selectAttend(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertAttend(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public MemberVO selectLogin(MemberVO pvo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectMemberCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectAttendCheckAllMember(Set<String> gijunDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
