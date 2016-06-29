package org.rogan.frame;
/*
 * 功能：词典程序主界面，实现查询功能
 * */
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import org.omg.CORBA.Environment;
import org.rogan.service.*;
import org.rogan.util.SqlHelper;


public class DataWindow extends JFrame implements ActionListener {
	
	JFrame topFrame;
	JDesktopPane desktop;
	FileDialog filedialog_save;  //声明文件对话框
	JTextField englishtext;
	JTextArea chinesetext;
	
	//声明查询、添加、修改、删除、声音按钮
	JButton btnQuery,btnAdd,btnEdit,btnDel,btnSound;  
	JButton refresh;
	
	JLabel label;
	JMenuBar mbar;
	JMenu mfile,medit,mhelp;
	//声明功能菜单项
	JMenuItem edic,cdic,back_data,quit,addedit,modedit,deledit,hhelp,about;
	
	
	//主窗口界面
	public DataWindow() {
		// TODO Auto-generated constructor stub
		super("英汉小词典");
		this.setBounds(250,250,600,400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		//JFrame本身是一个框架，并不是容器？？JFrame 有一个 Content Pane，窗口能显示的所有组件都是添加在这个 Content Pane 中。
		getContentPane().add(new JScrollPane(chinesetext));
		mbar = new JMenuBar();  //新建菜单条
		setJMenuBar(mbar);
		mbar.setOpaque(true);  //设置控件不透明
		mfile = new JMenu("文件");
		medit = new JMenu("编辑");
		mhelp = new JMenu("帮助");
		
		mbar.add(mfile);
		mbar.add(medit);
		mbar.add(mhelp);
		
		edic = new JMenuItem("英汉词典");
		cdic = new JMenuItem("汉英词典");
		back_data = new JMenuItem("备份词库");
		quit = new JMenuItem("退出");  //文件菜单
		addedit = new JMenuItem("添加词汇");
		modedit = new JMenuItem("修改词汇");
		deledit = new JMenuItem("删除词汇");
		hhelp = new JMenuItem("帮助");
		about = new JMenuItem("关于…");
		
		mfile.add(edic);
		mfile.add(cdic);
		mfile.add(back_data);
		mfile.addSeparator();
		mfile.add(quit);
		
		medit.add(addedit);
		medit.add(modedit);
		medit.add(deledit);
		
		mhelp.add(hhelp);
		mhelp.add(about);
		
		edic.addActionListener(this);
		cdic.addActionListener(this);
		quit.addActionListener(this);
		back_data.addActionListener(this);
		addedit.addActionListener(this);
		modedit.addActionListener(this);
		deledit.addActionListener(this);
		hhelp.addActionListener(this);
		about.addActionListener(this);
		
		englishtext = new JTextField(16);
		englishtext.setFont(new Font("",15,15));
		chinesetext  = new JTextArea(8,15);
		chinesetext.setEditable(false);
		
		btnQuery = new JButton("查询");
		btnSound = new JButton("发音");
		btnAdd = new JButton("添加");
		btnEdit = new JButton("修改");
		btnDel = new JButton("删除");
		refresh = new JButton("刷新");
		
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		
		label = new JLabel("输入要查询的英语单词");
		label.setFont(new Font("隶书",20,20));
		
		p1.add(label);
		p1.add(englishtext);
		p1.add(btnQuery);
		p1.add(btnSound);
		
		p2.add(btnAdd);
		p2.add(btnEdit);
		p2.add(btnDel);
		p2.add(refresh);
		
		this.add(p1,"North");
		this.add(p2,"South");
		this.add(new JScrollPane(chinesetext),"Center");
		
		btnQuery.addActionListener(this);
		btnAdd.addActionListener(this);
		btnEdit.addActionListener(this);
		btnDel.addActionListener(this);
		btnSound.addActionListener(this);
		englishtext.addActionListener(this);
		refresh.addActionListener(this);
		
		
		//创建对话框对象，用于备份数据库
		filedialog_save  = new FileDialog(this,"保存文件对话框",FileDialog.SAVE);
		
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void init() throws SQLException{
		String cname,ename;
		String sql = "select * from dic";
		String[] paras = {};
		SqlHelper sqlHelp = new SqlHelper();
		ResultSet rs = sqlHelp.query(sql, paras);
		chinesetext.setText("");
		while(rs.next()){
			ename = rs.getString("word");
			cname = rs.getString("meaning");
			chinesetext.append(ename+"\t\t"+cname+"\n");
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == btnQuery || e.getSource() == englishtext){
			chinesetext.setText("");
			if(englishtext.getText().equals("")){
				JOptionPane.showMessageDialog(this, "查询对象不能为空！","警告",JOptionPane.WARNING_MESSAGE);
				
			}else{
				//调用查询方法
				try {
					Listwords();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		else if(e.getSource()==btnAdd || e.getSource() == addedit){ //添加功能
			new AddWin();  //AddWin是实现“添加”功能的类
		}
		else if(e.getSource() == btnEdit || e.getSource() == modedit){
			new ModifyWin();
		}
		else if(e.getSource() == btnDel || e.getSource() == deledit){
			new DelWin();
		}
		else if(e.getSource() == btnSound){
			if(englishtext.getText() != null){
				try {
					InputStream is = getClass().getResource("xxx").openStream();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource() == edic){
			label.setText("输入要查询的英语单词");
		//	btnQuery.setVisible(true);  //????
		//	btnSound.setVisible(true);
		}
		else if(e.getSource() == refresh){
			try {
				init();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == cdic){
			label.setText("输入要查询的汉语意思");
			btnQuery.setVisible(true);
			btnSound.setVisible(true);
		}
		else if(e.getSource() == back_data){
			File fromfile = new File("english\\english.mdb");
			FileInputStream fis = null;
			filedialog_save.setVisible(true);
			
			try {
				fis = new FileInputStream(fromfile); //建立文件输入流
				int bytesRead; //定义变量来存储输入流中读取出来的文件
				byte[] buf = new byte[4 * 1024];
				File tofile = new File(filedialog_save.getDirectory(),
						filedialog_save.getFile());
				FileOutputStream fos = new FileOutputStream(tofile); //建立文件输出流
				while ((bytesRead = fis.read(buf)) != -1) {
					fos.write(buf, 0, bytesRead);
				}
				fos.flush();
				fos.close();
				fis.close();
			} catch (Exception e2) {
				// TODO: handle exception
			} finally {
			}
			
			
		}
		
		else if(e.getSource() == quit){
			System.exit(0);
		}
		else if(e.getSource() == about){
			final String AboutMsg = "A Electrical Dictionary 1.0 \n\n"+
		"An application wirtten to show off the function of dictionary.\n\n"
					+"\n\n"+"Copyright(c)2010";
			JOptionPane.showMessageDialog(topFrame, AboutMsg);
		}
		else if(e.getSource() == hhelp){
			JInternalFrame help;
			try {
				help = new HelpFrame();
				help.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
	}
	
	
	//实现查询功能
	public void Listwords() throws Exception{
		String cname,ename;
		SqlHelper sqlHelp = new SqlHelper();
		if(label.getText().equals("输入要查询的英语单词")){
			String sql = "select * from dic where word=?";
			String[] paras = {englishtext.getText()};
			ResultSet rs = sqlHelp.query(sql, paras);
			while(rs.next()){
				ename = rs.getString("word");
				cname = rs.getString("meaning");
				//if(ename.equals(englishtext.getText())){
					chinesetext.append(ename+"\t\t"+cname+"\n");
				//}
			}
			sqlHelp.close();
			if(chinesetext.getText().equals("")){  //没有查到
				JOptionPane.showMessageDialog(this, "词库里没有哦哦，(づ￣3￣づ╭❤～","警告",JOptionPane.WARNING_MESSAGE);
				
			}
		}
		else if(label.getText().equals("输入要查询的汉语意思")){
			String sql = "select * from dic where meaning like '%" + englishtext.getText() + "%'";
			//System.out.println(englishtext.getText());
			String[] paras = {};
			ResultSet rs = sqlHelp.query(sql, paras);
			while(rs.next()){
				ename = rs.getString("word");
				cname = rs.getString("meaning");
				chinesetext.append(ename+"\n");
			}
			sqlHelp.close();  //关闭数据库
			
			if(chinesetext.getText().equals("")){
				JOptionPane.showMessageDialog(this,"查不到啦！！","警告",JOptionPane.WARNING_MESSAGE);
			}
			
		}
	}

}
