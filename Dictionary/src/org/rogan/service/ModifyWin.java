package org.rogan.service;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import org.rogan.util.SqlHelper;

public class ModifyWin extends JFrame implements ActionListener {

	JTextField cname,ename;
	JButton btnModify,btnCancel;
	public ModifyWin() {
		// TODO Auto-generated constructor stub
		super("修改");
		this.setBounds(250,250,250,250);
		this.setVisible(true);
		JPanel p = new JPanel();
		p.add(new JLabel("输入英语单词："));
		ename = new JTextField(20);
		p.add(ename);
		p.add(new JLabel("输入该单词修改的汉语解释"));
		cname = new JTextField(20);
		p.add(cname);
		
		btnModify = new JButton("提交");
		btnCancel = new JButton("取消");
		p.add(btnModify);
		p.add(btnCancel);
		btnModify.addActionListener(this);
		btnCancel.addActionListener(this);
		
		this.add(p);
		this.validate();
		this.setResizable(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnModify){
			if(ename.getText().equals("") || cname.getText().equals("")){
				JOptionPane.showMessageDialog(this, "修改的单词或解释不能为空格！","警告",JOptionPane.WARNING_MESSAGE);
				
			}
			else{
				try {
					modify();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == btnCancel){
			dispose();
		}
	}
	
	public void modify() throws HeadlessException, SQLException{
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
				String s1 =  ename.getText().trim();
				String s2 = cname.getText().trim();
				String tempSql = "update dic set meaning=? where word=?";
				String[] tempparas = {s2,s1};
				if(sqlHelp.execute(tempSql, tempparas)){
				
				JOptionPane.showMessageDialog(this, "记录修改成功","恭喜",JOptionPane.WARNING_MESSAGE);
				dispose();
				}
				break;
				
			}
		}
		if(boo == false){
			JOptionPane.showMessageDialog(this, "不存在此单词","警告",JOptionPane.WARNING_MESSAGE);

			
		}
		sqlHelp.close();
	}

	
}
