package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
			try {
				jdbc.closeAll(conn, ps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
			try {
				jdbc.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return avo;
	}
	
	public List<MemberVO> selectAllMember(){
		List<MemberVO> allList = new ArrayList<MemberVO>();
		
		Connection conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("");
		
		return allList;
	}
	
	public List<MemberVO> selectMemberByDate(String targetDate){
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection conn = jdbc.getConnection();
		StringBuilder query = new StringBuilder();
		query.append("SELECT A.SEQ");
		query.append("	   , A.NAME");
		query.append("	   , A.ID");
		query.append("	   , A.PW");
		query.append("	   , A.AGE");
		query.append("	   , A.TELL");
		query.append("	   , A.REGISTER_DATE");
		query.append("	   , A.UPDATE_DATE");
		query.append("	   , B.CHECK_DATE");
		query.append("	   , TO_CHAR(B.CHECK_DATE, 'HH24:MI') AS CHECK_TIME");
		query.append("  FROM MEMBER A, ATTEND B");
		query.append(" WHERE A.ID = B.ID");
		query.append("   AND TO_CHAR(B.CHECK_DATE,'YYYY-MM-DD') = ?");

		try{
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			ps.setString(1, targetDate);
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				MemberVO mvo = new MemberVO();
				mvo.setSeq(rs.getInt(1));
				mvo.setName(rs.getString(2));
				mvo.setId(rs.getString(3));
				mvo.setPw(rs.getString(4));
				mvo.setAge(rs.getInt(5));
				mvo.setTell(rs.getString(6));
				mvo.setRegisterDate(rs.getString(7));
				mvo.setUpdateDate(rs.getString(8));
				mvo.setCheckDate(rs.getString(9));
				mvo.setCheckTime(rs.getString(10));
				memberList.add(mvo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				jdbc.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return memberList;
	}
	
	public ArrayList<String> selectCurrentDate(){
		List<String> list = new ArrayList<String>();
		
		Connection conn = jdbc.getConnection();
		StringBuilder query = new StringBuilder();
		query.append("SELECT TO_CHAR(SUM.CHECK_DATE,'YYYY-MM-DD') AS CHECK_DATE");
		query.append("  FROM (");
		query.append("			SELECT TO_DATE((SELECT TO_CHAR(SYSDATE,'YYYY') || '0101' FROM DUAL),'YYYYMMDD') + (ROWNUM-1) AS CHECK_DATE");
		query.append("				 , TO_CHAR(SYSDATE, 'YYYY-MM-DD') AS CURRENT_DATE");
		query.append("			  FROM DUAL CONNECT BY LEVEL <=365");
		query.append("	     ) SUM");
		query.append(" WHERE TO_CHAR(CHECK_DATE, 'D') = 7");
		query.append("   AND CHECK_DATE BETWEEN TO_DATE('2017-02-04','YYYY-MM-DD') AND CURRENT_DATE");
		
		try{
			System.out.println(query.toString());
			ps = conn.prepareStatement(query.toString());
			rs = ps.executeQuery();
			
			list = new ArrayList<String>();
			while(rs.next()){
				list.add(rs.getString(1));
			}
			
		}catch(Exception e){
			
		}finally{
			try {
				jdbc.closeAll(conn, ps, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (ArrayList<String>) list;
	}
	
	public static void main(String[] args) {
		JDBCMainDAO jdbcDao = new JDBCMainDAO();
		
		/*dao.insertCheck("chunkind");
		AttendVO avo = jdbcDao.selectAttend("chunkind");*/
		//System.out.println(jdbcDao.selectCurrentDate());
		
		ArrayList<String> dataList = jdbcDao.selectCurrentDate();
		HashMap<String, ArrayList<MemberVO>> result = new HashMap<String, ArrayList<MemberVO>>();
		
		for(int i=0; i<dataList.size(); i++){
			ArrayList<MemberVO> memberList = (ArrayList<MemberVO>) jdbcDao.selectMemberByDate(dataList.get(i));
			if(memberList.size() > 0) result.put(dataList.get(i), memberList);
		}
		
		System.out.println(result.toString());
		
		
	}
	
}














