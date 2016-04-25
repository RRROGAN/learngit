package com.rogan.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

//�������ˣ�ע���Զ�̶���
public class HelloServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//����һ��Զ�̶���
		try {
			IHello hello = new HelloImpl();
			//ע��Զ�̶���
			LocateRegistry.createRegistry(8888);
			//��Զ�̶���
			Naming.bind("rmi://localhost:8888/IHello",hello);
			System.out.println("----info:Զ�̶���󶨳ɹ���----");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
