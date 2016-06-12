package com.rogan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public class SqlHelper {
 private static Connection ct;
 private static PreparedStatement ps;
 private static ResultSet rs;
 
 String driver = "com.mysql.jdbc.Driver";
 String url = "jdbc:mysql://127.0.0.1:3306/myshopping";
 String user = "root";
 String passwd = "root";
 
 public SqlHelper(){
	
	 try {
		 Class.forName("com.mysql.jdbc.Driver");
		ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/myshopping","root","root");
		System.out.println("连接成功");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
 }
 
 public static ArrayList query(String sql, String[] paras) throws SQLException{
	 ArrayList al = new ArrayList();
	 if(sql!=null && !sql.equals("")){
		 ps = ct.prepareStatement(sql);
	 if(paras!=null && paras.length>0){
		 for(int i=0; i < paras.length; i++){
			 ps.setString(i+1,paras[i]);
		 }
		
		 //执行查询
		 rs = ps.executeQuery();
		 
		 
		 //元数据
		 ResultSetMetaData rsmd = rs.getMetaData();
		 //得到元数据的总列数
		int numCols = rsmd.getColumnCount();		    

		 while(rs.next()){
			 Map<String,Object> rowData = new HashMap<String,Object>();
			for(int i=1;i<=numCols;i++){
				rowData.put(rsmd.getColumnName(i),rs.getObject(i));
			}
			 al.add(rowData);
		 }
		
	 }
	 }
	return al;
	
	 
 }
	 
 public static boolean execute(String sql, String[] paras) throws SQLException{
	 boolean b = true;
	 if(sql != null && !sql.equals("")){
		 ps = ct.prepareStatement(sql);
		 for(int i=0; i < paras.length; i++){
			 ps.setObject(i+1, paras[i]);
		 }
		 if(ps.executeUpdate() != 1){
			 b = false;
		 }
	 }
	 
	return b;
	 
	 
 }
 
 public static void close(){
	 try {
		if(rs != null) rs.close();
		if(ps != null) ps.close();
		if(ct != null) ct.close();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} 
 }
 }
 
 

