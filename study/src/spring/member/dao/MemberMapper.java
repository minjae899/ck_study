package spring.member.dao;

import java.util.List;
import java.util.Set;

import spring.member.vo.AttendVO;
import spring.member.vo.MemberVO;
import spring.common.anno.Mapper;

@Mapper("memberMapper")
public interface MemberMapper {
	
	int selectId(String id) throws Exception;
	
	AttendVO selectAttend(String id) throws Exception;
	
	void insertAttend(String id) throws Exception;

	MemberVO selectLogin(MemberVO pvo) throws Exception;
	
	int selectMemberCnt() throws Exception;
	
	List<MemberVO> selectAttendCheckAllMember() throws Exception;
	
	Set<String> selectGijunDate() throws Exception;
	
}
