/**
 * 2016年4月25日16:14:34
 * @author Rogan
 * 功能：远程方法调用案例
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
		return "你好，"+ someBodyName;
	}

}
