package org.rogan.service;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.*;

public class HelpFrame extends JInternalFrame {
	public HelpFrame() throws Exception {
		// TODO Auto-generated constructor stub
		super("Help",true,true,true,true);
		setBounds(120,50,270,260);
		HtmlPane p = new HtmlPane();
	}
}


class HtmlPane extends JScrollPane{  //建立一个读取HTML格式帮助文件的面板
	JEditorPane editp;
	public HtmlPane() throws Exception {
		// TODO Auto-generated constructor stub
		File f = new File("C:\\Users\\Administrator\\Desktop\\help.html");  //建立文件对象
		String s = f.getPath();  //获取文件路径
		s = "file:" + s;
		URL url = new URL(s);  //通过URL格式，读取本地HTML文件
		editp = new JEditorPane(s);
		JViewport vp = getViewport();  //建立一个信息视图，以便浏览文件内容
		vp.add(editp);
	}
}
