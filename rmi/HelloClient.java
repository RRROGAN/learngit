package com.rogan.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

//使用远程对象
public class HelloClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IHello hello = (IHello)Naming.lookup("rmi://localhost:8888/IHello");
			System.out.println(hello.helloWorld());
			System.out.println(hello.sayHelloToSomeBody("Rogan"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
