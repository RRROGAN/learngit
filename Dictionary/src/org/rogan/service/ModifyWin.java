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
		super("�޸�");
		this.setBounds(250,250,250,250);
		this.setVisible(true);
		JPanel p = new JPanel();
		p.add(new JLabel("����Ӣ�ﵥ�ʣ�"));
		ename = new JTextField(20);
		p.add(ename);
		p.add(new JLabel("����õ����޸ĵĺ������"));
		cname = new JTextField(20);
		p.add(cname);
		
		btnModify = new JButton("�ύ");
		btnCancel = new JButton("ȡ��");
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
				JOptionPane.showMessageDialog(this, "�޸ĵĵ��ʻ���Ͳ���Ϊ�ո�","����",JOptionPane.WARNING_MESSAGE);
				
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
				
				JOptionPane.showMessageDialog(this, "��¼�޸ĳɹ�","��ϲ",JOptionPane.WARNING_MESSAGE);
				dispose();
				}
				break;
				
			}
		}
		if(boo == false){
			JOptionPane.showMessageDialog(this, "�����ڴ˵���","����",JOptionPane.WARNING_MESSAGE);

			
		}
		sqlHelp.close();
	}

	
}
