package member.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cmmn.util.Util;
import member.dao.MemberDAO;
import member.vo.AttendVO;
import member.vo.MemberVO;
import orm.jdbc.JDBCUtil;

public class MemberJDBCDAOImpl implements MemberDAO{

	private JDBCUtil jdbc = new JDBCUtil();
	private AttendVO avo = null;
	private StringBuilder query = null;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private int resultRow = 0;
	
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:16
	* @other 
	* @param id
	* @return
	* TODO CK
	 */
	@Override
	public boolean selectId(String id){
		boolean result = false;
		Connection conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("SELECT COUNT(1) FROM MEMBER WHERE ID = ?");
		try{
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()){
				if(Integer.parseInt(rs.getString(1))>0){
					result = true;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:27
	* @other 
	* @param id
	* @return
	* TODO CK
	* 오늘 출석했는지 여부를 체크한다.
	 */
	@Override
	public AttendVO selectAttend(String id){
		Connection conn = jdbc.getConnection();
		query = new StringBuilder(); 
		query.append("SELECT ID");
		query.append("	   , TO_CHAR(CHECK_DATE,'YYYY-MM-DD') ");
		query.append("	FROM ATTEND ");
		query.append(" WHERE ID = ? ");
		query.append("	 AND TO_CHAR(CHECK_DATE, 'YYYY-MM-DD') = TO_CHAR(SYSDATE, 'YYYY-MM-DD')");
		try {
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()){
				avo = new AttendVO();
				avo.setId(rs.getString(1));
				avo.setCheckDate(rs.getString(2));
				System.out.println("result avo : " + avo.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbc.closeAll(conn, ps, rs);
		}
		
		return avo;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:08
	* @other 
	* @param id
	* TODO CK
	 */
	@Override
	public void insertAttend(String id){
		conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("INSERT INTO ATTEND(ID, CHECK_DATE) VALUES('" + id + "', SYSDATE)");
		try {
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			resultRow = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbc.closeAll(conn, ps);
		}
		
	}
	
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:19
	* @other 
	* @param id
	* @param pw
	* @return
	* TODO CK
	 */
	@Override
	public MemberVO selectLogin(MemberVO pvo){
		MemberVO rvo = new MemberVO();
		
		Connection conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append(" SELECT SEQ");
		query.append(" 	    , NAME");
		query.append(" 	    , ID");
		query.append(" 	    , PW");
		query.append(" 	    , AGE");
		query.append(" 	    , GENDER");
		query.append(" 	    , TELL");
		query.append(" 	    , REGISTER_DATE");
		query.append(" 	    , UPDATE_DATE");
		query.append("   FROM MEMBER");
		query.append("  WHERE ID = ?");
		query.append("    AND PW = ?");
		try{
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, pvo.getId());
			ps.setString(2, pvo.getPw());
			rs = ps.executeQuery();
			if(rs.next()){
				rvo.setSeq(Integer.parseInt(rs.getString(1)));
				rvo.setName(rs.getString(2));
				rvo.setId(rs.getString(3));
				rvo.setPw(rs.getString(4));
				rvo.setAge(Integer.parseInt(rs.getString(5)));
				rvo.setGender(rs.getString(6));
				rvo.setTell(rs.getString(7));
				rvo.setRegisterDate(rs.getString(8));
				rvo.setUpdateDate(rs.getString(9));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rvo;
	}
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:24
	* @other 
	* @return
	* TODO CK
	 */
	@Override
	public int selectMemberCnt(){
		int result = 0;
		Connection conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("SELECT COUNT(1) FROM MEMBER");
		try {
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			rs = ps.executeQuery();
			if(rs.next()){
				result = Integer.parseInt(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:34
	* @other 
	* @param gijunDate
	* @return
	* TODO CK
	 */
	@Override
	public List<MemberVO> selectAttendCheckAllMember(Set<String> gijunDate){
		List<MemberVO> list = null;
		MemberVO vo = null;
		Connection conn = jdbc.getConnection();
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT A.ID");
		query.append("	   , A.NAME");
		query.append("	   , A.TELL");
		query.append("	   , C.CHECK_DATE AS GIJUN_DATE");
		query.append("	   , (");
		query.append("	 		SELECT MIN(TO_CHAR(B.CHECK_DATE, 'YYYY-MM-DD HH24:MI'))");
		query.append("	 		  FROM ATTEND B");
		query.append("	 		 WHERE A.ID = B.ID");
		query.append("	 		   AND TO_CHAR(B.CHECK_DATE, 'YYYY-MM-DD') = C.CHECK_DATE");
		query.append("	     ) AS ATTEND_DATE");
		query.append("  FROM MEMBER A");
		query.append("     , (");
		query.append("  		SELECT TO_CHAR(SUM.CHECK_DATE,'YYYY-MM-DD') AS CHECK_DATE");
		query.append("			  FROM (");
		query.append("						SELECT TO_DATE((SELECT TO_CHAR(SYSDATE,'YYYY') || '0101' FROM DUAL),'YYYYMMDD') + (ROWNUM-1) AS CHECK_DATE");
		query.append("							 , TO_CHAR(SYSDATE, 'YYYY-MM-DD') AS CURRENT_DATE");
		query.append("						  FROM DUAL CONNECT BY LEVEL <=365");
		query.append("				   ) SUM");
		query.append("			 WHERE TO_CHAR(CHECK_DATE, 'D') = 7");
		query.append("			   AND CHECK_DATE BETWEEN TO_DATE((SELECT TO_CHAR(SYSDATE - TO_CHAR(SYSDATE, 'DD') + 1 ,'YYYY-MM-DD') FROM DUAL),'YYYY-MM-DD') AND CURRENT_DATE");
		query.append("			 ORDER BY CHECK_DATE DESC");
		query.append("  	  ) C");
		query.append(" ORDER BY GIJUN_DATE DESC, A.REGISTER_DATE ASC");
		
		try{
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			rs = ps.executeQuery();
			
			list = new ArrayList<MemberVO>();
			while(rs.next()){
				vo = new MemberVO();
				vo.setId(rs.getString(1));
				vo.setName(rs.getString(2));
				vo.setTell(rs.getString(3));
				vo.setGijunDate(rs.getString(4));
				gijunDate.add(rs.getString(4));
				if(null != rs.getString(5)){
					vo.setCheckDate(rs.getString(5).substring(0, 10));
					vo.setCheckTime(rs.getString(5).substring(11, rs.getString(5).length()));
					vo.setPenalty(Util.sumPenalty(rs.getString(5)));
				}
				list.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbc.closeAll(conn, ps, rs);
		}
		
		return list;
	}
	
}
