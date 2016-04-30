/*
 * 从数据库中取出学生信息
 * */
package com.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Test2 extends JFrame{

	PreparedStatement ps = null;
	Connection ct = null;
	ResultSet rs = null;
	//rowData用来存放行数据，columNames存放列名
		Vector rowData, columnNames;
		JTable jt = null;
		JScrollPane jsp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test2 = new Test2();
	}
	
	public Test2(){
		columnNames = new Vector();
		//设置列名
		columnNames.add("学号");
		columnNames.add("名字");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		//rowData可以存放多行
		rowData = new Vector();
	
		
		//1.加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2.得到连接
			ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","root");
			//3.预编译
			ps = ct.prepareStatement("select * from stu" );
			//4.得到结果集
			rs = ps.executeQuery();
			//5.处理结果集
			while(rs.next()){
				Vector hang = new Vector();  //只能放在里面
				
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getInt(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				
				rowData.add(hang);
			}
			
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//6.关闭连接
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (ct != null)
					ct.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			
		}
													
		//初始化JTable
		jt = new JTable(rowData, columnNames);
		
		//初始化jsp
		jsp = new JScrollPane(jt);
		//把jsp放入JFrame
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
