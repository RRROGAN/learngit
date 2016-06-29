package org.rogan.service;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import org.rogan.util.SqlHelper;

public class DelWin extends JFrame implements ActionListener {
	
	JTextField dname;  //删除单词文本条
	JButton btnDel,btnCancel;
	
	public DelWin() {
		// TODO Auto-generated constructor stub
		super("删除单词");
		this.setBounds(250,250,250,250);
		this.setVisible(true);
		JPanel p1 = new JPanel();
		p1.add(new JLabel("输入要删除的单词："));
		dname = new JTextField(20);
		p1.add(dname);
		btnDel = new JButton("删除");
		btnCancel = new JButton("取消");
		p1.add(btnDel);
		p1.add(btnCancel);
		
		this.add(p1);
		btnDel.addActionListener(this);
		btnCancel.addActionListener(this);
		this.validate();
		this.setResizable(false);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnDel){
			if(dname.getText().equals("")){
				JOptionPane.showMessageDialog(this, "删除的单词不能为空","警告",JOptionPane.WARNING_MESSAGE);			
				
			}else{
				try {
					delete();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(e.getSource() == btnCancel){
			dispose();
		}
	}
	
	public void delete() throws SQLException{
		String cname1,ename1;
		String sql = "select * from dic";
		String[] paras = {};
		boolean boo = false;
		SqlHelper sqlHelp = new SqlHelper();
		ResultSet rs = sqlHelp.query(sql, paras);
		while((boo = rs.next()) == true){
			ename1 = rs.getString("word");
			cname1 = rs.getString("meaning");
			if(ename1.equals(dname.getText())){
				String s1 = dname.getText().trim();
				String tempSql = "delete from dic where word=?";
				String[] tempparas = {s1};
				sqlHelp.execute(tempSql, tempparas);
				
				JOptionPane.showMessageDialog(this, "成功删除记录","恭喜",JOptionPane.WARNING_MESSAGE);
				dispose();
				break;
			}
		}
		if(boo == false){
			JOptionPane.showMessageDialog(this, "不存在此单词","警告",JOptionPane.WARNING_MESSAGE);

			
		}
		sqlHelp.close();
		
	}

}
