/*
 * �����ݿ���ȡ��ѧ����Ϣ
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
	//rowData������������ݣ�columNames�������
		Vector rowData, columnNames;
		JTable jt = null;
		JScrollPane jsp = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test2 test2 = new Test2();
	}
	
	public Test2(){
		columnNames = new Vector();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		//rowData���Դ�Ŷ���
		rowData = new Vector();
	
		
		//1.��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2.�õ�����
			ct = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user","root","root");
			//3.Ԥ����
			ps = ct.prepareStatement("select * from stu" );
			//4.�õ������
			rs = ps.executeQuery();
			//5.��������
			while(rs.next()){
				Vector hang = new Vector();  //ֻ�ܷ�������
				
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
				//6.�ر�����
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
													
		//��ʼ��JTable
		jt = new JTable(rowData, columnNames);
		
		//��ʼ��jsp
		jsp = new JScrollPane(jt);
		//��jsp����JFrame
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
