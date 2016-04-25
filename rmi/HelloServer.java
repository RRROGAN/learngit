package com.rogan.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

//服务器端，注册绑定远程对象
public class HelloServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建一个远程对象
		try {
			IHello hello = new HelloImpl();
			//注册远程对象
			LocateRegistry.createRegistry(8888);
			//绑定远程对象
			Naming.bind("rmi://localhost:8888/IHello",hello);
			System.out.println("----info:远程对象绑定成功！----");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
