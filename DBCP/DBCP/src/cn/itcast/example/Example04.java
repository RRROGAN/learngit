package cn.itcast.example;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Example04 {
	public static DataSource ds = null;
	//初始化c3p0数据源
	static{
		//使用c3p0-config.xml配置文件中的name-config节点中的name属性的值
		//c3p0-config.xml中配置了2套数据源，<default-config>是默认数据源
		//<named-config name="itcast">是自定义数据源

		ComboPooledDataSource cpds = new ComboPooledDataSource("itcast");
		ds = cpds;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	System.out.println(ds.getConnection());	
	}

}

/*
 * 配置文件名称必须为c3p0-config.xml，并且位于该项目的src根目录下
 * 当传入的configName值为空或不存在时，则会使用默认的配置方式创建数据源
 * */
