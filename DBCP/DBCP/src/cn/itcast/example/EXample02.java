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
		//�½�һ�������ļ�
		Properties prop = new Properties();
		InputStream in = new EXample02().getClass().getClassLoader().getResourceAsStream("dbcpconfig.properties");
		//���ļ�������������ʽ���ص����ö�����
		try {
			prop.load(in);
			//��������Դ����
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException{
		//��ȡ���ݿ�����
		Connection conn = ds.getConnection();
		//��ȡ���ݿ�������Ϣ
		DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
		//��ӡ���ݿ�������Ϣ
		System.out.println(metaData.getURL()+",UserName=" + metaData.getUserName()
				+"," + metaData.getDriverName());
	}
}
