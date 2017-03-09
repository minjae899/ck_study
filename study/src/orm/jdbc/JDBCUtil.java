package orm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.vo.MemberVO;

public class JDBCUtil {
	
	private final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private final static String URL = "jdbc:oracle:thin:@victory.cs8zfdntdlzp.ap-northeast-2.rds.amazonaws.com:1521:orcl";
	private final static String USER = "victory";
	private final static String PASS = "victory123";
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:51:01
	* @other 
	* @return
	* TODO CK
	 */
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
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:51:05
	* @other 
	* @param conn
	* @param ps
	* TODO CK
	 */
	public void closeAll(Connection conn, PreparedStatement ps){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}if(ps !=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:51:09
	* @other 
	* @param conn
	* @param ps
	* @param rs
	* TODO CK
	 */
	public void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		closeAll(conn, ps);
	}
	
}
