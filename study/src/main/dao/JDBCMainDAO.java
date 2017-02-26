package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	
	public static void main(String[] args) {
		JDBCMainDAO jdbcDao = new JDBCMainDAO();
		
		/*dao.insertCheck("chunkind");
		AttendVO avo = jdbcDao.selectAttend("chunkind");*/
		System.out.println(jdbcDao.selectSysdate());
		
	}
	
}














