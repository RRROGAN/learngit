package cn.itcast.example;
/*
 * 功能:数据源的使用
 * 2016年6月23日20:29:06
 * */
import java.sql.Connection;
import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.mysql.jdbc.DatabaseMetaData;

public class Example01 {

	public static DataSource ds = null;
	static{
		//获取DBCP数据源实现类
		BasicDataSource bds = new BasicDataSource();
		//设置连接数据库需要的配置信息
		bds.setDriverClassName("com.mysql.jdbc.Driver");
		bds.setUrl("jdbc:mysql://localhost:3306/hibernate");
		bds.setUsername("root");
		bds.setPassword("root");
		//设置连接池的参数
		bds.setInitialSize(5);
		bds.setMaxActive(5);
		ds = bds;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//获取数据库连接
		Connection conn = ds.getConnection();
		//获取数据库连接信息
		DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
		//打印数据库连接信息
		System.out.println(metaData.getURL()+",UserName=" + metaData.getUserName()
				+"," + metaData.getDriverName());
	}

}
