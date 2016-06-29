package org.rogan.util;

import java.sql.*;
public class SqlHelper {
	
	//定义需要的对象
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs ;
	
	String dirver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/test";
	String user = "root";
	String passwd = "root";
	
	public SqlHelper(){
		//加载驱动
		try {
			Class.forName(dirver);
			ct = DriverManager.getConnection(url,user,passwd);
			System.out.println("连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String sql,String[] paras){
		if(sql != null && !sql.equals("") ){
		try {
			ps = ct.prepareStatement(sql);
			//对sql的参数赋值
			if(paras != null && paras.length > 0){
				for(int i=0; i< paras.length;i++){
				//ps.setString(i+1, paras[i]);
				ps.setObject(i +1, paras[i]);
			}
			}
			//执行查询
			rs = ps.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		//返回结果集
		return rs;
		
	}
	
	public boolean execute(String sql, String[] paras){
		//!sql.equals("") 非！忘了写搞了一下午。555！
		boolean b = true;
		if(sql != null && !sql.equals("")){
			try {
				ps = ct.prepareStatement(sql);
				if(paras != null && paras.length > 0){
				for(int i = 0; i < paras.length; i++){
					ps.setObject(i+1,paras[i]);
				}
				}
				//ps.execute();
				if(ps.executeUpdate() != 1){
					b = false;
				}
			
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				b = false;
				System.out.println("execute()方法出错");
				e.printStackTrace();
			}
		}
		return b;
		
	}
	
	//关闭资源
	public void close(){
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (ct != null) {
				ct.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
