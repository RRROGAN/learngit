package cn.itcast.example;
/*
 * ����:����Դ��ʹ��
 * 2016��6��23��20:29:06
 * */
import java.sql.Connection;
import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.mysql.jdbc.DatabaseMetaData;

public class Example01 {

	public static DataSource ds = null;
	static{
		//��ȡDBCP����Դʵ����
		BasicDataSource bds = new BasicDataSource();
		//�����������ݿ���Ҫ��������Ϣ
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost:3306/hibernate");
		bds.setUsername("root");
		bds.setPassword("root");
		//�������ӳصĲ���
		bds.setInitialSize(5);
		bds.setMaxActive(5);
		ds = bds;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//��ȡ���ݿ�����
		Connection conn = ds.getConnection();
		//��ȡ���ݿ�������Ϣ
		DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
		//��ӡ���ݿ�������Ϣ
		System.out.println(metaData.getURL()+",UserName=" + metaData.getUserName()
				+"," + metaData.getDriverName());
	}

}
