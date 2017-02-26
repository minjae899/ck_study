package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.vo.MemberVO;

public class JDBCUtil {
	
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@victory.cs8zfdntdlzp.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	private final static String USER = "victory";
	private final static String PASS = "victory123";
	
	public Connection getConnection(){
		Connection conn = null;
		//1.드라이버 로딩
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void closeAll(Connection conn, PreparedStatement ps) throws SQLException{
		if(conn != null){
			conn.close();
		}if(ps !=null){
			ps.close();
		}
	}

	public void closeAll(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException{
		if(rs != null){
			rs.close();
		}
		closeAll(conn, ps);
	}
	
//	public MemberVO getMember(String id) throws Exception{
//		//JDBC에 필요한 변수들
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		MemberVO vo = new MemberVO();
//		
//		//DB연결을 위한 JDBC4단계
//		//1.드라이버 로딩
//		Class.forName(DRIVER);
//		//2.Connection 객체 생성
//		conn = DriverManager.getConnection(URL, USER, PASS);
//		//3.쿼리 객체 생성
//		String query = "SELECT SEQ, NAME, ID, PW, AGE, GENDER, TELL, REGISTER_DATE, UPDATE_DATE FROM MEMBER WHERE ID = ?";
//		ps = conn.prepareStatement(query);
//		ps.setString(1, id);
//		
//		//4.쿼리 수행 및 데이터 받아오기.
//		rs = ps.executeQuery();
//		
//		if(rs.next()){
//			vo.setSeq(rs.getInt(1));
//			vo.setName(rs.getString(2));
//			vo.setId(rs.getString(3));
//			vo.setPw(rs.getString(4));
//			vo.setAge(rs.getInt(5));
//			vo.setGender(rs.getString(6));
//			vo.setTell(rs.getString(7));
//			vo.setRegisterDate(rs.getString(8));
//			vo.setUpdateDate(rs.getString(9));
//			System.out.println(vo.toString());
//		}
//		
//		//사용했던 객체들을 닫아 자원을 반환해준다.
//		conn.close();
//		ps.close();
//		rs.close();
//		
//		return vo;
//	}
	
	//test를 위한 main 메서드
//	public static void main(String[] args) throws Exception {
//		JDBCUtil jdbc = new JDBCUtil();
//		//System.out.println("잘가져옴... : " + jdbc.getMember("chunkind").toString());
//		System.out.println(jdbc.getCurrentDate().toString());
//	}
	
}
