package jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.vo.MainVO;

public class JDBCUtil {
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@ck-dev.cs8zfdntdlzp.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	private final static String USER = "ck";
	private final static String PASS = "wnstjd88";
	
	
	public MainVO getMember(String id) throws Exception{	// 여기는 왜 void가 아니라 리턴값이 CustVO임?
		//JDBC에 필요한 변수들
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MainVO vo = null;
		
		//DB연결을 위한 JDBC4단계
		//1.드라이버 로딩
		Class.forName(DRIVER);
		//2.Connection 객체 생성
		conn = DriverManager.getConnection(URL, USER, PASS);
		//3.쿼리 객체 생성
		String query = "SELECT USER_ID, USER_PW FROM MEMBER WHERE USER_ID = ?";
		ps = conn.prepareStatement(query);
		ps.setString(1, id);
		
		//4.쿼리 수행 및 데이터 받아오기.
		rs = ps.executeQuery();
		
		if(rs.next()){
			vo = new MainVO(id, rs.getString(2));
			System.out.println(vo.toString());
		}
		
		//사용했던 객체들을 닫아 자원을 반환해준다.
		conn.close();
		ps.close();
		rs.close();
		
		return vo;
	}
	
	//test를 위한 main 메서드
	public static void main(String[] args) throws Exception {
		JDBCUtil jdbc = new JDBCUtil();
		System.out.println("잘가져옴... : " + jdbc.getMember("chunkind").toString());
	}
	
}
