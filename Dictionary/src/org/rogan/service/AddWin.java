package org.rogan.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import org.rogan.util.SqlHelper;


public class AddWin extends JFrame implements ActionListener {

	JTextField cname,ename;
	JButton btnAdd,btnCancel;
	
	public AddWin() {
		// TODO Auto-generated constructor stub
		super("添加单词");
		this.setBounds(250,250,250,200);
		this.setVisible(true);
		JPanel p1 = new JPanel();
		p1.add(new JLabel("输入要添加的单词："));
		ename = new JTextField(20);
		p1.add(ename);
		p1.add(new JLabel("输入要添加的单词的解释"));
		cname = new JTextField(20);
		p1.add(cname);
		
		btnAdd = new JButton("提交");
		btnCancel = new JButton("取消");
		
		p1.add(btnAdd);
		p1.add(btnCancel);
		
		this.add(p1);
		
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
		this.validate();
		this.setResizable(false);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnAdd){
			//判断输入框是否为空
			if(ename.getText().equals("") || cname.getText().equals("")){
				JOptionPane.showMessageDialog(this, "添加的单词或解释不能为空","警告",JOptionPane.WARNING_MESSAGE);
				
			}else{
				try {
					addWord();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  //调用添加方法
			}
		}
		else if(e.getSource() == btnCancel){
			dispose();
		}
	}
	
	public void addWord() throws Exception{
		String cname1,ename1;
		String sql = "select * from dic";
		String[] paras = {};
		boolean boo = false;
		SqlHelper sqlHelp = new SqlHelper();
		ResultSet rs = sqlHelp.query(sql, paras);
		while((boo = rs.next()) == true){
			ename1 = rs.getString("word");
			cname1 = rs.getString("meaning");
			if(ename1.equals(ename.getText())){
				JOptionPane.showMessageDialog(this, "词汇已存在！不要添加了","警告",JOptionPane.WARNING_MESSAGE);
				break;
			}
		}
		if(boo == false){
			String s1 = ename.getText().trim();
			String s2 = cname.getText().trim();
			String tempSql = "insert into dic values(?,?)";
			String[] tempparas = {s1,s2};
			sqlHelp.execute(tempSql, tempparas);
			
			JOptionPane.showMessageDialog(this, "添加成功","恭喜",JOptionPane.WARNING_MESSAGE);
			dispose();
		}
		sqlHelp.close();
	}

}
