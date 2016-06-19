/**
 * 这是qq服务器，它在监听，等待某个qq客户端来连接
 */
package com.qq.server.model;
import com.qq.common.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class MyQqServer {

	
	
	public MyQqServer()
	{
		
		try {//容易发生异常，所以try一下
			
			//在9999监听
			System.out.println("我是服务器，在9999监听"); //监听只能在本台服务器上
			ServerSocket ss=new ServerSocket(9999);
			
			while(true)  //没有true，就只会监听一次
			{
				//阻塞,等待连接
				Socket s=ss.accept();  
				
				//接收客户端发来的信息.登陆是发过来的用户名和密码
				//BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))
				//String info = br.readLine();
				//以对象流的方式读取对象
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject(); //读取一个对象
				System.out.println("服务器接收到用户id:"+u.getUserId()+"  密码:"+u.getPasswd());
				Message m=new Message();  //肯定是要返回一个包
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456"))  //不到数据库验证，简单验证一下
				{
					//返回一个成功登陆的信息报
					
					m.setMesType("1");  //1认为是成功
					oos.writeObject(m);  //把m返回给客户端，b就是true
					
					//这里就单开一个线程，让该线程与该客户端保持通讯.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//启动与该客户端通信的线程.
					scct.start();
					
					//并通知其它在线用户.
					scct.notifyOther(u.getUserId());
				}else{
					m.setMesType("2");  //认为是登陆失败，关闭连接
					oos.writeObject(m);
					//关闭Socket
					s.close();
					
				}
				
				
			}	
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		
	}
	
}
