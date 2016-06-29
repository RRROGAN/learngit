package org.rogan.util;

import java.sql.*;
public class SqlHelper {
	
	//������Ҫ�Ķ���
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs ;
	
	String dirver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/test";
	String user = "root";
	String passwd = "root";
	
	public SqlHelper(){
		//��������
		try {
			Class.forName(dirver);
			ct = DriverManager.getConnection(url,user,passwd);
			System.out.println("���ӳɹ�");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ResultSet query(String sql,String[] paras){
		if(sql != null && !sql.equals("") ){
		try {
			ps = ct.prepareStatement(sql);
			//��sql�Ĳ�����ֵ
			if(paras != null && paras.length > 0){
				for(int i=0; i< paras.length;i++){
				//ps.setString(i+1, paras[i]);
				ps.setObject(i +1, paras[i]);
			}
			}
			//ִ�в�ѯ
			rs = ps.executeQuery();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		//���ؽ����
		return rs;
		
	}
	
	public boolean execute(String sql, String[] paras){
		//!sql.equals("") �ǣ�����д����һ���硣555��
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
				System.out.println("execute()��������");
				e.printStackTrace();
			}
		}
		return b;
		
	}
	
	//�ر���Դ
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
