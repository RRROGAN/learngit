package cn.itcast.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import com.mysql.jdbc.DatabaseMetaData;

public class EXample02 {
	public static DataSource ds = null;
	static{
		//新建一个配置文件
		Properties prop = new Properties();
		InputStream in = new EXample02().getClass().getClassLoader().getResourceAsStream("dbcpconfig.properties");
		//把文件以输入流的形式加载到配置对象中
		try {
			prop.load(in);
			//创建数据源对象
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException{
		//获取数据库连接
		Connection conn = ds.getConnection();
		//获取数据库连接信息
		DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
		//打印数据库连接信息
		System.out.println(metaData.getURL()+",UserName=" + metaData.getUserName()
				+"," + metaData.getDriverName());
	}
}
