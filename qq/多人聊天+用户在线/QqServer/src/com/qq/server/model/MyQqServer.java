/**
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ���������
 */
package com.qq.server.model;
import com.qq.common.*;
import java.net.*;
import java.io.*;
import java.util.*;
public class MyQqServer {

	
	
	public MyQqServer()
	{
		
		try {//���׷����쳣������tryһ��
			
			//��9999����
			System.out.println("���Ƿ���������9999����"); //����ֻ���ڱ�̨��������
			ServerSocket ss=new ServerSocket(9999);
			
			while(true)  //û��true����ֻ�����һ��
			{
				//����,�ȴ�����
				Socket s=ss.accept();  
				
				//���տͻ��˷�������Ϣ.��½�Ƿ��������û���������
				//BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()))
				//String info = br.readLine();
				//�Զ������ķ�ʽ��ȡ����
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject(); //��ȡһ������
				System.out.println("���������յ��û�id:"+u.getUserId()+"  ����:"+u.getPasswd());
				Message m=new Message();  //�϶���Ҫ����һ����
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456"))  //�������ݿ���֤������֤һ��
				{
					//����һ���ɹ���½����Ϣ��
					
					m.setMesType("1");  //1��Ϊ�ǳɹ�
					oos.writeObject(m);  //��m���ظ��ͻ��ˣ�b����true
					
					//����͵���һ���̣߳��ø��߳���ÿͻ��˱���ͨѶ.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//������ÿͻ���ͨ�ŵ��߳�.
					scct.start();
					
					//��֪ͨ���������û�.
					scct.notifyOther(u.getUserId());
				}else{
					m.setMesType("2");  //��Ϊ�ǵ�½ʧ�ܣ��ر�����
					oos.writeObject(m);
					//�ر�Socket
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
