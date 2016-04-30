package com.test1;
/*
 * 2016��3��17��09:54:39---
 *���ܣ����һ��mini�汾��ѧ������ϵͳ
 *model1ģʽ��������ҵ���߼������һ����ӣ��޸Ľ��涼�ò������ݿ�
 *�ŵ㣺�򵥣�����
 *ȱ�㣺���븴���Բ�ɶ��Բ�
 *
 * */
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.Vector;

public class Test3 extends JFrame implements ActionListener{
	

	
	//rowData������������ݣ�columNames�������
	Vector rowData, columnNames;
	JTable jt ;
	JScrollPane jsp ;
	//����һЩ�ؼ�
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4;
	JTextField jtf;
	JLabel jl1;
	StuModel stuModel;
	
	public static void main(String[] args){
		
		Test3 test3 = new Test3();
	}
	
	public Test3(){
		
		//����
		jp1 = new JPanel();		
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");
		jb1.setActionCommand("query");
		jb1.addActionListener(this);
		jl1 = new JLabel("����������");
		//�Ѹ����ؼ����뵽jp1  �����Jpanel1
		jp1.add(jl1);  //��������˳��ġ�
		jp1.add(jtf);
		jp1.add(jb1);
		
		//����
		jp2 = new JPanel();		
		jb2 = new JButton("���");
		jb2.addActionListener(this);
		jb2.setActionCommand("add");
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		jb2.setActionCommand("update");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
		jb4.addActionListener(this);
		jb2.setActionCommand("delete");
		jb4.addActionListener(this);
		 //�Ѹ�����ť���뵽jp2��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
	
		//�м�
		//����һ������ģ�Ͷ���
		 stuModel = new StuModel("");
		
		
		//��ʼ��JTable
		jt = new JTable(stuModel);
		
		//��ʼ��jsp
		jsp = new JScrollPane(jt);
		//��jsp����JFrame
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.add(jsp);
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�ж��ĸ���ť�����

		if(e.getActionCommand().equals("query")){
			System.out.println("�û���Ҫ��ѯ");
			//��ѯ���ݿ⣬�ظ��Բ����ˣ����Ը�һ�����ݿ����ӹ���
			//��취�ѶԱ�Ĳ�����װ��һ���࣬������ɱ����
			
			String name = this.jtf.getText().trim();
			
			//дһ��sql���
			String sql = "select * from stu where stuName='"+name+"'";
			//����sql������ģ��
			stuModel = new StuModel(sql);
			//����JTable*********����Ҫ
			jt.setModel(stuModel);
		
		
			
		}
		//���û�������,����һ������
		else if(e.getSource() == jb2){
			StuAddDialog sa = new StuAddDialog(this,"���ѧ��",true);
		}else if(e.getSource() == jb3){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			StuUpdDiolog stuUp = new StuUpdDiolog(this, "�޸�ѧ��", true, stuModel,rowNum);
			stuModel = new StuModel("");
			//����JTable*********����Ҫ
			jt.setModel(stuModel);
			System.out.println("hhh");
		
			
		}else if(e.getSource() == jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			//�õ�ѧ��ID
			String stuId = (String) stuModel.getValueAt(rowNum, 0);
			//System.out.println("id="+stuId);
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
				
				ps = ct.prepareStatement("delete from stu where stuId=?");  //��ֱ�������Ϊ�˷�ֹsqlע��
				ps.setString(1, stuId);
				
				//4.�õ������
				 ps.executeUpdate();
				//����sql������ģ��
					stuModel = new StuModel("");
					//����JTable*********����Ҫ
					jt.setModel(stuModel);
				
			
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
