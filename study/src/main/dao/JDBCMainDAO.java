package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jdbc.util.JDBCUtil;
import main.vo.AttendVO;
import main.vo.MemberVO;

public class JDBCMainDAO {

	private JDBCUtil jdbc = new JDBCUtil();
	private AttendVO avo = null;
	private MemberVO mvo = null;
	private StringBuilder query = null;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private int resultRow = 0;
	
	public void insertCheck(String id){
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
	
	public String selectSysdate(){
		String result = "";
		Connection conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL");
		try {
			ps = conn.prepareStatement(query.toString());
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int selectMemberCnt(){
		int result = 0;
		Connection conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("SELECT COUNT(1) FROM MEMBER");
		try {
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
					vo.setPenalty(sumPenalty(rs.getString(5)));
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
	
	//과금발생 로직
	public String sumPenalty(String fullDate){
		String result = "";
		int penaltyUnit = 100; //분당 지각 비용
		int penaltyMax = 3000; //
		
		int hh = 0;
		int mm = 0;
		
		try{
			hh = Integer.parseInt(fullDate.substring(11, 13));
			mm = Integer.parseInt(fullDate.substring(14, 16));
			
			if(hh >= 9){
				if(mm == 0){
					result = "";
				}else if(mm>0 && mm<30){
					result = (mm * penaltyUnit) + "";
				}else{
					result = penaltyMax + "";
				}
			}
			
		}catch(NullPointerException e){
			result = "3000";
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		JDBCMainDAO jdbcDao = new JDBCMainDAO();
		
		Set<String> gijunDate = new TreeSet<String>();
		List<MemberVO> list = jdbcDao.selectAttendCheckAllMember(gijunDate);
		System.out.println(list.toString());
		
		
	}
	
}














