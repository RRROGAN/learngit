package cn.itcast.example;

import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Example04 {
	public static DataSource ds = null;
	//��ʼ��c3p0����Դ
	static{
		//ʹ��c3p0-config.xml�����ļ��е�name-config�ڵ��е�name���Ե�ֵ
		//c3p0-config.xml��������2������Դ��<default-config>��Ĭ������Դ
		//<named-config name="itcast">���Զ�������Դ

		ComboPooledDataSource cpds = new ComboPooledDataSource("itcast");
		ds = cpds;
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
	System.out.println(ds.getConnection());	
	}

}

/*
 * �����ļ����Ʊ���Ϊc3p0-config.xml������λ�ڸ���Ŀ��src��Ŀ¼��
 * �������configNameֵΪ�ջ򲻴���ʱ�����ʹ��Ĭ�ϵ����÷�ʽ��������Դ
 * */
