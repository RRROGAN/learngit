package com.test2;
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
	
	//owner它的父窗口
	//title 窗口名
	//model 指定是模式窗口，还是非模式窗口
	public StuUpdDiolog(Frame owner,String title,boolean model,StuModel sm,int rowNum){
		super(owner,title,model);  //调用父类构造方法，达到模式对话框
		jl1 = new JLabel("学号");
		jtf1 = new JTextField();
		//String stuId = (String) sm.getValueAt(rowNum, 0);
		jtf1.setText((String) sm.getValueAt(rowNum, 0));
		//让jtf1不能修改
		jtf1.setEditable(false);
				
		jl2 = new JLabel("姓名");
		jtf2 = new JTextField();
		jtf2.setText((String) sm.getValueAt(rowNum, 1));
		jl3 = new JLabel("性别");
		jtf3 = new JTextField();
		jtf3.setText((String) sm.getValueAt(rowNum, 2));
		jl4 = new JLabel("年龄");
		jtf4 = new JTextField();
		jtf4.setText(sm.getValueAt(rowNum, 3).toString());
		jl5 = new JLabel("籍贯");
		jtf5 = new JTextField();
		jtf5.setText((String) sm.getValueAt(rowNum, 4));
		jl6 = new JLabel("系别123");
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
		jb1 = new JButton("修改");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
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
			//添加
			StuModel temp = new StuModel("");
			String sql ="update stu set stuName=?,stuSex=?,stuAge=?,stuJg=?,stuDep=? where stuId=?" ;
			String[] paras = {jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf1.getText()};
			if(!temp.updateStu(sql, paras)){
				JOptionPane.showMessageDialog(this, "修改失败");
				
			}
			this.dispose();
			
		}
	}
	
}
