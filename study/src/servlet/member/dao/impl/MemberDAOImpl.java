package servlet.member.dao.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import servlet.member.dao.MemberDAO;
import servlet.member.vo.AttendVO;
import servlet.member.vo.MemberVO;

/**
 * @quickCode ##
* @project  study
* @path member.dao.impl.MemberDAOImpl.java
* @auth CK
* @date 2017. 3. 9. 오후 10:36:09
* @other 
* TODO CK
 */
@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Resource
	private SqlSession sqlSession;

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:36:06
	* @other 
	* @param id
	* @return
	* TODO CK
	 */
	@Override
	public boolean selectId(String id) {
		
		boolean result = false;
		
		int cnt = sqlSession.selectOne("member.selectId", id);
		
		if(cnt>0) result = true;
		
		return result;
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:36:03
	* @other 
	* @param id
	* @return
	* TODO CK
	 */
	@Override
	public AttendVO selectAttend(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:36:00
	* @other 
	* @param id
	* TODO CK
	 */
	@Override
	public void insertAttend(String id) {
		// TODO Auto-generated method stub
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:35:58
	* @other 
	* @param pvo
	* @return
	* TODO CK
	 */
	@Override
	public MemberVO selectLogin(MemberVO pvo) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:35:55
	* @other 
	* @return
	* TODO CK
	 */
	@Override
	public int selectMemberCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:35:52
	* @other 
	* @param gijunDate
	* @return
	* TODO CK
	 */
	@Override
	public List<MemberVO> selectAttendCheckAllMember(Set<String> gijunDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 3. 9. 오후 10:35:42
	* @other 
	* @param args
	* TODO CK
	* 테스트를 위한 메인
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"orm/mybatis/SqlMapConfig.xml"});
		
		MemberDAO dao = (MemberDAO) context.getBean("memberDAOImpl");
		
		System.out.println(dao.selectId("kjs"));
		
	}

}
