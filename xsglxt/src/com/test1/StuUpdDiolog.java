package com.test1;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class StuUpdDiolog extends JDialog implements ActionListener{
	
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3;
	
	//owner���ĸ�����
	//title ������
	//model ָ����ģʽ���ڣ����Ƿ�ģʽ����
	public StuUpdDiolog(Frame owner,String title,boolean model,StuModel sm,int rowNum){
		super(owner,title,model);  //���ø��๹�췽�����ﵽģʽ�Ի���
		jl1 = new JLabel("ѧ��");
		jtf1 = new JTextField();
		//String stuId = (String) sm.getValueAt(rowNum, 0);
		jtf1.setText((String) sm.getValueAt(rowNum, 0));
		//��jtf1�����޸�
		jtf1.setEditable(false);
				
		jl2 = new JLabel("����");
		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNum, 1));
		jl3 = new JLabel("�Ա�");
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNum, 2));
		jl4 = new JLabel("����");
		jtf4 = new JTextField();
		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
		jl5 = new JLabel("����");
		jtf5 = new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNum, 4));
		jl6 = new JLabel("ϵ��123");
		jtf6 = new JTextField();
		jtf6.setText((String) sm.getValueAt(rowNum, 5));
		
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(6,1));
	
		
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		
		
		jp2 = new JPanel();
		jp2.setLayout(new GridLayout(6,1));
		jp2.add(jtf1);
		jp2.add(jtf2);
		jp2.add(jtf3);
		jp2.add(jtf4);
		jp2.add(jtf5);
		jp2.add(jtf6);
		
		
		jp3 = new JPanel();
		jb1 = new JButton("�޸�");
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
		jp3.add(jb1);
		jp3.add(jb2);
		
		this.add(jp1,BorderLayout.WEST);
		
		this.add(jp2,BorderLayout.CENTER);
		this.add(jp3,BorderLayout.SOUTH);
		
		this.setSize(300,200);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		this.setTitle(title);
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jb1){
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			
			//1.��������
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//2.�õ�����
				String url="jdbc:mysql://localhost:3306/user?user=root&password=root&useUnicode=true&characterEncoding=GBK" ;
				ct = DriverManager.getConnection(url);
				//3.Ԥ����
				
			//	ps = ct.prepareStatement("insert into stu values(?,?,?,?,?,?)");  //��ֱ�������Ϊ�˷�ֹsqlע��
				ps = ct.prepareStatement("update stu set stuName=?,stuSex=?,stuAge=?,stuJg=?,stuDep=? where stuId=?" );
				
				ps.setString(1, jtf2.getText());
				
				ps.setString(2, jtf3.getText());
				ps.setString(3, jtf4.getText());
				//System.out.println(jtf4.getText());
				ps.setString(4, jtf5.getText());
				ps.setString(5, jtf6.getText());
				ps.setString(6, jtf1.getText());
				ps.executeUpdate();
				
				
				// System.out.println( ps.executeUpdate());
				// System.out.println("zala");
				 //System.out.println("000");
				 this.dispose();  //�رնԻ���
				//5.��������
				
		}catch(Exception ex){
			
		}finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(ct != null){
				try {
					ct.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		}
	}
	
}
