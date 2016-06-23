package cn.itcast.example;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Example03 {
	public static DataSource ds = null;
	static{
		//获取DBCP数据源实现类
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		//设置连接数据库需要的配置信息
		try {
			cpds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cpds.setJdbcUrl("jdbc:mysql://localhost:3306/hibernate");
		cpds.setUser("root");
		cpds.setPassword("root");
		//设置连接池的参数
		cpds.setInitialPoolSize(5);
		cpds.setMaxPoolSize(5);
		ds = cpds;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(ds.getConnection());
	}

}
