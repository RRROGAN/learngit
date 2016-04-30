package com.test2;
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

public class StuManger extends JFrame implements ActionListener{
	

	
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
		
		StuManger test3 = new StuManger();
	}
	
	public StuManger(){
		
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
		//jb2.setActionCommand("add");
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		//jb3.setActionCommand("update");
		
		jb4 = new JButton("ɾ��");
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
			//�������������ʵʱ����
			stuModel = new StuModel("");
			jt.setModel(stuModel);
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
			
		
			
		}else if(e.getSource() == jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��ha");
				return;
			}
			//�õ�ѧ��ID
			String stuId = (String) stuModel.getValueAt(rowNum, 0);			
			String sql = "delete from stu where stuId=?";
			String[] paras = {stuId};
			StuModel temp = new StuModel("");
			temp.updateStu(sql, paras);
			
			 stuModel = new StuModel("");
			//����JTable*********����Ҫ
			jt.setModel(stuModel);
		}
		
	}
}
