/**
 * 我的好友列表,(也包括陌生人，黑名单)
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//处理第一张卡片.
	//好友的panel   在这里定义，在构造函数中初始化
	JPanel jphy1,jphy2,jphy3;  //好友的有三个panel,jphy1是整个大JPanel
	JButton jphy_jb1,jphy_jb2,jphy_jb3;  //三个按钮
	JScrollPane jsp1;   //滚动条
	String owner;
	
	//处理第二张卡片(陌生人). 还是现在这里定义，在构造函数中初始化
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jb1s;
	//
	CardLayout cl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqFriendList qqFriendList=new QqFriendList();  //看效果
	}
	
	//更新在线的好友情况
	public void upateFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" ");
		
		for(int i=0;i<onLineFriend.length;i++)
		{
			
			jb1s[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}
	
	//构造函数
	public QqFriendList(String ownerId)  //好友信息是放在服务器端的
	{
		this.owner=ownerId; 
		//处理第一张卡片(显示好友列表)
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);  //监听。去看监听函数
		jphy_jb3=new JButton("黑名单");
		jphy1=new JPanel(new BorderLayout());
		//假定有50个好友，好友信息放在服务器端
		jphy2=new JPanel(new GridLayout(50,1,4,4));  //4,4代表上下行的空格，就是行间距
		
		//给jphy2，初始化50个好友.
		jb1s =new JLabel[50];
		
		for(int i=0;i<jb1s.length;i++)
		{
			jb1s[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);  //尽量往左边放
			//没启用的时候是图标是灰色哦哦！不能实现监听哦！！！
			jb1s[i].setEnabled(false); //设置是否启用此组件。已启用的组件可以响应用户输入，而未启用的组件则无法响应用户输入
			if(jb1s[i].getText().equals(ownerId))
			{
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);  //实现用户头像的监听
			jphy2.add(jb1s[i]);
			
			
		}
		
		jphy3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		
		jsp1=new JScrollPane(jphy2);  //2号panel在滑动条
		
		
		//对jphy1,初始化  jphy1是边界布局
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//处理第二张卡片，与第一张有很多相似之处
		
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		jpmsr1=new JPanel(new BorderLayout());
		//假定有20个陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//给jphy2，初始化20陌生人.
		JLabel []jb1s2=new JLabel[20];
		
		for(int i=0;i<jb1s2.length;i++)
		{
			jb1s2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jb1s2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		
		//对jpmsr1,初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		cl=new CardLayout();  //new一个卡片布局
		this.setLayout(cl);
		this.add(jphy1,"1");  //第一张卡片是好友panel，
		this.add(jpmsr1,"2");  //第二张是陌生人panel
		//在窗口显示自己的编号.
		this.setTitle(ownerId);
		this.setSize(140, 400);
		this.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果点击了陌生人按钮，就显示第二张卡片
		if(arg0.getSource()==jphy_jb2)  //在第一张卡片中，jphy_jb2表示的就是陌生人
		{
			cl.show(this.getContentPane(), "2");  //不能直接写this
		}else if(arg0.getSource()==jpmsr_jb1){  //在第二张卡片中，jpmsr_jb1表示的就是好友
			cl.show(this.getContentPane(), "1");
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//响应用户双击的事件，并得到好友的编号.
		if(arg0.getClickCount()==2)  //双击表示进入聊天
		{
			//得到该好友的编号
			String friendNo=((JLabel)arg0.getSource()).getText();
			//System.out.println("你希望和 "+friendNo+" 聊天");
			QqChat qqChat=new QqChat(this.owner,friendNo);  //双击之后，就会创建qqChat对象			
			//把聊天界面加入到管理类
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);  //设置前景色显示高亮
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
