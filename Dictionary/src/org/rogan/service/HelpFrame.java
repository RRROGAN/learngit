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


class HtmlPane extends JScrollPane{  //����һ����ȡHTML��ʽ�����ļ������
	JEditorPane editp;
	public HtmlPane() throws Exception {
		// TODO Auto-generated constructor stub
		File f = new File("C:\\Users\\Administrator\\Desktop\\help.html");  //�����ļ�����
		String s = f.getPath();  //��ȡ�ļ�·��
		s = "file:" + s;
		URL url = new URL(s);  //ͨ��URL��ʽ����ȡ����HTML�ļ�
		editp = new JEditorPane(s);
		JViewport vp = getViewport();  //����һ����Ϣ��ͼ���Ա�����ļ�����
		vp.add(editp);
	}
}
