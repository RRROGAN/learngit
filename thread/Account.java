/**
 * 2016��4��27��21:22:11
 * ���ܣ��߳�ͬ������
 * */
package com.rogan.thread;

public class Account {
	
	private double balance;

	public Account() {
		balance = 0;
		System.out.println("Total Money:" + balance);
	}
	
	//ȡ���ٽ����
	public synchronized void withdraw(double money){
		if(balance == 0){
			try {
				this.wait();  //����ͬ������Ļ�������ӵ�л���������ִ��
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance = balance - money;
		System.out.println("withdraw 100 success");
		notify();  //����ӵ�и÷����Ļ������ĵȴ������еĵ�һ���̣߳�ͬʱ�Զ���øû������Ļ�������
	}
	
	//����ٽ����
	public synchronized void deposit(double money){
		if(balance != 0){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		balance = balance + money;
		System.out.println("deposit 100 success");
		notify(); 
	}
	
	
}
