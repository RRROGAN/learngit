/*
 * 2016��3��14��20:57:37
 * JTable��ʹ��
 * */
package com.test2;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
public class Test1 extends JFrame{
	
	//rowData������������ݣ�columNames�������
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1(); 
	}
	
	public Test1(){
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
		Vector hang = new Vector();
		hang.add("sp001");
		hang.add("�����");
		hang.add("��");
		hang.add("500");
		hang.add("����ɽ");
		hang.add("������");
		 //���뵽rowData
		rowData.add(hang);
		rowData.add(hang);
		
		
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
