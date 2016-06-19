/**
 * �ҵĺ����б�,(Ҳ����İ���ˣ�������)
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//�����һ�ſ�Ƭ.
	//���ѵ�panel   �����ﶨ�壬�ڹ��캯���г�ʼ��
	JPanel jphy1,jphy2,jphy3;  //���ѵ�������panel,jphy1��������JPanel
	JButton jphy_jb1,jphy_jb2,jphy_jb3;  //������ť
	JScrollPane jsp1;   //������
	String owner;
	
	//����ڶ��ſ�Ƭ(İ����). �����������ﶨ�壬�ڹ��캯���г�ʼ��
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jb1s;
	//
	CardLayout cl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendList qqFriendList=new QqFriendList();  //��Ч��
	}
	
	//�������ߵĺ������
	public void upateFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" ");
		
		for(int i=0;i<onLineFriend.length;i++)
		{
			
			jb1s[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}
	
	//���캯��
	public QqFriendList(String ownerId)  //������Ϣ�Ƿ��ڷ������˵�
	{
		this.owner=ownerId; 
		//�����һ�ſ�Ƭ(��ʾ�����б�)
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		jphy_jb2.addActionListener(this);  //������ȥ����������
		jphy_jb3=new JButton("������");
		jphy1=new JPanel(new BorderLayout());
		//�ٶ���50�����ѣ�������Ϣ���ڷ�������
		jphy2=new JPanel(new GridLayout(50,1,4,4));  //4,4���������еĿո񣬾����м��
		
		//��jphy2����ʼ��50������.
		jb1s =new JLabel[50];
		
		for(int i=0;i<jb1s.length;i++)
		{
			jb1s[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);  //��������߷�
			//û���õ�ʱ����ͼ���ǻ�ɫŶŶ������ʵ�ּ���Ŷ������
			jb1s[i].setEnabled(false); //�����Ƿ����ô�����������õ����������Ӧ�û����룬��δ���õ�������޷���Ӧ�û�����
			if(jb1s[i].getText().equals(ownerId))
			{
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);  //ʵ���û�ͷ��ļ���
			jphy2.add(jb1s[i]);
			
			
		}
		
		jphy3=new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);  //2��panel�ڻ�����
		
		
		//��jphy1,��ʼ��  jphy1�Ǳ߽粼��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//����ڶ��ſ�Ƭ�����һ���кܶ�����֮��
		
		jpmsr_jb1=new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("������");
		jpmsr1=new JPanel(new BorderLayout());
		//�ٶ���20��İ����
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//��jphy2����ʼ��20İ����.
		JLabel []jb1s2=new JLabel[20];
		
		for(int i=0;i<jb1s2.length;i++)
		{
			jb1s2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jb1s2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		
		//��jpmsr1,��ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		cl=new CardLayout();  //newһ����Ƭ����
		this.setLayout(cl);
		this.add(jphy1,"1");  //��һ�ſ�Ƭ�Ǻ���panel��
		this.add(jpmsr1,"2");  //�ڶ�����İ����panel
		//�ڴ�����ʾ�Լ��ı��.
		this.setTitle(ownerId);
		this.setSize(140, 400);
		this.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//��������İ���˰�ť������ʾ�ڶ��ſ�Ƭ
		if(arg0.getSource()==jphy_jb2)  //�ڵ�һ�ſ�Ƭ�У�jphy_jb2��ʾ�ľ���İ����
		{
			cl.show(this.getContentPane(), "2");  //����ֱ��дthis
		}else if(arg0.getSource()==jpmsr_jb1){  //�ڵڶ��ſ�Ƭ�У�jpmsr_jb1��ʾ�ľ��Ǻ���
			cl.show(this.getContentPane(), "1");
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫�����¼������õ����ѵı��.
		if(arg0.getClickCount()==2)  //˫����ʾ��������
		{
			//�õ��ú��ѵı��
			String friendNo=((JLabel)arg0.getSource()).getText();
			//System.out.println("��ϣ���� "+friendNo+" ����");
			QqChat qqChat=new QqChat(this.owner,friendNo);  //˫��֮�󣬾ͻᴴ��qqChat����			
			//�����������뵽������
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);  //����ǰ��ɫ��ʾ����
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
