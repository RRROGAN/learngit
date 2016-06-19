package org.easybooks.bookstore.jdbc;
import java.sql.*;
public class MySQLConnBean {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public MySQLConnBean() {
		// TODO Auto-generated constructor stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hibernate","root","root");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//执行查询类的sql语句
	public ResultSet executeQuery(String sql){
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	public void close(){
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
