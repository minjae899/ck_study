package jdbc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.config.OracleInfo;
import main.vo.MainVO;

public class JDBCUtil {

	// 공통적인 기능을 추출한 getConnection() | close()
	public Connection getConn() throws SQLException {
		Connection conn = DriverManager.getConnection(OracleInfo.URL, OracleInfo.USER, OracleInfo.PASS);
		System.out.println("DB Connecting..OK");
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
	
	public void insertQuery(Object obj, String tableNM) throws Exception{
		
		Field[] fieldList = obj.getClass().getFields();
		
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getConn();

		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO " + tableNM + "(");
		for(int i=0; i < fieldList.length; i++){
			if(i == fieldList.length-1){
				query.append(fieldList[i].getName());
			}else{
				query.append(fieldList[i].getName() + ", ");
			}
			
		}
		query.append(") VALUES(");
		for(int i=0; i < fieldList.length; i++){
			if(i == fieldList.length-1){
				query.append("?");
			}else{
				query.append("?, ");
			}
		}
		query.append(")");
		
		String strQuery = query.toString();
		ps = conn.prepareStatement(strQuery);
		
		for(int i=0; i<fieldList.length; i++){
			String methodNm = makeGetter(fieldList[i].getName());
			Method method = obj.getClass().getMethod(methodNm);
			
			Class<?> classType = fieldList[i].getType();
			
//			if(classType instanceof String){
//				
//			}
			
//			ps.setString(i, x);
		}
		
		
//		ps.setString(1, vo.getId());
//		ps.setString(2, vo.getName());
//		ps.setString(3, vo.getAddr());

		int row = ps.executeUpdate();
		System.out.println(row + "row addCust().. OK");

		closeAll(conn, ps);
	}

//	public void deleteCust(String id) throws SQLException{
//		Connection conn = null;
//		PreparedStatement ps = null;
//		conn = getConn();
//
//		String query = "DELETE FROM cust WHERE id = ?";
//		ps = conn.prepareStatement(query);
//		ps.setString(1, id);
//
//		int row =ps.executeUpdate();
//		System.out.println(row + "row deleteCust().. OK");
//
//		closeAll(conn, ps);
//	}
//
//	public void updateCust(CustVO vo) throws SQLException{// id로 찾을꺼니까 ()안에는 id인자가 들어가야 하는거아님?
//		Connection conn = null;
//		PreparedStatement ps = null;
//		conn = getConn();
//
//		String query = "UPDATE cust SET name = ?, addr = ? WHERE id = ?";
//		ps = conn.prepareStatement(query);
//		ps.setString(1, vo.getName());	//어느때에는 ps.setString(1, vo.getName()); 이렇게 하고 또 어느때에는 ps.setString(1, Name); 이렇게 하는지 구별방법. 처음에 ps.setString(1, Name); 이렇게 하니까 오류나서 옆에꺼 보니까 ps.setString(1, vo.getName()); 이렇게 하는거 였음
//		ps.setString(2, vo.getAddr());
//		ps.setString(3, vo.getId());
//
//		int row = ps.executeUpdate();
//		System.out.println(row + "row UpdateCust().. OK");
//
//		closeAll(conn, ps);
//	}
//	public void updateCust(String id, String name, String addr) throws SQLException{
//		Connection conn = null;
//		PreparedStatement ps = null;
//		conn = getConn();
//
//		String query = "UPDATE cust SET name = ?, addr = ? WHERE id = ?";
//		ps = conn.prepareStatement(query);
//		ps.setString(1, name);
//		ps.setString(2, addr);
//		ps.setString(3, id);
//
//		int row = ps.executeUpdate();
//		System.out.println(row + "row UpdateCust().. OK");
//
//		closeAll(conn, ps);
//	} //위에랑 똑같은거
//
//	public CustVO getCust(String id) throws SQLException{	// 여기는 왜 void가 아니라 리턴값이 CustVO임?
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;	// 이건 왜 쓰임.........
//		CustVO vo = null;
//		conn = getConn();	//아니구나 쓰이는 구나!
//		
//		String query = "SELECT * FROM cust WHERE id = ?";
//		ps = conn.prepareStatement(query);
//		ps.setString(1, id);	//ps랑 rs가 왜 이렇게 쓰이는지 정확한 해석 요망
//		rs = ps.executeQuery();
//		
//		if(rs.next()){
//			vo = new CustVO(id, rs.getString(2), rs.getString(3));	//rs.getString(2) 이게 몬데 ㅅㅂ. rs는 여기서 나오는데 도대체 여기에서 getString(2)이게 의미하는게 뭐야 ㅅㅂ 무슨 두번째인데 씨팔 그리고 if문은 또 왜써!!!
//			System.out.println("VO 객체 만들었음.");
//		}
//		closeAll(conn, ps, rs);
//		return vo;
//	}	// 얘랑 밑에는 찾았다고 출력문 안날려? 그리고 return 은 왜써?
//
//	public ArrayList<CustVO> getAllCust() throws SQLException{	// <>이안에 CustVO는 왜 들어가는데!!!??
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		ArrayList<CustVO> list = new ArrayList<CustVO>();
//		
//		conn = getConn();
//		
//		String query = "SELECT * FROM cust";	// 얜 where로 어디인지 지정 안해줘도 됨?
//		ps = conn.prepareStatement(query);
//		rs = ps.executeQuery();
//		
//		while(rs.next()){
//			list.add(new CustVO(rs.getString(1), rs.getString(2), rs.getString(3)));
//		}	// 위에 if가 왜 쓰이는지 이제 쫌 알껏 같음! 위에 if는 id라는 조건으로 찾아야 해서 if를 쓴건데 여기는 왜 while을 써?
//		closeAll(conn, ps, rs);
//		return list;	
//	}
	
	public String makeGetter(String fieldName){
		String firstLetter = fieldName.substring(0, 1);
		String completeLetter = "get"+ firstLetter.toUpperCase() +fieldName.substring(1, fieldName.length());
		return completeLetter;
	}

	public static void main(String[] args) {
		JDBCUtil jdbc = new JDBCUtil();
		MainVO mvo = new MainVO();
		mvo.setId("chunkind");
		mvo.setPassword("wnstjd");
		try {
			jdbc.insertQuery(mvo, "member");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
