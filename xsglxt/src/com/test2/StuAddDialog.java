package com.test2;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
public class StuAddDialog extends JDialog implements ActionListener{
	
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JButton jb1,jb2;
	JPanel jp1,jp2,jp3;
	
	public static void main(String[] args){
		//StuAddDialog sa = new StuAddDialog();
	}
	
	//owner它的父窗口
	//title 窗口名
	//model 指定是模式窗口，还是非模式窗口
	public StuAddDialog(Frame owner,String title,boolean model){
		super(owner,title,model);  //调用父类构造方法，达到模式对话框
		jl1 = new JLabel("学号");
		jtf1 = new JTextField();
		jl2 = new JLabel("姓名");
		jtf2 = new JTextField();
		jl3 = new JLabel("性别");
		jtf3 = new JTextField();
		jl4 = new JLabel("年龄");
		jtf4 = new JTextField();
		jl5 = new JLabel("籍贯");
		jtf5 = new JTextField();
		jl6 = new JLabel("系别123");
		jtf6 = new JTextField();
		
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
		jb1 = new JButton("添加");
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
			String sql = "insert into stu values(?,?,?,?,?,?)";
			String[] paras = {jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText()};
			if(!temp.updateStu(sql, paras)){
				JOptionPane.showMessageDialog(this, "添加失败");
				
			}
			this.dispose();
			
		}
}
}