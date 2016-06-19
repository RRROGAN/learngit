/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.client.model.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qqChat=new QqChat("1");
	}
	
	public QqChat(String ownerId,String friend) //和谁在聊
	{
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jtf=new JTextField(15);//文本框
		jb=new JButton("发送");
		jb.addActionListener(this);  //监听发送按钮，
		jp=new JPanel();
		jp.add(jtf); //放入文本框
		jp.add(jb);   //放入按钮
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+" 正在和 "+friend+" 聊天");
		//换一个图标
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		this.setSize(300, 200);
		this.setVisible(true);
		
		
	}
	
	//写一个方法，让它显示消息
	public void showMessage(Message m)
	{
		String info=m.getSender()+" 对 "+m.getGetter()+" 说:"+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==jb)
		{
			//如果用户点击了发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes); //给message赋值
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());  //文本域拿到
			m.setSendTime(new java.util.Date().toString()); //将当前时间传进去
			//m搞好了，发送给服务器.怎么发送呢？？？
			//发送给服务器，最最要的是拿到socket，socket在qqclient中，怎么搞？？？
			//
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
			
		}
			
	}

//	public void run() {
//		// TODO Auto-generated method stub
//		while(true)
//		{
//			try {
//				//读取[如果读不到就等待.]
//				ObjectInputStream ois=new ObjectInputStream(QqClientConServer.s.getInputStream());
//				
//				Message m=(Message)ois.readObject();
//				
//				//显示
//				String info=m.getSender()+" 对 "+m.getGetter()+" 说:"+m.getCon()+"\r\n";
//				this.jta.append(info);
//				
//				
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				// TODO: handle exception
//			}
//		
//			
//			
//		}
//		
//	}

}
