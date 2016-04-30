package com.test2;
/*
 * 2016年3月17日09:54:39---
 *功能：完成一个mini版本的学生管理系统
 *model1模式：界面与业务逻辑混合在一起，添加，修改界面都得操作数据库
 *优点：简单，方便
 *缺点：代码复用性差，可读性差
 *
 * */
import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



import java.util.Vector;

public class StuManger extends JFrame implements ActionListener{
	

	
	//rowData用来存放行数据，columNames存放列名
	Vector rowData, columnNames;
	JTable jt ;
	JScrollPane jsp ;
	//定义一些控件
	JPanel jp1,jp2;
	JButton jb1,jb2,jb3,jb4;
	JTextField jtf;
	JLabel jl1;
	StuModel stuModel;
	
	public static void main(String[] args){
		
		StuManger test3 = new StuManger();
	}
	
	public StuManger(){
		
		//上面
		jp1 = new JPanel();		
		jtf = new JTextField(10);
		jb1 = new JButton("查询");
		jb1.setActionCommand("query");
		jb1.addActionListener(this);
		jl1 = new JLabel("请输入名字");
		//把各个控件加入到jp1  上面的Jpanel1
		jp1.add(jl1);  //加入是有顺序的。
		jp1.add(jtf);
		jp1.add(jb1);
		
		//下面
		jp2 = new JPanel();		
		jb2 = new JButton("添加");
		jb2.addActionListener(this);
		//jb2.setActionCommand("add");
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		//jb3.setActionCommand("update");
		
		jb4 = new JButton("删除");
		jb4.addActionListener(this);
	
		 //把各个按钮加入到jp2中
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
	
		//中间
		//创建一个数据模型对象
		 stuModel = new StuModel("");
		
		
		//初始化JTable
		jt = new JTable(stuModel);
		
		//初始化jsp
		jsp = new JScrollPane(jt);
		//把jsp放入JFrame
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
		//判断哪个按钮被点击

		if(e.getActionCommand().equals("query")){
			System.out.println("用户想要查询");
			//查询数据库，重复性操作了，可以搞一个数据库连接工具
			//想办法把对表的操作封装成一个类，可以完成表操作
			
			String name = this.jtf.getText().trim();
			
			//写一个sql语句
			String sql = "select * from stu where stuName='"+name+"'";
			//根据sql语句更新模型
			stuModel = new StuModel(sql);
			//更新JTable*********很重要
			jt.setModel(stuModel);
		
		
			
		}
		//当用户点击添加,弹出一个窗口
		else if(e.getSource() == jb2){
			StuAddDialog sa = new StuAddDialog(this,"添加学生",true);
			//下面两句代码是实时更新
			stuModel = new StuModel("");
			jt.setModel(stuModel);
		}else if(e.getSource() == jb3){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			
			StuUpdDiolog stuUp = new StuUpdDiolog(this, "修改学生", true, stuModel,rowNum);
			stuModel = new StuModel("");
			//更新JTable*********很重要
			jt.setModel(stuModel);
			
		
			
		}else if(e.getSource() == jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选中一行ha");
				return;
			}
			//得到学生ID
			String stuId = (String) stuModel.getValueAt(rowNum, 0);			
			String sql = "delete from stu where stuId=?";
			String[] paras = {stuId};
			StuModel temp = new StuModel("");
			temp.updateStu(sql, paras);
			
			 stuModel = new StuModel("");
			//更新JTable*********很重要
			jt.setModel(stuModel);
		}
		
	}
}
