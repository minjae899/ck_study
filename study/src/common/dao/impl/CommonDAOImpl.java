package common.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.dao.CommonDAO;
import orm.jdbc.JDBCUtil;

public class CommonDAOImpl implements CommonDAO{

	private JDBCUtil jdbc = new JDBCUtil();
	private StringBuilder query = null;
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 4:50:12
	* @other 
	* @return
	* TODO CK
	 */
	@Override
	public String selectSysdate(){
		String result = "";
		conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL");
		try {
			System.out.println(query.toString());
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
	
	/**
	 * @quickCode ##
	* @auth CK
	* @date 2017. 2. 27. 오후 6:36:17
	* @other 
	* @return
	* TODO CK
	 */
	@Override
	public String selectAllowedIp(){
		String result = "";
		conn = jdbc.getConnection();
		query = new StringBuilder();
		query.append("SELECT IP FROM ALLOWED_IP WHERE CODE = 'CD001'");
		try {
			System.out.println(query.toString());
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
	
}
