/*
 * 2016年3月14日20:57:37
 * JTable的使用
 * */
package com.test2;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;
public class Test1 extends JFrame{
	
	//rowData用来存放行数据，columNames存放列名
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test1 test1 = new Test1(); 
	}
	
	public Test1(){
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
		Vector hang = new Vector();
		hang.add("sp001");
		hang.add("孙悟空");
		hang.add("妖");
		hang.add("500");
		hang.add("花果山");
		hang.add("少林派");
		 //加入到rowData
		rowData.add(hang);
		rowData.add(hang);
		
		
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
