/**
 * 2016��4��25��16:14:34
 * @author Rogan
 * ���ܣ�Զ�̷������ð���
 * */
package com.rogan.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements IHello {

	public HelloImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String helloWorld() throws RemoteException {
		// TODO Auto-generated method stub
		return "hello,world";
	}

	@Override
	public String sayHelloToSomeBody(String someBodyName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return "��ã�"+ someBodyName;
	}

}
