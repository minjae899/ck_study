package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.vo.MainVO;

public class JDBCUtil {
	
	public MainVO login(String id) throws Exception{
		MainVO rvo = new MainVO();
				
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MainVO vo = null;
		
		String url = "jdbc:oracle:thin:@victory.cs8zfdntdlzp.ap-northeast-2.rds.amazonaws.com:1521:orcl";
		String user = "victory";
		String pass = "victory123";
		
		//1.드라이버 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.커넥션 객체 생성
		conn = DriverManager.getConnection(url, user, pass);
		//3.쿼리 객체 생성
		String query = "SELECT USER_ID, USER_PW FROM MEMBER WHERE USER_ID = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, id);
		//4.쿼리 수행
		rs= ps.executeQuery();
		
		if(rs.next()){
			rvo.setUserId(rs.getString(1));
			rvo.setUserPw(rs.getString(2));
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
		}
		//사용했던 자원 반환
		rs.close();
		ps.close();
		conn.close();
		
		return rvo;
	}
	
	//JDBC 4 단계
	public static void main(String[] args) throws Exception {
		
	}
}
