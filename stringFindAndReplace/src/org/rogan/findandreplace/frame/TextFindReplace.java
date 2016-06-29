package org.rogan.findandreplace.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.rogan.findandreplace.service.MatchFun;

/*
 * ���ܣ��ַ����Ĳ��Һ��滻
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
		setTitle("�ı��Ĳ������滻");
		setSize(550,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		tarea = new JTextArea();  //����ı�����
		JScrollPane scroller = new JScrollPane(tarea);
		tfObj1 = new JTextField();  //�滻��ѯ�ı���
		tfObj2 = new JTextField();
		btnFind = new JButton("Find"); //��ʼ��������ť
		btnReplace = new JButton("Replace");
		btnExit = new JButton("Exit");
		
		labObj1 = new JLabel("�ı����򣨴ӹ�괦��ʼ���ң�");  //��ʾ��ʾ��Ϣ
		labObj2 = new JLabel("�����һ��滻��ǰ���ַ�����");
		labObj3 = new JLabel("�滻����ַ���");
		
		pObj1 = new JPanel();  //�������panel�����ڿ����������ϸ������λ�úʹ�С
		pObj2 = new JPanel();
		pObj3 = new JPanel();
		pObj4 = new JPanel();
		pObj5 = new JPanel();
		
		//���pObj1���ڿ����ı��������ʾ��Ϣ�����λ��
		pObj1.setLayout(new BorderLayout());
		pObj1.add("North",labObj1);
		pObj1.add("Center",scroller);
		
		//���pObj2���ڿ��Ƶ�һ���ı������ʾ��Ϣ�����λ��
		pObj2.setLayout(new BorderLayout());
		pObj2.add("North",labObj2);
		pObj2.add("Center",tfObj1);
		
		//���pObj3���ڿ��Ƶڶ����ı������ʾ��Ϣ�����λ��
		pObj3.setLayout(new BorderLayout());
		pObj3.add("North",labObj3);
		pObj3.add("Center",tfObj2);
		
		//���pObj4���ڿ���Find Replace��Exit��ť�����λ��
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
		
		validate(); //ȷ�����������Ч�Ĳ���
		
		//���ö԰�ť�ļ���
		btnFind.addActionListener(this);
		btnReplace.addActionListener(this);
		btnExit.addActionListener(this);
		
		//����һ���Ի��򣬵��û�����Find��Replace��ť����ʾһ�����ֽ����Ϣ�ĶԻ��򣬶Ի�������һ��Label�����һ��
		//Ok��ť��Label���������ʾ���һ��滻�ַ����Ĵ���
		diaObj = new JDialog(this);  //JDialogҲ�Ƕ�������֮һ
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
		JButton butObj = (JButton)(e.getSource());  //����¼�Դ��ť
		if(butObj.getLabel() == "Exit"){
			System.exit(0);
		}
		
		if(butObj.getLabel() == "Find" || butObj.getLabel() == "Replace"){
			String str1 = tarea.getText();  //��ȡ�ı���������ַ�������
			String str2 = tfObj1.getText();  //��ȡҪ���ҵ��ַ���
			int matchNum = 0;  //����ַ���ƥ��Ĵ���
			int cursorPos = tarea.getCaretPosition();  //��Ź�굱ǰ�� λ��
			 
			MatchFun classObj = new MatchFun();
			if(butObj.getLabel() == "Find"){
				matchNum = classObj.strFind(str1, str2, cursorPos);
				textLab.setText("���ҵ�"+matchNum+"��"); //�ڶԻ�������ʾ���Ҵ���
				diaObj.show();  //��ʾ���ҽ���Ի���
			}
			
			if(butObj.getLabel() == "Replace"){
				String str3 = tfObj2.getText();  //��ȡҪ�滻���ַ���
				matchNum = classObj.strReplace(str1, str2, str3, cursorPos);
				textLab.setText("���滻��" + matchNum + "��");
				StringBuffer taText = classObj.regStr;
				tarea.setText(taText.toString());  //ˢ���滻���ı������������ʾ
				diaObj.show(); //��ʾ�滻����Ի���
				
			}
		}
	}
	
}
