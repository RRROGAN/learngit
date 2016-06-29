package org.rogan.findandreplace.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.rogan.findandreplace.service.MatchFun;

/*
 * 功能：字符串的查找和替换
 * */
public class TextFindReplace extends JFrame implements ActionListener {

	private JTextArea tarea;
	private JTextField tfObj1,tfObj2;
	private JButton btnFind;
	private JButton btnReplace;
	private JButton btnExit;
	private JPanel pObj1,pObj2,pObj3,pObj4,pObj5;
	private JLabel labObj1,labObj2,labObj3;
	boolean boolObj = false;
	JDialog diaObj;
	JLabel textLab;
	
	JButton butObj2 = new JButton("OK");
	public TextFindReplace() {
		// TODO Auto-generated constructor stub
		setTitle("文本的查找与替换");
		setSize(550,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tarea = new JTextArea();  //存放文本内容
		JScrollPane scroller = new JScrollPane(tarea);
		tfObj1 = new JTextField();  //替换查询文本框
		tfObj2 = new JTextField();
		btnFind = new JButton("Find"); //初始化三个按钮
		btnReplace = new JButton("Replace");
		btnExit = new JButton("Exit");
		
		labObj1 = new JLabel("文本区域（从光标处开始查找）");  //显示提示信息
		labObj2 = new JLabel("待查找或替换当前的字符串：");
		labObj3 = new JLabel("替换后的字符串");
		
		pObj1 = new JPanel();  //定义五个panel，用于控制主界面上各组件的位置和大小
		pObj2 = new JPanel();
		pObj3 = new JPanel();
		pObj4 = new JPanel();
		pObj5 = new JPanel();
		
		//面板pObj1用于控制文本区域和提示信息的相对位置
		pObj1.setLayout(new BorderLayout());
		pObj1.add("North",labObj1);
		pObj1.add("Center",scroller);
		
		//面板pObj2用于控制第一个文本框和提示信息的相对位置
		pObj2.setLayout(new BorderLayout());
		pObj2.add("North",labObj2);
		pObj2.add("Center",tfObj1);
		
		//面板pObj3用于控制第二个文本框和提示信息的相对位置
		pObj3.setLayout(new BorderLayout());
		pObj3.add("North",labObj3);
		pObj3.add("Center",tfObj2);
		
		//面板pObj4用于控制Find Replace和Exit按钮的相对位置
		pObj4.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		pObj4.add(btnFind);
		pObj4.add(btnReplace);
		pObj4.add(btnExit);
		
		pObj5.setLayout(new GridLayout(3,1));
		pObj5.add(pObj2);
		pObj5.add(pObj3);
		pObj5.add(pObj4);
		
		setLayout(new GridLayout(1,2));
		add(pObj1);
		add(pObj5);
		
		validate(); //确保组件具有有效的布局
		
		//设置对按钮的监听
		btnFind.addActionListener(this);
		btnReplace.addActionListener(this);
		btnExit.addActionListener(this);
		
		//创建一个对话框，当用户单击Find或Replace按钮后，显示一个呈现结果信息的对话框，对话框上有一个Label组件和一个
		//Ok按钮，Label组件用于显示查找或替换字符串的次数
		diaObj = new JDialog(this);  //JDialog也是顶层容器之一
		diaObj.setLayout(new FlowLayout(FlowLayout.CENTER,40,20));
		textLab = new JLabel("");
		diaObj.add(textLab);
		diaObj.add(butObj2);
		butObj2.addActionListener(this);
		diaObj.setSize(230,100);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton butObj = (JButton)(e.getSource());  //获得事件源按钮
		if(butObj.getLabel() == "Exit"){
			System.exit(0);
		}
		
		if(butObj.getLabel() == "Find" || butObj.getLabel() == "Replace"){
			String str1 = tarea.getText();  //获取文本区域的主字符串内容
			String str2 = tfObj1.getText();  //获取要查找到字符串
			int matchNum = 0;  //存放字符串匹配的次数
			int cursorPos = tarea.getCaretPosition();  //存放光标当前的 位置
			 
			MatchFun classObj = new MatchFun();
			if(butObj.getLabel() == "Find"){
				matchNum = classObj.strFind(str1, str2, cursorPos);
				textLab.setText("共找到"+matchNum+"处"); //在对话框中显示查找次数
				diaObj.show();  //显示查找结果对话框
			}
			
			if(butObj.getLabel() == "Replace"){
				String str3 = tfObj2.getText();  //获取要替换的字符串
				matchNum = classObj.strReplace(str1, str2, str3, cursorPos);
				textLab.setText("共替换到" + matchNum + "处");
				StringBuffer taText = classObj.regStr;
				tarea.setText(taText.toString());  //刷新替换后文本区域的文字显示
				diaObj.show(); //显示替换结果对话框
				
			}
		}
	}
	
}
