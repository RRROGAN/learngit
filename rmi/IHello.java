package com.rogan.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

//声明为远程对象
public interface IHello extends Remote {

	public String helloWorld() throws RemoteException;
	public String sayHelloToSomeBody(String someBodyName) throws RemoteException;
}
