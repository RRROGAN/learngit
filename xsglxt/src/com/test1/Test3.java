package com.test1;
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

public class Test3 extends JFrame implements ActionListener{
	

	
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
		
		Test3 test3 = new Test3();
	}
	
	public Test3(){
		
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
		jb2.setActionCommand("add");
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		jb2.setActionCommand("update");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
		jb4.addActionListener(this);
		jb2.setActionCommand("delete");
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
			System.out.println("hhh");
		
			
		}else if(e.getSource() == jb4){
			int rowNum = this.jt.getSelectedRow();
			if(rowNum == -1){
				//提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			//得到学生ID
			String stuId = (String) stuModel.getValueAt(rowNum, 0);
			//System.out.println("id="+stuId);
			PreparedStatement ps = null;
			Connection ct = null;
			ResultSet rs = null;
			
			//1.加载驱动
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//2.得到连接
				String url="jdbc:mysql://localhost:3306/user?user=root&password=root&useUnicode=true&characterEncoding=GBK" ;
				ct = DriverManager.getConnection(url);
				//3.预编译
				
				ps = ct.prepareStatement("delete from stu where stuId=?");  //不直接添加是为了防止sql注入
				ps.setString(1, stuId);
				
				//4.得到结果集
				 ps.executeUpdate();
				//根据sql语句更新模型
					stuModel = new StuModel("");
					//更新JTable*********很重要
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
