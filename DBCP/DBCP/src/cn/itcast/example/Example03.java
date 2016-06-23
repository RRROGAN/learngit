package cn.itcast.example;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Example03 {
	public static DataSource ds = null;
	static{
		//��ȡDBCP����Դʵ����
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		//�����������ݿ���Ҫ��������Ϣ
		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/hibernate");
		cpds.setUser("root");
		cpds.setPassword("root");
		//�������ӳصĲ���
		cpds.setInitialPoolSize(5);
		cpds.setMaxPoolSize(5);
		ds = cpds;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(ds.getConnection());
	}

}
